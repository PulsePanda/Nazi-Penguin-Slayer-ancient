public class RefreshFrame extends Core implements Runnable {
	public void run() {
		while (isRunning()) {
			while (isPaused())
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			getFrame().refresh();
		}
	}
}
