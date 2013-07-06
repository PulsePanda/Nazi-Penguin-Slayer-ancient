package panels;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Login;

public class OptionsWindow {
	private static JFrame parent;
	private static JButton createAccount;

	public OptionsWindow(JFrame frame) {
		parent = frame;
		init();
	}

	private void init() {
		final JDialog dialog = new JDialog(parent, true);
		dialog.setLayout(null);
		dialog.setSize(400, 220);
		dialog.setLocationRelativeTo(parent);

		createAccount = new JButton("Create Account");
		createAccount.setBounds(71, 5, 250, 50);
		createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.createAccount();
				dialog.dispose();
			}
		});
		if (!Login.serverOnline) {
			createAccount.setEnabled(false);
		}
		dialog.add(createAccount);

		dialog.setVisible(true);
	}

	private void addThings() {

	}
}
