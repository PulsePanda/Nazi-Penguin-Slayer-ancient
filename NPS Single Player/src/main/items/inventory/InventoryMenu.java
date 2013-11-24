package main.items.inventory;

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

			}
		}
	}
}
