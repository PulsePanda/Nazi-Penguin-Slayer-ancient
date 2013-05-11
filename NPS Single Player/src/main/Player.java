package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

	private static BufferedImage img;
	private static String imgDir = FILES.playerImage;
	private static IO io = new IO();
	private static int x = 0, y = 0, w = 25, h = 25, moveSpeed = 6;
	private static boolean visible = true;
	private static Core core;

	public Player() {
		img = io.getImage(imgDir);
		core = Core.getCore();
	}

	public BufferedImage getImage() {
		return img;
	}

	public void move(int adjustX, int adjustY) {
		x += adjustX;
		y += adjustY;
	}

	public void applyGravity() {
		if (isColliding())
			return;
		move(0, moveSpeed);
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
}
