package sprites;

import game.Core;
import io.FILES;
import io.IO;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Block implements Serializable {

	/**
	 * BLOCK KEY: 0 = nothing. 1 = grass block 2 = dirt block 3 = stone block 4
	 * = wood plank block
	 */
	static int w = 5, h = w;
	int blockCode;

	public Block(int blockCode) {
		this.blockCode = blockCode;

	}

	public int getID() {
		return blockCode;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public static BufferedImage getImage(int ID) {
		BufferedImage image = null;

		switch (ID) {
		case 0:
			return null;
		case 1:
			return Core.io.getImage(FILES.grassBlockImage);
		case 2:
			return Core.io.getImage(FILES.dirtBlockImage);
		case 3:
			return Core.io.getImage(FILES.stoneBlockImage);
		case 4:
			return Core.io.getImage(FILES.woodPlankBlockImage);
		default:
			return null;
		}
	}
}
