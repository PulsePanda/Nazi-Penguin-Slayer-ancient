package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

	private static BufferedImage img;
	private static String imgDir = FILES.playerImage, facing = "right";
	private static IO io = new IO();
	private static int x = 493, y = 250, w = 20, h = w, baseMoveSpeed = 5,
			moveSpeed = baseMoveSpeed, moveX = 0, moveY = 0, jumpHeight = 20,
			fallSpeed = -5;
	private static boolean visible = true, allowGravity = true,
			falling = false, ableToMove = true, jumping = false;
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
		core.setYOff(moveY);
		core.setXOff(moveX);
	}

	public void applyGravity() {
		if (!allowGravity)
			return;

		if (isCollidingBottom()) {
			moveY = 0;
			falling = false;
			jumping = false;
			moveSpeed = baseMoveSpeed;
			return;
		}

		falling = true;
		moveY = fallSpeed;
		// move();
	}

	public boolean isFalling() {
		return falling;
	}

	public void jump() {
		if (jumping)
			return;
		jumping = true;
		allowGravity = false;

		/**
		 * the loop to jump. for some reason the stupid graphics wont update
		 * till its done tho... NOTE: i found that the paint method in the panel
		 * actually doesnt get the new and improved y.... which begs the
		 * question, am i updating the right thing? NOTE: if i take out the
		 * thread pause, no jump occurs... but if i make it 1, the jump is
		 * tiny... so we have a huge problem
		 */
		for (int i = jumpHeight; i > 0; i--) {
			setMoveY(i);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		allowGravity = true;
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
		return moveSpeed;
	}
}
