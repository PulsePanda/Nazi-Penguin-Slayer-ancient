package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Connection implements Serializable {
	String IP, port;
	String message = "";
	Socket socket;
	int numberOfFiles;
	ObjectInputStream in;
	ObjectOutputStream out;
	JProgressBar bar;
	static int barVal = 0;

	public Connection(String IP, String port, JProgressBar b, String goal) {
		this.IP = IP;
		this.port = port;
		bar = b;

		try {
			socket = new Socket(IP, Integer.parseInt(port));
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e1) {
			e1.printStackTrace();
			UpdateFrame.serverOnline = false;
			return;
		}

		switch (goal) {
		case "update":
			update();
			break;
		}
	}

	public void update() {
		send("update");
		if (!read().equals("ok"))
			return;

		if (!read().equals("get version"))
			return;
		sendVersion();

		if (!read().equals("updating"))
			return;
		send("ok");
		numberOfFiles = (int) read();
		send("ok");
		bar.setMaximum(numberOfFiles);

		for (int i = 0; i < numberOfFiles; i++) {
			UpdateObject uo = (UpdateObject) read();
			copyUpdate(uo);
			send("ok");
			barVal++;
			bar.setValue(barVal);
			bar.repaint();
		}
		if (!read().equals("done"))
			return;
		send("ok");
	}

	public void closeEverything() {
		try {
			out.flush();
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyUpdate(UpdateObject o) {
		try {
			FileOutputStream fileOutStream = new FileOutputStream(o.getPath());
			fileOutStream.write(o.getFile());
			fileOutStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendVersion() {
		String version = null;
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("properties.properties"));
			version = p.getProperty("version");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("FAIL!!!");
		}
		// FileReader fileReader;
		// String version = null;
		// try {
		// fileReader = new FileReader(
		// "C:\\Program Files\\AVTECH\\NPS\\Files\\bin\\version.txt");
		// BufferedReader bufferedReader = new BufferedReader(fileReader);
		//
		// version = bufferedReader.readLine();
		//
		// bufferedReader.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		send(version);
	}

	public Object read() {
		Object o = null;
		try {
			o = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public void send(Object o) {
		try {
			out.writeObject(o);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
