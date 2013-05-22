package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	IO io = new IO();
	Core core;

	public Panel() {
		core = Core.getCore();
	}

	public void paint(Graphics g) {
		// draw background image first
		g.drawImage(io.getImage(FILES.backgroundImage), 0, 0, Core.getFrame()
				.getWidth(), Core.getFrame().getHeight(), null);

		Tile[][] tiles = Core.getTiles();
		for (int i = 0; i < Core.getTileArrayWidth(); i++) {
			for (int a = 0; a < Core.getTileArrayHeight(); a++) {
				Tile tile = tiles[i][a];
				int x = i * tile.w + Core.getXOff();
				int y = a * tile.h + Core.getYOff();
				tile.setX(x);
				tile.setY(y);
				g.drawImage(tile.getImage(), tile.getX(), tile.getY(), tile.w,
						tile.h, null);
			}
		}

		// draw player
		Player player = Core.getPlayer();
		g.drawImage(player.getImage(), player.getX(), player.getY(),
				player.getW(), player.getH(), null);
//		System.out.println(player.getY());
	}
}
