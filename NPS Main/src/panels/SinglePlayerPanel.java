package panels;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import files.FILES;
import io.IO;
import io.PlaySound;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actionListeners.BackButtonAction;
import actionListeners.ExitAction;
import actionListeners.SinglePlayerAction;
import actionListeners.SinglePlayerResumeAction;
import actionListeners.SinglePlayerStartAction;

import main.Dialogs;
import main.Frame;

public class SinglePlayerPanel extends Panel {

	IO io = new IO();
	JLabel resume, startNew, back, exit;

	public SinglePlayerPanel(int w, int h) {
		super(w, h);
		setLayout(null);
		setUpButtons();
	}

	private void setUpButtons() {
		resume = new JLabel();
		startNew = new JLabel();
		back = new JLabel();

		File f = new File("../worlds/world.dat");
		resume.setText("resume game");
		resume.setBounds(275, 175, 250, 50);
		resume.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					PlaySound.playSound(FILES.buttonClicked);
					Desktop desktop = Desktop.getDesktop();
					File openFile = new File("NPS Single Player.jar");
					desktop.open(openFile);
					System.exit(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			// public void mouseEntered(MouseEvent e) {
			// resume.setIcon(new ImageIcon(io
			// .getImage(FILES.singlePlayerResumeRollover)));
			// }
			//
			// public void mouseExited(MouseEvent e) {
			// resume.setIcon(new ImageIcon(io
			// .getImage(FILES.singlePlayerResumeButton)));
			// }
		});
		if (!f.exists()) {
			resume.setEnabled(false);
		}

		startNew.setIcon(new ImageIcon(io
				.getImage(FILES.singlePlayerStartButton)));
		startNew.setBounds(275, 250, 250, 50);
		startNew.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// GameCore.start();
				/**
				 * delete the current world...
				 */
				File f = new File("../worlds/world.dat");
				if (f.exists())
					if (!f.delete())
						Dialogs.errorDiagExit("Could not start a new world! Please try again or check "
								+ "the FAQ");
				/**
				 * start the single player jar file, and close this program
				 */
				try {
					PlaySound.playSound(FILES.buttonClicked);
					Desktop desktop = Desktop.getDesktop();
					File openFile = new File("NPS Single Player.jar");
					desktop.open(openFile);
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				startNew.setIcon(new ImageIcon(io
						.getImage(FILES.singlePlayerStartRollover)));
			}

			public void mouseExited(MouseEvent e) {
				startNew.setIcon(new ImageIcon(io
						.getImage(FILES.singlePlayerStartButton)));
			}
		});

		back.setIcon(new ImageIcon(io.getImage(FILES.backButton)));
		back.setBounds(275, 325, 250, 50);
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Frame.changePanel("MAINSCREEN");
				Frame.dp.changeImage(1);
				try {
					PlaySound.playSound(FILES.buttonClicked);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				back.setIcon(new ImageIcon(io
						.getImage(FILES.backButtonRollover)));
			}

			public void mouseExited(MouseEvent e) {
				back.setIcon(new ImageIcon(io.getImage(FILES.backButton)));
			}
		});

		add(resume);
		add(startNew);
		add(back);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(io.getImage(FILES.singlePlayerBackground), 0, -40,
				Frame.getH(), Frame.getW(), null);
	}
}
