package panels;

import java.awt.Graphics;
import java.io.File;

import files.FILES;
import io.IO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import actionListeners.BackButtonAction;
import actionListeners.ExitAction;
import actionListeners.SinglePlayerAction;
import actionListeners.SinglePlayerResumeAction;
import actionListeners.SinglePlayerStartAction;

import main.Frame;

public class SinglePlayerPanel extends JPanel {

	private int width, height;
	IO io = new IO();
	JButton resume, startNew, back, exit;

	public SinglePlayerPanel(int w, int h) {
		width = w;
		height = h;
		setLayout(null);
		setUpButtons();
	}

	private void setUpButtons() {
		resume = new JButton();
		startNew = new JButton();
		back = new JButton();

		// resume.setIcon(new ImageIcon(io
		// .getImage(FILES.singlePlayerResumeButton)));
		// resume.setRolloverIcon(new ImageIcon(io
		// .getImage(FILES.singlePlayerResumeRollover)));
		File f = new File("../worlds/world.dat");
		resume.setText("resume game");
		resume.setBounds(275, 175, 250, 50);
		resume.setBorderPainted(false);
		resume.addActionListener(new SinglePlayerResumeAction());
		if (!f.exists()) {
			resume.setEnabled(false);
		}

		startNew.setIcon(new ImageIcon(io
				.getImage(FILES.singlePlayerStartButton)));
		startNew.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.singlePlayerStartRollover)));
		startNew.setBounds(275, 250, 250, 50);
		startNew.setBorderPainted(false);
		startNew.addActionListener(new SinglePlayerStartAction());

		back.setIcon(new ImageIcon(io.getImage(FILES.backButton)));
		back.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.backButtonRollover)));
		back.setBounds(275, 325, 250, 50);
		back.setBorderPainted(false);
		back.addActionListener(new BackButtonAction());

		// exit = new JButton();
		// exit.setIcon(new ImageIcon(io.getImage(FILES.exitButton)));
		// exit.setBounds(750, 0, 25, 25);
		// exit.setBorderPainted(false);
		// exit.setRolloverIcon(new ImageIcon(io.getImage(FILES.exitRollover)));
		// exit.addActionListener(new ExitAction());

		// add(exit);
		add(resume);
		add(startNew);
		add(back);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(io.getImage(FILES.singlePlayerBackground), 0, 0,
				Frame.getH(), Frame.getW(), null);
	}
}
