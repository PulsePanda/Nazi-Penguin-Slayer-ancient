package panels;

import io.IO;
import io.PlaySound;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actionListeners.ExitAction;

import main.Dialogs;
import main.Frame;
import files.FILES;

public class DragPanel extends Panel {

	int image = 1;
	IO io = new IO();

	public DragPanel(int width, int height) {
		super(width, height);

		setLayout(null);

		final JLabel exit = new JLabel(new ImageIcon(
				io.getImage(FILES.exitButton)));
		final JLabel exitr = new JLabel(new ImageIcon(
				io.getImage(FILES.exitRollover)));
		exitr.setVisible(false);

		exit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				exit.setVisible(false);
				exitr.setVisible(true);
			}
		});

		exitr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Properties p = new Properties();
					p.load(new FileInputStream("properties.properties"));
					p.setProperty("running", "false");
					FILES.saveProperties(p);

					PlaySound.playSound(FILES.buttonClicked);
					System.exit(0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent arg0) {
				exitr.setVisible(false);
				exit.setVisible(true);
			}
		});

		final JLabel minimize = new JLabel(new ImageIcon(
				io.getImage(FILES.minimizeButton)));
		final JLabel minimizer = new JLabel(new ImageIcon(
				io.getImage(FILES.minimizeRollover)));

		minimizer.setVisible(false);

		minimize.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				minimize.setVisible(false);
				minimizer.setVisible(true);
			}
		});

		minimizer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((Frame) Frame.f).setMinimized();
			}

			public void mouseExited(MouseEvent e) {
				minimizer.setVisible(false);
				minimize.setVisible(true);
			}
		});

		exitr.setBounds(750, 5, 30, 30);
		exit.setBounds(750, 5, 30, 30);
		minimizer.setBounds(710, 5, 30, 30);
		minimize.setBounds(710, 5, 30, 30);
		add(minimizer);
		add(minimize);
		add(exit);
		add(exitr);
	}

	public void changeImage(int i) {
		image = i;
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		switch (image) {
		case 1:
			g.drawImage(io.getImage(FILES.mainPanelBackgroundImage), 0, 0, 800,
					500, null);
			break;
		case 2:
			g.drawImage(io.getImage(FILES.singlePlayerBackground), 0, 0, 800,
					500, null);
			break;
		case 3:
			g.drawImage(io.getImage(FILES.multiplayerBackground), 0, 0, 800,
					500, null);
			break;
		case 4:
			g.drawImage(io.getImage(FILES.optionBackground), 0, 0, 800, 500,
					null);
			break;
		}
		g.drawString("NPS " + FILES.version + "            AVTECH Software",
				15, 15);
	}
}
