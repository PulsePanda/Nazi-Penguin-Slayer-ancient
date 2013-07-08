package panels;

import io.IO;
import io.PlaySound;

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

public class MainScreenPanel extends Panel {

	JLabel singlePlayer, multiPlayer, options;
	IO io = new IO();

	public MainScreenPanel(int width, int height) {
		super(width, height);
		setLayout(null);
		addButtons();
	}

	public void addButtons() {
		singlePlayer = new JLabel();
		singlePlayer.setIcon(new ImageIcon(io
				.getImage(FILES.singlePlayerButton)));
		singlePlayer.setBounds(275, 175, 250, 50);
		singlePlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Frame.changePanel("SINGLEPLAYER");
				Frame.dp.changeImage(2);

				try {
					PlaySound.playSound(FILES.buttonClicked);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				singlePlayer.setIcon(new ImageIcon(io
						.getImage(FILES.singlePlayerRollover)));
			}

			public void mouseExited(MouseEvent e) {
				singlePlayer.setIcon(new ImageIcon(io
						.getImage(FILES.singlePlayerButton)));
			}
		});

		multiPlayer = new JLabel();
		multiPlayer
				.setIcon(new ImageIcon(io.getImage(FILES.multiPlayerButton)));
		multiPlayer.setBounds(275, 250, 250, 50);
		multiPlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Frame.changePanel("MULTIPLAYER");
				Frame.dp.changeImage(3);
				try {
					PlaySound.playSound(FILES.buttonClicked);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				multiPlayer.setIcon(new ImageIcon(io
						.getImage(FILES.multiPlayerRollover)));
			}

			public void mouseExited(MouseEvent e) {
				multiPlayer.setIcon(new ImageIcon(io
						.getImage(FILES.multiPlayerButton)));
			}
		});

		options = new JLabel();
		options.setIcon(new ImageIcon(io.getImage(FILES.optionButton)));
		options.setBounds(275, 325, 250, 50);
		options.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Frame.changePanel("OPTIONS");
				Frame.dp.changeImage(4);
				try {
					PlaySound.playSound(FILES.buttonClicked);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				options.setIcon(new ImageIcon(io.getImage(FILES.optionRollover)));
			}

			public void mouseExited(MouseEvent e) {
				options.setIcon(new ImageIcon(io.getImage(FILES.optionButton)));
			}
		});

		// add(exit);
		add(singlePlayer);
		add(multiPlayer);
		add(options);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(io.getImage(FILES.mainPanelBackgroundImage), 0, -40,
				Frame.getH(), Frame.getW(), null);
	}
}
