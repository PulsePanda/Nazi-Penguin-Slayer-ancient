package main.items.inventory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import main.io.FILES;
import main.io.IO;

public class InventoryBlock implements MouseListener {

	private static final IO io = new IO();

	public static final String defaultImageDir = FILES.inventoryBlock;
	public static final String selectedImageDir = FILES.inventoryBlock;
	public static final String mouseOverImageDir = FILES.inventoryBlock;

	public static final BufferedImage defaultImage = io
			.getImage(defaultImageDir);

	public static BufferedImage currentImage = defaultImage;

	public int x, y;
	public static final int w = defaultImage.getWidth(), h = defaultImage
			.getHeight();

	public InventoryBlock(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
}
