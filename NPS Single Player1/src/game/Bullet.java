package game;

import java.awt.image.BufferedImage;

import thread.MoveBullet;

public class Bullet extends Sprite {
	public int damage;

	public Bullet(BufferedImage img, int x, int y, int damage) {
		super(img, x, y);
		setVisible(true);
		this.damage = damage;
		new Thread(new MoveBullet(this)).start();
	}

	public void move() {
		if (!atEdge( bulletMoveSpeed, "x", FRAME_WIDTH))
			posX =  (posX + bulletMoveSpeed);
		else
			delete();
	}

	public int getDamage() {
		return damage;
	}

	public void delete() {
		killed();
		bullet.remove(this);
		bullet.trimToSize();
	}
}
