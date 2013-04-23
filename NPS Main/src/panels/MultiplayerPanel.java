package panels;

import io.IO;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import actionListeners.BackButtonAction;
import actionListeners.ExitAction;
import actionListeners.HostButtonAction;
import actionListeners.JoinButtonAction;
import actionListeners.SinglePlayerAction;

import main.Frame;
import files.FILES;

public class MultiplayerPanel extends JPanel {

	private int width, height;
	IO io = new IO();
	JButton host, join, back, exit;

	public MultiplayerPanel(int w, int h) {
		width = w;
		height = h;
		setLayout(null);
		addButtons();
	}

	private void addButtons() {
		host = new JButton();
		join = new JButton();
		back = new JButton();

		host.setIcon(new ImageIcon(io.getImage(FILES.multiplayerHostButton)));
		host.setBounds(275, 175, 250, 50);
		host.setBorderPainted(false);
		host.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.multiplayerHostRollover)));
		host.addActionListener(new HostButtonAction());

		join.setIcon(new ImageIcon(io.getImage(FILES.multiplayerJoinButton)));
		join.setBounds(275, 250, 250, 50);
		join.setBorderPainted(false);
		join.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.mulitplayerJoinRollover)));
		join.addActionListener(new JoinButtonAction());

		back.setIcon(new ImageIcon(io.getImage(FILES.backButton)));
		back.setBounds(275, 325, 250, 50);
		back.setBorderPainted(false);
		back.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.backButtonRollover)));
		back.addActionListener(new BackButtonAction());

//		exit = new JButton();
//		exit.setIcon(new ImageIcon(io.getImage(FILES.exitButton)));
//		exit.setBounds(750, 0, 25, 25);
//		exit.setBorderPainted(false);
//		exit.setRolloverIcon(new ImageIcon(io.getImage(FILES.exitRollover)));
//		exit.addActionListener(new ExitAction());

//		add(exit);
		add(host);
		add(join);
		add(back);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(io.getImage(FILES.multiplayerBackground), 0, -40,
				Frame.getH(), Frame.getW(), null);
	}

}
