package main.components.panels;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.Core;
import main.io.IO;

public class Menu extends JPanel {
	protected int w, h;
	protected Core core = Core.getCore();
	protected static final IO io = new IO();
	protected Point point = new Point();
	protected String title = "";
	protected BufferedImage image;

	public Menu() {
		init();
	}

	public Menu(String t, int w, int h) {
		title = t;
		this.w = w;
		this.h = h;
		init();
	}

	public void init() {
		// w = image.getWidth();
		// h = image.getHeight();
		setSize(w, h);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					remove();
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				requestFocus();
			}
		});

		this.setFocusable(true);

		setVisible(false);
	}

	public void remove() {
		visible(false);
		core.getFrame().requestFocus();
	}

	public void setMoveable(boolean m) {
		if (m) {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					point.x = e.getX();
					point.y = e.getY();
				}
			});
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					Point p = getLocation();
					setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
				}
			});
		}
	}

	public void visible(boolean v) {
		setVisible(v);
		core.pause(v);
	}
}
