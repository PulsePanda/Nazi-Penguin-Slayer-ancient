package main.items.inventory;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import main.Core;
import main.components.frames.Dialogs;
import main.components.panels.Menu;
import main.io.FILES;
import main.io.IO;

public class InventoryOverlay extends Menu {

	public InventoryOverlay() {
		this.setFocusable(false);
		this.setVisible(true);
		image = io.getImage(FILES.inventoryImage);
		init();
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, w, h, null);

		Inventory in = Core.getPlayer().getInventory();
	}
}
