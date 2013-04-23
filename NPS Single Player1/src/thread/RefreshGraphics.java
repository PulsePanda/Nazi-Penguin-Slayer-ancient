package thread;

import game.GameCore;

public class RefreshGraphics extends GameCore implements Runnable {

	public void run() {
		while (gameRunning) {

			while (paused) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}

			if (!player.isVisible()) {
				player.die();
			}
			player.move();

			for (int i = 0; i < enemy.size(); i++) {
				if (player.checkHit(enemy.get(i).getBounds()))
					player.die();
			}

			gameFrame.refresh();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
