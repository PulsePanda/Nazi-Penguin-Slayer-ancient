package main.components.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.Core;
import main.components.frames.Frame;
import main.io.FILES;
import main.io.IO;
import main.sprites.Player;
import main.sprites.Tile;

public class Panel extends JPanel {
	static IO io = new IO();
	Core core;
	private static BufferedImage backgroundImage;
	private static BufferedImage invImage;

	public Panel() {
		core = Core.getCore();
		backgroundImage = io.getImage(FILES.backgroundImage);
		invImage = io.getImage("C:\\Users\\Austin\\Desktop\\inv.png");
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				core.getFrame().requestFocus();
			}
		});
	}

	public void checkDraw(Tile tile, Graphics g) {
		Frame frame = core.getFrame();
		int leftBound = 0 - tile.w, rightBound = frame.w + tile.w, topBound = 0 - tile.h, botBound = frame.h
				+ tile.h;

		if (tile.getX() < leftBound || tile.getX() > rightBound
				|| tile.getY() < topBound || tile.getY() > botBound) {
			return;
		}

		g.drawImage(tile.getImage(), tile.getX(), tile.getY(), tile.w, tile.h,
				null);
	}

	public void paint(Graphics g) {
		// draw background image first
		g.drawImage(backgroundImage, 0, 0, Core.getFrame().getWidth(), Core
				.getFrame().getHeight(), null);

		// work with the tiles
		Tile[][] tiles = Core.getTiles();
		for (int i = 0; i < Core.getTileArrayWidth(); i++) {
			for (int a = 0; a < Core.getTileArrayHeight(); a++) {
				Tile tile = tiles[i][a];
				int x = i * tile.w + Core.getXOff();
				int y = a * tile.h + Core.getYOff();
				tile.setX(x);
				tile.setY(y);
				checkDraw(tile, g);
			}
		}

		// draw player
		Player player = Core.getPlayer();
		g.drawImage(player.getImage(), player.getX(), player.getY(),
				player.getW(), player.getH(), null);
	}
}
