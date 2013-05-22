package main;

import io.IO;
import io.PlaySound;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.*;

import files.FILES;

import panels.DragPanel;
import panels.MainScreenPanel;
import panels.MultiplayerPanel;
import panels.OptionPanel;
import panels.SinglePlayerPanel;

public class Frame extends JFrame {

	private static int height, width, posX, posY;
	private boolean resizable = false;
	private static CardLayout cl;
	private static JPanel cardPanel;
	public static DragPanel dp;
	private static IO io = new IO();
	private static Point point = new Point();
	public static JFrame f;
	public static final JLabel exit = new JLabel(new ImageIcon(
			io.getImage(FILES.exitButton)));
	public static final JLabel exitr = new JLabel(new ImageIcon(
			io.getImage(FILES.exitRollover)));
	public static final JLabel minimize = new JLabel(new ImageIcon(
			io.getImage(FILES.minimizeButton)));
	public static final JLabel minimizer = new JLabel(new ImageIcon(
			io.getImage(FILES.minimizeRollover)));

	public Frame(int height, int width, int posX, int posY, boolean resizable,
			JPanel panel, String title) {
		f = this;
		/*
		 * MAKE EVERYTHING
		 */
		this.height = height;
		this.width = width;
		this.posX = posX;
		this.posY = posY;
		this.resizable = resizable;
		cl = new CardLayout();
		cardPanel = new JPanel();

		// dp = new DragPanel(800, 40);
		makeButtons();

		/*
		 * set up dragging panel
		 */

		cardPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		cardPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});

		/*
		 * MAKE FRAME SETTINGS
		 */
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		setSize(this.height, this.width);
		setUndecorated(true);
		// if (this.posX == 0 || this.posY == 0) {
		// setLocationByPlatform(true);
		// } else {
		// setLocation(this.posX, this.posY);
		// }
		setLocationRelativeTo(null);
		setResizable(this.resizable);

		/*
		 * DOING ALL THE CARDLAYOUT STUFF NOW
		 */
		cardPanel.setLayout(cl);
		cardPanel.add(new MainScreenPanel(getW(), getH()), "MAINSCREEN");
		cardPanel.add(new SinglePlayerPanel(getW(), getH()), "SINGLEPLAYER");
		cardPanel.add(new MultiplayerPanel(getW(), getH()), "MULTIPLAYER");
		cardPanel.add(new OptionPanel(getW(), getH()), "OPTIONS");

		// add(dp, BorderLayout.NORTH);
		add(minimizer);
		add(minimize);
		add(exit);
		add(exitr);

		add(cardPanel);
		setIconImage(io.getImage(FILES.taskbarIcon));

		/*
		 * MAKE VISIBLE
		 */
		setVisible(true);

		setAlwaysOnTop(false);
		// setState(Frame.ICONIFIED);
	}

	public static void makeButtons() {
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
	}

	public static int getW() {
		return width;
	}

	public static int getH() {
		return height;
	}

	public static int getPosX() {
		return posX;
	}

	public static int getPosY() {
		return posY;
	}

	public static void setMinimized() {
		f.setState(Frame.ICONIFIED);
	}

	public static void changePanel(String panelName) {
		cl.show(cardPanel, "" + panelName);
	}

	public static JFrame getFrame() {
		return f;
	}

	public void disposeFrame() {
		this.dispose();
	}
}
