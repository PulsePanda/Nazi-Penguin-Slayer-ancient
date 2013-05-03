package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	public Panel() {

	}

	public void paint(Graphics g) {
		Tile[][] tiles = Core.getTiles();
		for (int i = 0; i < Core.getTileArrayWidth(); i++) {
			Tile tile = tiles[i][0];
			g.drawImage(tile.getImage(), 0 + (i * tile.w), 0, tile.w, tile.h,
					null);
		}
	}
}
