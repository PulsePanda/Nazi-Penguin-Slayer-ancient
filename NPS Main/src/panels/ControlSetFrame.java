package panels;

import io.IO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import files.FILES;

import main.Start;

public class ControlSetFrame extends JFrame {
	IO io = new IO();
	String button;
	int w = 425, h = 225;

	public ControlSetFrame(String s) {
		button = s;

		setSize(w, h);
		setUndecorated(true);
		setResizable(false);
		setLocation((int) (Start.mf.getX() + (0.25 * Start.mf.getWidth())),
				(int) (Start.mf.getY() + (0.25 * Start.mf.getHeight())));
		setAlwaysOnTop(true);
		setLayout(null);
		addKeyListener(new ControlKeyClass(button, this));
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawImage(io.getImage(FILES.controlWindowBackgroundButton), 0, 0,
				425, 225, null);
	}

}
