package main;

import files.FILES;
import io.Connection;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.*;

import panels.LoginPanel;
import panels.MainScreenPanel;
import panels.OptionsWindow;

public class Login extends Start {

	static JFrame f;
	static JTextField user;
	static JPasswordField pass;
	static JButton cancel, login;
	public static boolean serverOnline = true;
	static boolean loggedIn = false;
	// static final int WIDTH = 200, HEIGHT = 225;
	static final int width = 850, height = 500;
	public static String username, password;
	public static boolean loggedin = false;
	public static Properties userProperties = new Properties();
	private static JEditorPane display;

	public static void createFrame() {
		f = new JFrame("Nazi Penguin Slayer   Version: " + version);
		f.setSize(850, 500);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		display = new JEditorPane();
		display.setEditable(false);
		try {
//			display.setPage("http://npslayer.tumblr.com/");

			display.setPage("http://avtechsoftware.weebly.com/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		display.setBorder(null);

		f.add(new JScrollPane(display), BorderLayout.CENTER);
		f.add(new LoginPanel(width, 100), BorderLayout.SOUTH);
		f.setVisible(true);
	}

	public static void login(String user, String pass) {
		username = user;
		password = pass;
		String dir = "login temp.properties";

		File infotemp = new File(dir);
		if (!infotemp.exists())
			try {
				infotemp.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		Properties p = new Properties();
		try {
			p.load(new FileInputStream(dir));
			p.setProperty("username", username);
			p.setProperty("password", password);
			p.save(new FileOutputStream(new File(dir)),
					"temp files for login info");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (username.equals("Username") || username.equals("")
				|| password.equals("Password") || password.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Username or password was not entered. Please enter.");
			return;
		}

		Connection conn = new Connection(FILES.IPAddress, FILES.port, null,
				"login");

		if (!loggedin) {
			JOptionPane
					.showMessageDialog(null,
							"Username or password was incorrect. Please try again or check the FAQ");
			return;
		} else {
			startMenuFrame();
			f.dispose();
			return;
		}

		// put the bottom part here
	}

	public static void setProperties(Properties p) {
		userProperties = p;
	}

	public static void showOptionsWindow() {
		new OptionsWindow(f);
	}
}
