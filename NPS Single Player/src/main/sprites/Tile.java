/**
 * tile codes:
 * 0: nothing
 * 1: grass
 * 2: dirt
 * 3: stone
 */

package main.sprites;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.io.FILES;
import main.io.IO;

public class Tile extends Component {
	public String img;
	public int ID;
	public BufferedImage image;
	public int x, y;
	public int w;
	public int h;
	public IO io = new IO();

	public Tile(int ID) {
		this.ID = ID;
		image = io.getImage(FILES.dirtBlock);
		w = image.getWidth();
		h = image.getHeight();
		if (ID != 0) {
			img = getImageString(ID);
			image = io.getImage(img);
		} else {
			image = null;
			img = null;
		}
	}

	public void setX(int i) {
		x = i;
	}

	public int getX() {
		return x;
	}

	public void setY(int i) {
		y = i;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getImageString(int ID) {
		switch (ID) {
		case 0:
			return null;
		case 1:
			return FILES.grassBlock;
		case 2:
			return FILES.dirtBlock;
		case 3:
			return FILES.stoneBlock;
		default:
			return null;
		}
	}

	public void setID(int ID) {
		this.ID = ID;
		if (ID != 0) {
			img = getImageString(ID);
			image = io.getImage(img);
		} else {
			image = null;
			img = null;
		}
	}

	public int getID() {
		return ID;
	}

	public Rectangle getBounds() {
		if (getID() == 0)
			return new Rectangle();
		else
			return new Rectangle(x, y, w, h);
	}
}
