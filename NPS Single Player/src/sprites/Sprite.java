package sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import game.Core;
import game.Dialogs;

public class Sprite implements Serializable {
	public Core core = new Core();
	private final static int MINIMUM_HEALTH = 0, MINIMUM_SPEED = 1,
			MINIMUM_DAMAGE = 1;
	private int width, height, x, y, health, maxHealth;
	private double speed, damage;
	private BufferedImage img;

	public Sprite(BufferedImage img, int x, int y, int width, int height,
			double speed, int maxHealth, double damage) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.img = img;
		this.speed = speed;
		this.maxHealth = maxHealth;
		this.damage = damage;
		health = maxHealth;
	}

	public void getHit(int damageTaken) {
		if (getCurrentHealth() - damageTaken >= MINIMUM_HEALTH) {
			/**
			 * insert an animation for damage taken and show health lost
			 */
			changeHealth(-damageTaken);
		} else if (getCurrentHealth() - damageTaken <= MINIMUM_HEALTH) {
			die();
		} else {
			Dialogs.errorDiagExit("Critical Error! Player damage error!");
		}
	}

	public void getHealed(int amountHealed) {
		/**
		 * insert animation for getting healed and show health gained and play
		 * heal sound if player
		 */
		changeHealth(amountHealed);
	}

	public void die() {
		/**
		 * set animation for death!
		 */
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, getWidth(), getHeight());
	}

	public int getCurrentHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public double getSpeed() {
		return speed;
	}

	public double getDamage() {
		return damage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void changeX(int amount) {
		x += amount;
	}

	public void setX(int set) {
		x = set;
	}

	public void changeY(int amount) {
		y += amount;
	}

	public void setY(int set) {
		y = set;
	}

	public void changeW(int amount) {
		width += amount;
	}

	public void setW(int set) {
		width = set;
	}

	public void changeH(int amount) {
		height += amount;
	}

	public void setH(int set) {
		height = set;
	}

	public void changeSpeed(double amount) {
		speed += amount;
	}

	public void setSpeed(double set) {
		speed = set;
	}

	public void changeDamage(double amount) {
		damage += amount;
	}

	public void setDamage(double set) {
		damage = set;
	}

	public void changeHealth(int amount) {
		health += amount;
	}

	public void setHealth(int set) {
		health = set;
	}

	public void setMaxHealth(int set) {
		maxHealth = set;
	}

	public BufferedImage getImage() {
		return img;
	}
}
