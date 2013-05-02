import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame {

	IO io = new IO();

	private int width = 800, height = 600;
	private ImageIcon taskbarImg;

	public Frame() {
		setTitle("Frame");
		init();
	}

	public Frame(String title, String tbImg, int w, int h) {
		setTitle(title);
		if (tbImg != null)
			setIconImage(io.getImage(tbImg));
		width = w;
		height = h;
		init();
	}

	private void init() {
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void refresh() {
		this.repaint();
	}

	public void paint(Graphics g) {

	}
}
