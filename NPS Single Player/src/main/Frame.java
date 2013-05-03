package main;

import java.awt.Component;

import javax.swing.JFrame;

public class Frame extends JFrame {

	IO io = new IO();
	int w = 800, h = 600;

	public Frame() {
		init();
	}

	public Frame(String title, String tbImg, int w, int h) {
		setTitle(title);
		if (tbImg != null)
			setIconImage(io.getImage(tbImg));
		this.w = w;
		this.h = h;
		init();
	}

	public void init() {
		setResizable(false);
		setSize(w, h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	public void delete() {
		this.dispose();
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public void refresh() {
		repaint();
		setTitle("Nazi Penguin Slayer   Day: " + Core.getDay());
	}
}
