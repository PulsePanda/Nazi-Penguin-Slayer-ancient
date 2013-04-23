package thread;

import game.GameCore;

public class Shoot extends GameCore implements Runnable {

	private static boolean running = false;

	public void run() {
		while (paused) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		running = true;
		while (shooting && gameRunning) {
			player.shoot();

			try {
				Thread.sleep(bulletShootSpeedMillis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		running = false;
	}

	public boolean getRunning() {
		return running;
	}

}
