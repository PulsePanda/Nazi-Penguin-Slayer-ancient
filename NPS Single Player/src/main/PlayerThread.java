package main;

public class PlayerThread implements Runnable {

	private Core core;
	private Player player;

	public PlayerThread() {
		core = Core.getCore();
		player = core.getPlayer();
	}

	public void run() {
		while (core.isRunning()) {
			while (core.isPaused()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			player.applyGravity();
			player.move();

			try {
				Thread.sleep(player.getMoveSpeed());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
