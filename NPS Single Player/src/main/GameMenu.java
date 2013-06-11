package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMenu extends JPanel {

	private int w = 400, h = 300;
	private Core core = Core.getCore();
	private static final IO io = new IO();
	private Point point = new Point();
	private String title = "";

	public GameMenu(String t) {
		title = t;
		init();
	}

	public GameMenu(String t, int w, int h) {
		title = t;
		this.w = w;
		this.h = h;
		init();
	}

	public void init() {
		setSize(w, h);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
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

		this.setFocusable(true);

		setVisible(false);
	}

	public void remove() {
		setVisible(false);
		core.getFrame().requestFocus();
	}

	public void setMoveable() {
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

	public void paint(Graphics g) {
		g.drawImage(io.getImage(FILES.ingameMenuBackground), 0, 0, w, h, null);
		g.setColor(Color.white);

		/**
		 * add a change to the title font and size here
		 */

		int titleWidth = title.length(), stringX = (w / 2) - (titleWidth / 2);
		g.drawString(title, stringX, 10);
	}
}
