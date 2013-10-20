package main.items.inventory;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import main.Core;
import main.components.frames.Dialogs;
import main.components.panels.Menu;
import main.io.FILES;
import main.io.IO;

public class InventoryOverlay extends Menu {

	private static IO io = new IO();
	private int w, h;

	public InventoryOverlay(String t, int w, int h) {
		super(t, w, h);
		this.w = w;
		this.h = h;
		this.setFocusable(false);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(io.getImage(FILES.inventoryImage), 0, 0, w, h, null);
		Inventory in = Core.getPlayer().getInventory();
	}
}
