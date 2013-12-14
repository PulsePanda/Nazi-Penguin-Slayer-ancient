package main.items.inventory;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InventoryMenu extends JPanel {

	private int inventoryWidth = 8;
	private int totalWidth = 0;

	private int inventoryHeight = 5;
	private int totalHeight = 0;

	private InventoryBlock[][] inventoryBlocks = new InventoryBlock[inventoryWidth][inventoryHeight];

	public InventoryMenu() {
		for (int w = 0; w < inventoryWidth; w++) {
			int tempW = 5 + (InventoryBlock.w * w);

			for (int h = 0; h < inventoryHeight; h++) {
				int tempH = 5 + InventoryBlock.h + (InventoryBlock.h * h);
				inventoryBlocks[w][h] = new InventoryBlock(tempW, tempH);

				totalWidth += tempW;
				totalHeight += tempH;
			}
		}

		setSize(totalWidth, 10 + totalHeight);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));

		setFocusable(true);
		setVisible(true);
	}

	public void paint(Graphics g) {
		for (int w = 0; w < inventoryWidth; w++) {
			for (int h = 0; h < inventoryHeight; h++) {
				InventoryBlock ib = inventoryBlocks[w][h];
				g.drawImage(ib.currentImage, ib.x, ib.y, ib.w, ib.h, null);
			}
		}
	}
}
