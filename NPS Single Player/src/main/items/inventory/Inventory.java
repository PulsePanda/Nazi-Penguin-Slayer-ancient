package main.items.inventory;

import java.util.ArrayList;

import main.items.Item;
import main.sprites.Player;

public class Inventory {

	/**
	 * items 1 - 3 are equipable. the rest need to be moved. ie: key 1 equips
	 * item slot 0; key 2 equips item slot 1, so on, up to 3
	 */

	private Player p;
	private static final int maxInvSpace = 16;
	private Item[] items = new Item[maxInvSpace];

	public Inventory(Player p) {
		this.p = p;
		setUpInv();
	}

	private void setUpInv() {
		// have a default glock item in slot 1. make the rest = null
		for (int i = 0; i < maxInvSpace; i++) {
			items[i] = null;
		}
		items[0] = new Item(Item.GLOCK);
	}

	public void addItem(Item i) {
		for (int a = 0; a < maxInvSpace; a++) {
			if (items[a].equals(null)) {
				items[a] = i;
				return;
			}
		}
	}

	public void destroyItem(int index) {
		items[index] = null;
	}

	public void setItem(int index, Item i) {
		items[index] = i;
	}

	public void compactInventory() {

	}

	public Item getItem(int index) {
		return items[index];
	}

	public int getMaxSpace() {
		return maxInvSpace;
	}
}
