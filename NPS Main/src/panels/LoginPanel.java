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
		userField.setBounds(595, 20, 155, 25);
		passField.setBounds(595, 50, 155, 25);

		login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.login(userField.getText(), passField.getText());
			}
		});
		login.setBounds(755, 50, 80, 24);
		// if the server is offline, change the button
		Connection c = new Connection(FILES.IPAddress, FILES.port, null,
				"online");
		if (!Login.serverOnline) {
			login.setText("OFFLINE");
			login.setEnabled(false);
		}

		options = new JButton("Options");
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.showOptionsWindow();
			}
		});
		options.setBounds(755, 20, 80, 24);

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
