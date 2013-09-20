package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

	private static BufferedImage img;
	private static String imgDir = FILES.playerImage, facing = "right";
	private static IO io = new IO();
	public static int x = 493, y = 20, w = 20, h = w, baseMoveSpeed = 5,
			currentMoveSpeed = baseMoveSpeed, moveX = 0, moveY = 0,
			jumpHeight = 36, fallSpeed = -3;
	private static boolean visible = true, falling = false, ableToMove = true,
			jumping = false;
	private static Core core;

	public Player() {
		img = io.getImage(imgDir);
		core = Core.getCore();
	}

	public BufferedImage getImage() {
		return img;
	}

	public void move() {
		core = Core.getCore();
		if (!ableToMove)
			return;
		core.setTileGroupXOff(moveX);
		core.setPlayerYOff(moveY);
	}

	public void applyGravity() {
		if (jumping)
			return;

		if (isCollidingBottom()) {
			moveY = 0;
			falling = false;
			return;
		}

		falling = true;
		setMoveY(fallSpeed);
	}

	public boolean isFalling() {
		return falling;
	}

	public void jump() {
		if (isFalling())
			return;
		jumping = true;

		/**
		 * the loop to jump. for some reason the stupid graphics wont update
		 * till its done tho... NOTE: i found that the paint method in the panel
		 * actually doesnt get the new and improved y....
		 */
		for (int i = 8; i > 0; i--) {
			core.setPlayerYOff(i);
		}
		jumping = false;
		falling = true;
	}

	public void setMoveX(int x) {
		moveX = x;
	}

	public void setMoveY(int y) {
		moveY = y;
	}

	public boolean isCollidingBottom() {
		ArrayList<Tile> tiles = Core.getList();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = (Tile) tiles.get(i);
			if (getBounds().intersects(tile.getBounds()))
				return true;
		}
		return false;
	}

	public boolean isCollidingRight() {
		ArrayList<Tile> tiles = Core.getList();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = (Tile) tiles.get(i);

		}
		return false;
	}

	public boolean isCollidingLeft() {
		ArrayList<Tile> tiles = Core.getList();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = (Tile) tiles.get(i);

		}
		return false;
	}

	public boolean isCollidingTop() {
		ArrayList<Tile> tiles = Core.getList();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = (Tile) tiles.get(i);

		}
		return false;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

	public Rectangle getRightBounds() {
		return new Rectangle(x + w - 1, y + 1, 1, h - 2);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle(x + 1, y + 1, 1, h - 2);
	}

	public boolean isVisible() {
		return visible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getBaseMoveSpeed() {
		return baseMoveSpeed;
	}

	public int getMoveSpeed() {
		return currentMoveSpeed;
	}
}
