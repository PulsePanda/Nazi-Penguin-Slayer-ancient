package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

	private static BufferedImage img;
	private static String imgDir = FILES.playerImage, facing = "right";
	private static IO io = new IO();
	private static int x = 0, y = 0, w = 20, h = w, baseMoveSpeed = 10,
			moveSpeed = baseMoveSpeed, moveX = 0, moveY = 0, jumpHeight = 40,
			fallSpeed = 4;
	private static boolean visible = true, allowGravity = true,
			falling = false, ableToMove = true;
	private static Core core;

	public Player() {
		img = io.getImage(imgDir);
		core = Core.getCore();
	}

	public BufferedImage getImage() {
		return img;
	}

	public void move() {
		if (!ableToMove)
			return;
		x += moveX;
		y += moveY;
	}

	public void applyGravity() {
		if (!allowGravity)
			return;

		if (isColliding()) {
			moveY = 0;
			falling = false;
			moveSpeed = baseMoveSpeed;
			return;
		}

		falling = true;
		moveY = fallSpeed;
		move();
	}

	public boolean isFalling() {
		return falling;
	}

	public void jump() {
		allowGravity = false;
		// do the jump
		y -= jumpHeight;

		allowGravity = true;
	}

	public void setMoveX(int x) {
		moveX = x;
	}

	public void setMoveY(int y) {
		moveY = y;
	}

	public boolean isColliding() {
		ArrayList<Tile> list = Core.getList();
		for (int i = 0; i < list.size(); i++) {
			Tile tile = (Tile) list.get(i);
			if (getBounds().intersects(tile.getBounds()))
				return true;
		}
		return false;
	}

	public boolean isCollidingMovingRight() {
		return false;
	}

	public boolean isCollidingMovingLeft() {
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
