package main.components.frames;

import java.awt.Component;

import javax.swing.JFrame;

import main.Core;
import main.io.IO;

public class Frame extends JFrame {

	IO io = new IO();
	public int w = 800;
	public int h = 600;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setVisible(true);
	}

	public void remove() {
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
		setTitle("Nazi Penguin Slayer   Day: " + Core.getWorld().getDay() + "  FPS: " + Core.getFPSCounter().getFPS());
	}
}
