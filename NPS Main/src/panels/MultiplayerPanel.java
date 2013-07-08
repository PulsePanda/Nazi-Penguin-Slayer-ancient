package panels;

import io.IO;
import io.PlaySound;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actionListeners.BackButtonAction;
import actionListeners.ExitAction;
import actionListeners.HostButtonAction;
import actionListeners.JoinButtonAction;
import actionListeners.SinglePlayerAction;

import main.Frame;
import files.FILES;

public class MultiplayerPanel extends Panel {

	IO io = new IO();
	JLabel host, join, back, exit;

	public MultiplayerPanel(int w, int h) {
		super(w, h);
		setLayout(null);
		addButtons();
	}

	private void addButtons() {
		host = new JLabel();
		join = new JLabel();
		back = new JLabel();

		host.setIcon(new ImageIcon(io.getImage(FILES.multiplayerHostButton)));
		host.setBounds(275, 175, 250, 50);
		host.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					PlaySound.playSound(FILES.buttonClicked);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Desktop desktop = Desktop.getDesktop();
					File openFile = new File("NPS Multi Host.jar");
					desktop.open(openFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				host.setIcon(new ImageIcon(io
						.getImage(FILES.multiplayerHostRollover)));
			}

			public void mouseExited(MouseEvent e) {
				host.setIcon(new ImageIcon(io
						.getImage(FILES.multiplayerHostButton)));
			}
		});

		join.setIcon(new ImageIcon(io.getImage(FILES.multiplayerJoinButton)));
		join.setBounds(275, 250, 250, 50);
		join.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					PlaySound.playSound(FILES.buttonClicked);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Desktop desktop = Desktop.getDesktop();
					File openFile = new File("NPS Multi Join.jar");
					desktop.open(openFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				join.setIcon(new ImageIcon(io
						.getImage(FILES.multiplayerJoinRollover)));
			}

			public void mouseExited(MouseEvent e) {
				join.setIcon(new ImageIcon(io
						.getImage(FILES.multiplayerJoinButton)));
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
