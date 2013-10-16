package main.components.panels;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import main.Core;
import main.io.IO;

public class Menu extends JPanel {
	protected int w = 400, h = 300;
	protected Core core = Core.getCore();
	protected static final IO io = new IO();
	protected Point point = new Point();
	protected String title = "";
	protected boolean exitable = true;

	public Menu(String t, boolean exitable) {
		title = t;
		this.exitable = exitable;
		init();
	}

	public Menu(String t, int w, int h, boolean exitable) {
		title = t;
		this.exitable = exitable;
		this.w = w;
		this.h = h;
		init();
	}

	public void init() {
		setSize(w, h);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		if (exitable) {
			addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						remove();
					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
				}
			});

			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					requestFocus();
				}
			});
		}

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
					setLocation(p.x + e.getX() - point.x, p.y + e.getY()
							- point.y);
				}
			});
		}
	}

	public void visible(boolean v) {
		setVisible(v);
		core.pause(v);
	}
}
