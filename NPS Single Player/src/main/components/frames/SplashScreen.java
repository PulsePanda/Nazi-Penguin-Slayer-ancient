package main.components.frames;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.io.FILES;

public class SplashScreen {

	// JFrame f = new JFrame();
	JFrame frame = new JFrame();

	public SplashScreen() {
		final JLabel label = new JLabel(new ImageIcon(getImage(FILES.splashScreen)));

		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		// Without this, the window is draggable from any non transparent
		// point, including points inside textboxes.
		frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

		// frame.getContentPane().setLayout(new java.awt.BorderLayout());
		// frame.getContentPane().add(label, java.awt.BorderLayout.NORTH);
		frame.add(label);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public BufferedImage getImage(String dir) {
		BufferedImage img = null;
		File dirFile = new File(dir);
		try {
			img = ImageIO.read(dirFile);
		} catch (IOException e) {
		}
		return img;
	}

	public void remove() {
		frame.dispose();
	}
}
