package thread;

import game.Bullet;
import game.Enemy;
import game.GameCore;

public class MoveBullet extends GameCore implements Runnable {
	Bullet bullet;

	public MoveBullet(Bullet b) {
		bullet = b;
	}

	public void run() {
		while (bullet.visible && gameRunning) {

			while (paused) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}

			bullet.move();

			for (int i = 0; i < enemy.size(); i++) {
				Enemy e = enemy.get(i);
				if (bullet.checkHit(e.getBounds())) {
					e.hit(bullet);
					bullet.delete();
				}
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
