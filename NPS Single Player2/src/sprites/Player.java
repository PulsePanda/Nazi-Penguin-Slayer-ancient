package sprites;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Player extends Sprite implements Serializable {
	public Player(BufferedImage img, int x, int y, int width, int height,
			double speed, int maxHealth, double damage) {
		super(img, x, y, width, height, speed, maxHealth, damage);

	}
}
