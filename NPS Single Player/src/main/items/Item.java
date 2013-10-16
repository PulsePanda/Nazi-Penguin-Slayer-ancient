package main.items;

import java.awt.image.BufferedImage;

import main.io.IO;

public class Item {

	/**
	 * all the default item id's that are usable
	 */
	public static final int GLOCK = 0;
	public static final int SNIPER = 1;

	private String imgDir;
	private int itemID;
	private BufferedImage img;
	private IO io = new IO();

	public Item(int itemID) {
		this.imgDir = imgDir;
		setImageDir();
		img = io.getImage(imgDir);
		this.itemID = itemID;
	}

	private void setImageDir() {
		switch (itemID) {
		case GLOCK:
			break;
		case SNIPER:
			break;
		}
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int i) {
		itemID = i;
	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}

	public BufferedImage getImage() {
		return img;
	}
}
