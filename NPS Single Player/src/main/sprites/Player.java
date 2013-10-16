package main.sprites;

import main.Core;
import main.items.inventory.Inventory;

public class Player extends Sprite {

//	private Inventory inv = new Inventory(this);

	public Player(String imgDir, int x, int y, int w, int h) {
		super(imgDir, x, y, w, h);

	}

	public void move() {
		core = Core.getCore();
		if (!ableToMove)
			return;
		if (moveX < 0 && !isCollidingRight())
			core.setTileGroupXOff(moveX);
		if (moveX > 0 && !isCollidingLeft())
			core.setTileGroupXOff(moveX);
		core.setPlayerYOff(moveY);
	}

	public Inventory getInventory() {
		return inv;
	}

	public void setInventory(Inventory in) {
		inv = in;
	}

	public void jump() {
		if (isFalling())
			return;
		jumping = true;

		for (int i = 8; i > 0; i--) { // wanna set a way to make 8! more
										// automatic
			core.setPlayerYOff(i);
		}
		jumping = false;
		falling = true;
	}
}
