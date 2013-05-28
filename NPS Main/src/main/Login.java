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

import panels.MainScreenPanel;
import panels.NewsPanel;

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
	private static NewsPanel newsPanel;

	// public static void createFrame() {
	// if (!serverOnline) {
	// Connection c = new Connection("nazipenguinslayer.no-ip.org",
	// "6987", null, null);
	// JOptionPane.showMessageDialog(null,
	// "Server offline. Please try again later or check the FAQ.");
	// System.exit(0);
	// }
	//
	// f = new JFrame("Login");
	// user = new JTextField();
	// pass = new JPasswordField();
	// cancel = new JButton();
	// login = new JButton();
	//
	// user.setBounds(5, 10, WIDTH - 15, 25);
	// user.setText("Username");
	// user.addFocusListener(new FocusListener() {
	// public void focusGained(FocusEvent arg0) {
	// user.setText("");
	// }
	//
	// public void focusLost(FocusEvent arg0) {
	// }
	// });
	//
	// pass.setBounds(5, 40, WIDTH - 15, 25);
	// pass.setText("Password");
	// pass.addFocusListener(new FocusListener() {
	// public void focusGained(FocusEvent arg0) {
	// pass.setText("");
	// }
	//
	// public void focusLost(FocusEvent arg0) {
	// }
	// });
	//
	// login.setBounds(5, 80, WIDTH - 15, 50);
	// login.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// login(user.getText(), pass.getText());
	// }
	// });
	// login.setText("add image, login");
	//
	// cancel.setBounds(5, 135, WIDTH - 15, 50);
	// cancel.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent arg0) {
	// System.exit(0);
	// }
	// });
	// cancel.setText("add image, cancel");
	//
	// f.setResizable(false);
	// f.setAlwaysOnTop(true);
	// f.setSize(WIDTH, HEIGHT);
	// f.setLocationRelativeTo(null);
	// f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	// f.setLayout(null);
	// f.add(user);
	// f.add(pass);
	// f.add(cancel);
	// f.add(login);
	// f.setFocusable(true);
	// f.setVisible(true);
	//
	// // make frame show on top of other windows
	// f.setAlwaysOnTop(true);
	// f.toFront();
	// f.requestFocus();
	// f.setAlwaysOnTop(false);
	// }

	public static void createFrame2() {
		f = new JFrame("Nazi Penguin Slayer   Version: " + version);
		f.setSize(width, height);
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		newsPanel = new NewsPanel(width, height);
		f.add(newsPanel);
		f.setVisible(true);
	}

	private static void login(String user, String pass) {
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

		Connection conn = new Connection("nazipenguinslayer.no-ip.org", "6987",
				null, "login");

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
}
