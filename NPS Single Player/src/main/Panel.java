package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	IO io = new IO();

	public Panel() {

	}

	public void paint(Graphics g) {
		// draw background image first
		g.drawImage(io.getImage(FILES.backgroundImage), 0, 0, Core.getFrame()
				.getWidth(), Core.getFrame().getHeight(), null);

		Tile[][] tiles = Core.getTiles();
		for (int i = 0; i < Core.getTileArrayWidth(); i++) {
			for (int a = 0; a < Core.getTileArrayHeight(); a++) {
				Tile tile = tiles[i][a];
				int x = i * tile.w, y = a * tile.h;
				tile.setX(x);
				tile.setY(y);
				g.drawImage(tile.getImage(), 0 + (i * tile.w),
						0 + (a * tile.h), tile.w, tile.h, null);
			}
		}
	}
}
