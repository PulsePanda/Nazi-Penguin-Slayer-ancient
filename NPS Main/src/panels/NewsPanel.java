package panels;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class NewsPanel extends JPanel {

	private int width, height;

	public NewsPanel(int w, int h) {
		width = w;
		height = h;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paint(Graphics g) {

	}
}
