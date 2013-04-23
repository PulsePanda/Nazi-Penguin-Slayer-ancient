package game;

import java.awt.image.BufferedImage;
import java.util.Random;

import thread.EnemyHandler;

public class Enemy extends Sprite {

	static double speed = 1;
	int health = 2;
	int z = 200;

	public Enemy(BufferedImage img, int x, int y) {
		super(img, x, y);
		setVisible(true);
		new Thread(new EnemyHandler(this)).start();
	}

	public void hit(Bullet b) {
		int bulletDamage = b.getDamage();

		health -= bulletDamage;
		if (health <= 0) {
			killed();
			enemy.remove(this);
			enemy.trimToSize();
			newEnemy();
		}
	}

	public void newEnemy() {
		if (enemy.isEmpty()) {
			level++;
			/**
			 * NEED TO MAKE A WAY TO MOVE FASTER AS A PERSON
			 */
			for (int i = 0; i < level; i++) {
				enemy.add(new Enemy(playerImage, ENEMY_START_X, randomGenerator
						.nextInt(650)));
			}
		}
	}

	public void move() {
		// int playerX = player.getX();
		// int playerY = player.getY();
		//
		// if (playerX < getX()) {
		// moveX(-speed);
		// } else {
		// moveX(speed);
		// }
		// if (playerY < getY()) {
		// moveY(-speed);
		// } else {
		// moveY(speed);
		// }

		// System.out.println(getX());
		if (getX() > 200) {
			moveX(-speed);
		} else {
			player.die();
			setVisible(false);
		}
	}

}
