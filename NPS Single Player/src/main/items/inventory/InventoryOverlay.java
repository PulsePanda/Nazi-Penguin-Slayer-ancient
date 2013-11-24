package main.items.inventory;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InventoryOverlay extends JPanel {

	private int inventoryOverlayWidth = 4;
	private int totalWidth = 0;

	private InventoryBlock[] inventoryBlocks = new InventoryBlock[inventoryOverlayWidth];

	public InventoryOverlay() {
		for (int i = 0; i < inventoryOverlayWidth; i++) {
			int temp = 5 + (InventoryBlock.w * i);
			inventoryBlocks[i] = new InventoryBlock(temp, 5);

			totalWidth += temp;
		}

		setSize(totalWidth, 10 + InventoryBlock.h);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));

		setFocusable(true);
		setVisible(true);
	}

	public void paint(Graphics g) {
		for (int i = 0; i < inventoryOverlayWidth; i++) {
			InventoryBlock ib = inventoryBlocks[i];
			g.drawImage(ib.currentImage, ib.x, ib.y, ib.w, ib.h, null);
		}
	}
}
