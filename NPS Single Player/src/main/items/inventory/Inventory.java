package main.items.inventory;

import java.util.ArrayList;

import main.items.Item;
import main.sprites.Player;

public class Inventory {

	private Player p;
	private ArrayList<Item> items = new ArrayList<Item>();

	public Inventory(Player p) {
		this.p = p;
	}

	public void addItem(Item i) {
		items.add(i);
	}

	public void destroyItem(int index) {
		items.remove(index);
	}

	public void setItem(int index, Item i) {
		items.set(index, i);
	}

	public void compactInventory() {
		items.trimToSize();
	}
}
