package main.items.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Properties;

import javax.swing.JPanel;

import main.Core;

public class InventoryMenu extends JPanel implements KeyListener {

	private int inventoryWidth = 8;
	private int totalWidth = 0;

	private int inventoryHeight = 5;
	private int totalHeight = 0;

	private boolean visible = false;

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
		setVisible(visible);
	}

	public void visible() {
		if (!visible) {
			visible = true;
			setVisible(visible);
			requestFocus();
		} else {
			visible = false;
			setVisible(visible);
		}
	}

	public void paint(Graphics g) {
		for (int w = 0; w < inventoryWidth; w++) {
			for (int h = 0; h < inventoryHeight; h++) {
				InventoryBlock ib = inventoryBlocks[w][h];
				g.drawImage(ib.currentImage, ib.x, ib.y, ib.w, ib.h, null);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		Core core = Core.getCore();
		Properties properties = core.getProperties();
		if (key == Integer.parseInt(properties.getProperty("inventory"))) {
			core.getInventoryMenu().visible();
		} else if (key == KeyEvent.VK_ESCAPE) {
			visible();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
