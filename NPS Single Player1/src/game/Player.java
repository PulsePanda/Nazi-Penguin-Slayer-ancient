package game;

import io.FILES;

import java.awt.image.BufferedImage;

public class Player extends Sprite {

	public Player(BufferedImage img, int x, int y) {
		super(img, x, y);
		setVisible(true);
	}

	public void shoot() {
		// bullet = new Bullet(null, getX(), getY());
		bullet.add(new Bullet(io.getImage(FILES.bulletImage), getX(), getY()
				+ (getHeight() / 2), INITIAL_PLAYER_DAMAGE));
	}

	public void move() {
		if (!atEdge(playerMoveX, "x", FRAME_WIDTH)
				&& !atEdge(playerMoveX, "x", 200)) {
			moveX(playerMoveX);
		}

		// if (!(getX() >= 200)) {
		// moveX(playerMoveX);
		// }
		if (!atEdge(playerMoveY, "y", FRAME_HEIGHT))
			moveY(playerMoveY);
	}

	public void die() {
		System.out.println("Player had died!!!");
		stop();
	}
}
