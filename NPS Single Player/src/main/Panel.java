package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	public Panel() {

	}

	public void paint(Graphics g) {
		Tile[][] tiles = Core.getTiles();
		for (int i = 0; i < Core.getTileArrayWidth(); i++) {
			for (int a = 0; a < Core.getTileArrayHeight(); a++) {
				Tile tile = tiles[i][a];
				g.drawImage(tile.getImage(), 0 + (i * tile.w),
						0 + (a * tile.h), tile.w, tile.h, null);
			}
		}
	}
}
