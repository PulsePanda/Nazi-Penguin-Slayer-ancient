package main.items.inventory;

import java.awt.Graphics;

import main.components.panels.Menu;
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
		g.drawImage(io.getImage("C:\\Users\\Austin\\Desktop\\inv.png"), 0, 0,
				w, h, null);
	}
}
