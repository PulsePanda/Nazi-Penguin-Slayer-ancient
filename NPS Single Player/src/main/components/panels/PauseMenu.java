package main.components.panels;

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

import main.io.FILES;

public class PauseMenu extends Menu {

	public PauseMenu(String t) {
		super(t);
	}

	public PauseMenu(String t, int w, int h) {
		super(t, w, h);
	}

	public void paint(Graphics g) {
		g.drawImage(io.getImage(FILES.ingameMenuBackground), 0, 0, w, h, null);
		g.setColor(Color.white);

		/**
		 * add a change to the title font and size here
		 */

		int titleWidth = title.length(), stringX = (w / 2) - (titleWidth / 2);
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.setColor(Color.white);
		g.drawString(title, stringX, 20);
	}
}
