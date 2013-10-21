package main.items.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.components.panels.Menu;
import main.io.FILES;

public class InventoryMenu extends Menu {

	public InventoryMenu(String t) {
		super(t);
	}

	public InventoryMenu(String t, int w, int h) {
		super(t, w, h);
	}

	public void paint(Graphics g) {
		g.drawImage(io.getImage(FILES.inventoryImage), 0, 0, w, h, null);
	}
}
