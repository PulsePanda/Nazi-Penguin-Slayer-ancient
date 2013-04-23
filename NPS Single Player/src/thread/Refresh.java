package thread;

import game.Core;

public class Refresh extends Core implements Runnable {

	@Override
	public void run() {
		while (isRunning()) {
			while (isPaused()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				getFrame().refresh();
				getFrame().setSelected();

				Thread.sleep(20);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
