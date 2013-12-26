package main.components.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Core;
import main.io.FILES;

public class PauseMenu extends Menu {

	private JButton resume, saveExit, options;
	private Core core = Core.getCore();

	public PauseMenu() {
		image = io.getImage(FILES.pauseOverlay);
		init();
		w = Core.frameW;
		h = Core.frameH;
		setupButtons();
	}

	public void setupButtons() {
		resume = new JButton("Resume");
		saveExit = new JButton("Save and Exit");
		options = new JButton("Options");

		// set up buttons
		int buttonW = 250, buttonH = 50;

		resume.setBounds((w / 2) - (buttonW / 2), (h / 2) - (buttonH * 3),
				buttonW, buttonH);
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});

		saveExit.setBounds((w / 2) - (buttonW / 2), (h / 2) - (buttonH / 4),
				buttonW, buttonH);
		saveExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAndExit();
			}
		});

		options.setBounds((w / 2) - (buttonW / 2), (h / 2) - (buttonH + 30),
				buttonW, buttonH);
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		// add buttons
		add(resume);
		add(saveExit);
		add(options);
	}

	public void saveAndExit() {
		if (Core.save())
			System.exit(0);
		else
			return;
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, w, h, null);

		// draw buttons
		resume.repaint();
		saveExit.repaint();
		options.repaint();
	}
}
