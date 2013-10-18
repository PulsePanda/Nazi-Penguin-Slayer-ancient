package main.items;

import main.io.FILES;

public class Glock extends Item {

	public Glock() {
		super(Item.GLOCK);
		imgDir = FILES.glockImage;
		setImage();
	}
}
