/**
 * tile codes:
 * 0: nothing
 * 1: grass
 * 2: dirt
 * 3: stone
 */

package main;

import java.awt.image.BufferedImage;

public class Tile {
	String img;
	int ID;
	BufferedImage image;
	int x, y, w = 50, h = 50;
	IO io = new IO();

	public Tile(int ID) {
		this.ID = ID;
		if (ID != 0) {
			img = getImageString(ID);
			image = io.getImage(img);
		} else {
			image = null;
			img = null;
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getImageString(int ID) {
		switch (ID) {
		case 0:
			return null;
		case 1:
			return FILES.grassBlock;
		case 2:
			return FILES.dirtBlock;
		case 3:
			return FILES.stoneBlock;
		default:
			return null;
		}
	}
}
