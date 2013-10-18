package main.items;

import java.awt.image.BufferedImage;
import main.io.IO;

public class Item {

	/**
	 * all the default item id's that are usable
	 */
	public static final int GLOCK = 0;
	public static final int M1GRAND = 1;

	protected String imgDir;
	protected int itemID;
	protected BufferedImage img;
	protected IO io = new IO();

	public Item(int itemID) {
		this.itemID = itemID;
	}

	protected void setImage() {
		img = io.getImage(imgDir);
	}

	public int getItemID() {
		return itemID;
	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}

	public BufferedImage getImage() {
		return img;
	}

	public void shoot() {

	}

	public void reload() {

	}

	public void getAmmo() {

	}

	public void addAmmo() {

	}

	public void subAmmo() {

	}
}
