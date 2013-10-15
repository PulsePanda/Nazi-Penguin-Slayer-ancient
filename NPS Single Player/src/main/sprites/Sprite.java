package main.sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Core;
import main.io.IO;

public class Sprite {
	private static BufferedImage img;
	private static String imgDir, facing = "right";
	private static IO io = new IO();
	public static int x, y, w, h, baseMoveSpeed = 5,
			currentMoveSpeed = baseMoveSpeed, moveX = 0, moveY = 0,
			jumpHeight = 36, fallSpeed = -3;
	private static boolean visible = true;
	protected static boolean falling = false;
	protected static boolean ableToMove = true;
	protected static boolean jumping = false;
	protected static Core core;

	public Sprite(String imgDir, int x, int y, int w, int h) {
		this.imgDir = imgDir;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		img = io.getImage(this.imgDir);
		core = Core.getCore();
	}

	public BufferedImage getImage() {
		return img;
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
			if (getBottomBounds().intersects(tile.getBounds()))
				return true;
		}
		return false;
	}

	public boolean isCollidingRight() {
		ArrayList<Tile> tiles = Core.getList();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = (Tile) tiles.get(i);
			if (getRightBounds().intersects(tile.getBounds()))
				return true;
		}
		return false;
	}

	public boolean isCollidingLeft() {
		ArrayList<Tile> tiles = Core.getList();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = (Tile) tiles.get(i);
			if (getLeftBounds().intersects(tile.getBounds()))
				return true;
		}
		return false;
	}

	public boolean isCollidingTop() {
		ArrayList<Tile> tiles = Core.getList();
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = (Tile) tiles.get(i);
			if (getTopBounds().intersects(tile.getBounds()))
				return true;
		}
		return false;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

	public Rectangle getRightBounds() {
		return new Rectangle(x + w - 1, y + getH() / 2, 1, 1);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle(x, y + getH() / 2, 1, 1);
	}

	public Rectangle getBottomBounds() {
		return new Rectangle(x + getW() / 2, y + getH(), 1, 1);
	}

	public Rectangle getTopBounds() {
		return new Rectangle(x + getW() / 2, y, 1, 1);
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
