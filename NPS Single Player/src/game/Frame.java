package game;

import io.FILES;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame extends Core {

	public Frame() {
		frame.setSize(getWidth(), getHeight());
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setAlwaysOnTop(false);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.add(new GamePanel(getWidth(), getHeight()));
		frame.addKeyListener(new KeyClass());

		// sets the location of the window to top left of screen
		frame.setBounds(0, 0, getWidth(), getHeight());

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void setSelected() {
		frame.requestFocus();
	}

	public void disposeFrame() {
		frame.dispose();
	}

	public void refresh() {
		frame.setTitle("NPS " + FILES.VERSION + "    Day: " + getDay());
		frame.repaint();
	}
}
