package panels;

import io.IO;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import actionListeners.ExitAction;
import actionListeners.MultiPlayerAction;
import actionListeners.OptionAction;
import actionListeners.SinglePlayerAction;

import files.FILES;

import main.Dialogs;
import main.Frame;

public class MainScreenPanel extends JPanel {

	int width, height;
	JButton singlePlayer, multiPlayer, options, exit;
	IO io = new IO();

	public MainScreenPanel() {
	}

	public MainScreenPanel(int width, int height) {
		this.width = width;
		this.height = height;
		setLayout(null);
		addButtons();
	}

	public void addButtons() {
		singlePlayer = new JButton();
		singlePlayer.setIcon(new ImageIcon(io
				.getImage(FILES.singlePlayerButton)));
		singlePlayer.setBounds(275, 175, 250, 50);
		singlePlayer.setBorderPainted(false);
		singlePlayer.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.singlePlayerRollover)));
		singlePlayer.addActionListener(new SinglePlayerAction());

		multiPlayer = new JButton();
		multiPlayer
				.setIcon(new ImageIcon(io.getImage(FILES.multiPlayerButton)));
		multiPlayer.setBounds(275, 250, 250, 50);
		multiPlayer.setBorderPainted(false);
		multiPlayer.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.multiPlayerRollover)));
		multiPlayer.addActionListener(new MultiPlayerAction());

		options = new JButton();
		options.setIcon(new ImageIcon(io.getImage(FILES.optionButton)));
		options.setBounds(275, 325, 250, 50);
		options.setBorderPainted(false);
		options.setRolloverIcon(new ImageIcon(io.getImage(FILES.optionRollover)));
		options.addActionListener(new OptionAction());

		exit = new JButton();
		exit.setIcon(new ImageIcon(io.getImage(FILES.exitButton)));
		exit.setBounds(750, 0, 25, 25);
		exit.setBorderPainted(false);
		exit.setRolloverIcon(new ImageIcon(io.getImage(FILES.exitRollover)));
		exit.addActionListener(new ExitAction());

		

		// add(exit);
		add(singlePlayer);
		add(multiPlayer);
		add(options);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(io.getImage(FILES.mainPanelBackgroundImage), 0, -40,
				Frame.getH(), Frame.getW(), null);
	}
}
