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
import java.net.URI;
import java.util.Properties;

import javax.swing.*;

import panels.LoginPanel;
import panels.MainScreenPanel;
import panels.OptionsWindow;

public class Login extends Start {

	static JFrame f;
	static JTextField user;
	public static String createUsername, createPassword, createName,
			createEmail;
	public static boolean uniqueUsername = true;
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
			display.setPage("http://npslayer.tumblr.com/");
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
	}

	public static void setProperties(Properties p) {
		userProperties = p;
	}

	public static void showOptionsWindow() {
		new OptionsWindow(f);
	}

	private static void openPaypal() {
		try {
			Desktop desktop = java.awt.Desktop.getDesktop();

			URI oURL = new URI(
					"https://www.paypal.com/us/cgi-bin/webscr?cmd=_flow&SESSION=v2Er1"
							+ "RgMWXZuAPevHUMjU5io9Q6ptsjwnw45RLgL0_ftS0ON5YIN6jcSZuK&dispatch=5"
							+ "0a222a57771920b6a3d7b606239e4d529b525e0b7e69bf0224adecfb0124e9b61f7"
							+ "37ba21b081988da7a3c03e3ee25661350b6a36dba24a");
			desktop.browse(oURL);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void createAccount() {
		JFrame parent = Frame.f;
		final JDialog createAW = new JDialog(parent, true);
		final JTextField fullName = new JTextField();
		fullName.setBounds(75, 10, 200, 25);
		createAW.add(fullName);
		final JLabel fullNameLabel = new JLabel("Full Name:");
		fullNameLabel.setBounds(10, 11, 100, 25);
		createAW.add(fullNameLabel);

		final JTextField username = new JTextField();
		username.setBounds(75, 40, 200, 25);
		createAW.add(username);
		final JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(9, 41, 100, 25);
		createAW.add(usernameLabel);

		final JPasswordField password = new JPasswordField();
		password.setBounds(75, 70, 200, 25);
		createAW.add(password);
		final JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 71, 100, 25);
		createAW.add(passwordLabel);

		final JTextField email = new JTextField();
		email.setBounds(75, 100, 200, 25);
		createAW.add(email);
		final JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(10, 101, 100, 25);
		createAW.add(emailLabel);

		final JButton paypal = new JButton("Pay With Paypal");
		paypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (email.getText().equals("") || fullName.getText().equals("")
						|| username.getText().equals("") || password.equals("")) {
					Dialogs.msgDialog("Please fill in every field!");
					return;
				}
				if (!email.getText().contains("@")
						|| !email.getText().contains(".")) {
					Dialogs.msgDialog("Please enter a valid email address!");
					return;
				}
				/**
				 * check for unique username
				 */
				createUsername = username.getText();
				Connection conn = new Connection(FILES.IPAddress, FILES.port,
						null, "uniqueUsername");
				if (!uniqueUsername) {
					Dialogs.msgDialog("Username is already in use! Please try again!");
					return;
				}

				createUsername = username.getText();
				createPassword = password.getText();
				createName = fullName.getText();
				createEmail = email.getText();
				Connection c = new Connection(FILES.IPAddress, FILES.port,
						null, "createAccount");
				openPaypal();
				createAW.dispose();
			}
		});
		paypal.setBounds(10, 130, 265, 50);
		createAW.add(paypal);

		createAW.setSize(300, 250);
		createAW.setLayout(null);
		createAW.setLocationRelativeTo(parent);
		createAW.setResizable(false);

		createAW.setVisible(true);
	}
}
