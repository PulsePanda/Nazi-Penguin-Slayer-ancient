package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite extends GameCore {
	public BufferedImage img;
	public int posX;
	public int posY;
	public boolean visible;

	public Sprite(BufferedImage img, int x, int y) {
		this.img = img;
		posX = x;
		posY = y;
		visible = true;
	}

	public void moveX(double speed) {
		posX += speed;
	}

	public void moveY(double speed) {
		posY += speed;
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public int getWidth() {
		return img.getWidth();
	}

	public int getHeight() {
		return img.getHeight();
	}

	public BufferedImage getImage() {
		return img;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, getWidth(), getHeight());
	}

	public void setVisible(boolean vis) {
		visible = vis;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean checkHit(Rectangle r) {
		boolean hit = false;
		/* need something here to check contact with a bullet... */
		if (getBounds().intersects(r))
			hit = true;
		return hit;
	}

	public boolean atEdge(int x, String xy, int border) {
		boolean atEdge = false;

		if (xy.equals("x"))
			if (posX + x <= 0 || posX + getWidth() + x >= border)
				atEdge = true;

		if (xy.equals("y"))
			if (posY + getHeight() + x >= border || posY + x <= 0)
				atEdge = true;

		return atEdge;
	}

	public void killed() {
		setVisible(false);
	}
}
