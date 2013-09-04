package main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JTextArea;

/**
 * key terms for updating::::::::::::::::::::::::::::::::::::::::::::::::::::
 * get version: gets the current version of the client:::::::::::::::::::::::
 * updating: tells the client that we are going to update it:::::::::::::::::
 * ok: sent from the client to tell the server to continue. it got the message
 */

public class MainServerThread extends MainServerClass implements Runnable,
		Serializable {

	JTextArea jta;
	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	static String version = "0_0_1";
	String username;
	String clientVersion;

	public MainServerThread(Socket s) {
		socket = s;

		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			MainServerClass.updateArea("New connection from "
					+ socket.getLocalAddress().toString()
					+ " has been established.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		switch ((String) read()) {
		case "update":
			send("ok");
			MainServerClass.updateArea(socket.getLocalAddress().toString()
					+ " is updating");
			updateClient();
			break;
		case "login":
			send("ok");
			MainServerClass.updateArea(socket.getLocalAddress().toString()
					+ " is logging in");
			login();
			break;
		case "logout":
			send("ok");
			MainServerClass.updateArea(socket.getLocalAddress().toString()
					+ " is logging out");
			logout();
			break;
		case "uniqueUsername":
			send("ok");
			checkUniqueUsername();
			break;
		case "createAccount":
			send("ok");
			createAccount();
			break;
		}
	}

	private void createAccount() {
		String user;
		String pass;
		String email;
		String name;

		user = (String) read();
		send("ok");

		pass = (String) read();
		send("ok");

		email = (String) read();
		send("ok");

		name = (String) read();
		send("ok");

		/**
		 * create the new properties file containing the info
		 */
		Properties p = new Properties();
		p.setProperty("username", user);
		p.setProperty("password", pass);
		p.setProperty("name", name);
		p.setProperty("email", email);
		p.setProperty("activated", "false");

		try {
			FileWriter fw = new FileWriter("A:\\test\\users\\" + user
					+ ".properties");
			p.store(fw, user + " user properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		send("done");
		closeEverything();
	}

	private void checkUniqueUsername() {
		boolean matched = false;
		String[] alf;
		// File originDir = new File("/home/pi/Programming/NPS/Users");
		File originDir = new File("A:\\test\\users");
		alf = originDir.list();

		String username = (String) read();

		for (int i = 0; i < alf.length; i++) {
			if (alf[i].equals(username + ".properties")) {
				matched = true;
			}
		}

		if (matched) {
			send("matched");
		} else {
			send("no match");
		}

		closeEverything();
	}

	public void updateClient() {
		if (isClientUpToDate()) {
			send("dont update");
			return;
		}
		// tell client that you are going to update it
		send("updating");
		if (!read().equals("ok"))
			return;

		/**
		 * figure out which zip to send
		 * 
		 */

		String zipLocation = "A:\\test\\patches\\";
		File zipsDir = new File(zipLocation);
		String[] zipFiles = zipsDir.list();
		ArrayList<File> zipsToSend = new ArrayList<File>();
		ArrayList<UpdateObject> updateArray = new ArrayList<UpdateObject>();
		int filesIndex = 0;

		for (int i = 0; i < zipFiles.length; i++) {
			if (zipFiles[i].equals(clientVersion + ".zip")) {
				filesIndex = i + 1;
			}
		}

		for (int i = filesIndex; i < zipFiles.length; i++) {
			zipsToSend.add(new File("A:\\test\\patches\\" + zipFiles[i]));
		}

		// number of files going to be sent
		send(updateArray.size());
		if (!read().equals("ok"))
			return;

		// add all the update files
		for (int i = 0; i < zipsToSend.size(); i++) {
			updateArray.add(new UpdateObject(zipsToSend.get(i).getName(),
					copyFile(zipsToSend.get(i).getPath())));
		}

		// send the files
		for (int i = 0; i < updateArray.size(); i++) {
			send(updateArray.get(i));
			if (!read().equals("ok"))
				return;
		}

		send("done");
		if (!read().equals("ok"))
			return;
		closeEverything();
	}

	public void login() {
		String u = (String) read();
		username = u;
		boolean usernameMatched = false, passwordMatched = false, activated = false;
		Properties p = new Properties();

		String[] alf;
		File originDir = new File("A:\\test\\users");
		alf = originDir.list();

		for (int i = 0; i < alf.length; i++) {
			if (alf[i].equals(username + ".properties")) {
				usernameMatched = true;
				try {
					p.load(new FileInputStream(originDir + "/" + alf[i]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (usernameMatched) {
			send("matched");
			String password = (String) read();
			if (p.getProperty("password").equals(password)) {
				send("matched");
				passwordMatched = true;
				if (p.getProperty("activated").equals("true"))
					activated = true;
			} else {
				send("no");
			}
		} else {
			send("no");
			return;
		}

		if (usernameMatched && passwordMatched && activated) {

			MainServerClass.updateArea(socket.getLocalAddress() + " "
					+ username + " successfully logged in");
			loggedin.add(socket.getLocalAddress().toString());
			send(p);
		}

		closeEverything();
	}

	public void logout() {
		loggedin.remove(socket.getLocalAddress().toString() + " " + username);
		send("done");
		closeEverything();
	}

	public void closeEverything() {
		try {
			in.close();
			out.flush();
			out.close();
			socket.close();
			MainServerClass.updateArea("Closed Everything on "
					+ socket.getLocalAddress().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] copyFile(String dir) {
		File f = new File(dir);
		byte[] mybytearray = null;

		try {
			mybytearray = new byte[(int) f.length()];
			FileInputStream fis;
			fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(mybytearray, 0, mybytearray.length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mybytearray;
	}

	public boolean isClientUpToDate() {
		boolean upToDate = false;

		send("get version");
		clientVersion = (String) read();

		if (clientVersion.equals(version))
			upToDate = true;
		else
			upToDate = false;

		return upToDate;
	}

	public void send(Object ob) {
		try {
			out.writeObject(ob);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object read() {
		Object uo = null;
		try {
			uo = in.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uo;
	}
}
