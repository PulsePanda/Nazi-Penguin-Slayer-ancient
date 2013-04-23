package thread;

import game.Enemy;
import game.GameCore;

public class EnemyHandler extends GameCore implements Runnable {
	Enemy e;

	public EnemyHandler(Enemy e) {
		this.e = e;
	}

	public void run() {
		while (gameRunning && e.isVisible()) {
			while (paused) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
			e.move();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
