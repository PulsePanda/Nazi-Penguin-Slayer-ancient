package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JProgressBar;

import main.Login;

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
			Login.serverOnline = false;
			return;
		}

		switch (goal) {
		case "login":
			login();
			break;
		default:
			break;
		}
	}

	public void login() {
		String username = null, password = null;
		Properties p = new Properties();
		String dir = "login temp.properties";
		try {
			p.load(new FileInputStream(dir));
			username = p.getProperty("username");
			password = p.getProperty("password");

			p.setProperty("username", "Username");
			p.setProperty("password", "Password");
			p.save(new FileOutputStream(new File(dir)),
					"temp files for login info");
		} catch (Exception e) {
			e.printStackTrace();
		}

		send("login");
		if (!read().equals("ok")) {
			Login.loggedin = false;
			return;
		}

		send(Login.username);
		if (!read().equals("matched")) {
			Login.loggedin = false;
			return;
		}

		send(Login.password);
		if (!read().equals("matched")) {
			Login.loggedin = false;
			return;
		}

		Login.setProperties((Properties) read());
		Login.loggedin = true;
		return;
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
