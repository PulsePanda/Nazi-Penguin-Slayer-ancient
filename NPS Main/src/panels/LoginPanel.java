package panels;

import io.Connection;
import io.IO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Login;

import files.FILES;

public class LoginPanel extends Panel {

	IO io = new IO();

	private static JTextField userField = new JTextField();
	private static JPasswordField passField = new JPasswordField();
	private static String username, password;
	private static JButton login, options;

	public LoginPanel(int width, int height) {
		super(width, height);
		setLayout(null);
		create();
	}

	private void create() {
		userField.setBounds(550, 20, 155, 25);
		passField.setBounds(550, 50, 155, 25);

		login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.login(userField.getText(), passField.getText());
			}
		});
		login.setBounds(710, 50, 125, 24);
		// if the server is offline, change the button
		Connection c = new Connection(FILES.IPAddress, FILES.port, null,
				"online");
		if (!Login.serverOnline) {
			login.setText("OFFLINE");
			login.setEnabled(false);
		}

		options = new JButton("Create Account");
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Login.showOptionsWindow();
				Login.createAccount();
			}
		});
		options.setBounds(710, 20, 125, 24);
		if (!Login.serverOnline) {
			options.setEnabled(false);
		}

		add(userField);
		add(passField);
		add(login);
		add(options);
	}

	public void paint(Graphics g) {
		// g.drawImage(io.getImage(FILES.loginBackground), 0, 0, width, height,
		// null);

		g.setColor(Color.white);
		g.drawString("Username:", 520, 35);
		g.drawString("Password:", 523, 65);
		userField.repaint();
		passField.repaint();
		login.repaint();
		options.repaint();
	}
}
