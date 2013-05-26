package panels;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LoginPanel extends JPanel {

	private int width, height;

	public LoginPanel(int w, int h) {
		width = w;
		height = h;
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paint(Graphics g) {

	}
}
