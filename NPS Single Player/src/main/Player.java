package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

	private static BufferedImage img;
	private static String imgDir = FILES.playerImage, facing = "right";
	private static IO io = new IO();
	private static int x = 0, y = 0, w = 20, h = 20, moveSpeed = 15, moveX = 0,
			moveY = 0;
	private static boolean visible = true;
	private static Core core;

	public Player() {
		img = io.getImage(imgDir);
		core = Core.getCore();
	}

	public BufferedImage getImage() {
		return img;
	}

	public void move() {
		x += moveX;
		y += moveY;
	}

	public void applyGravity() {
		if (isColliding()) {
			moveY = 0;
			return;
		}
		setMoveX(0);
		moveY = 1;
		move();
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

	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
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

	public int getMoveSpeed() {
		return moveSpeed;
	}
}
