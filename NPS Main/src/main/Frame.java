package main;

import io.IO;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

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
	private IO io = new IO();
	private static Point point = new Point();
	public static JFrame f;

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

		dp = new DragPanel(800, 40);

		/*
		 * set up dragging panel
		 */

		dp.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		dp.addMouseMotionListener(new MouseMotionAdapter() {
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

		add(dp, BorderLayout.NORTH);
		add(cardPanel);

		setIconImage(io.getImage(FILES.taskbarIcon));

		/*
		 * MAKE VISIBLE
		 */
		setVisible(true);

		setAlwaysOnTop(false);
		// setState(Frame.ICONIFIED);
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

	public void disposeFrame() {
		this.dispose();
	}
}
