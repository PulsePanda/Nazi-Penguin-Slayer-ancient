package main;

import java.awt.image.BufferedImage;

public class Tile {
	String img;
	BufferedImage image;
	int x, y, w = 50, h = 50;
	IO io = new IO();

	public Tile(String imgDir) {
		img = imgDir;
		image = io.getImage(img);
	}

	public BufferedImage getImage() {
		return image;
	}
}
