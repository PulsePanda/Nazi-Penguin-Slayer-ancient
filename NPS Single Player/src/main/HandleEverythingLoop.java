package main;

public class HandleEverythingLoop implements Runnable {
	private Frame frame;
	private static Core core = Core.getCore();

	public HandleEverythingLoop(Frame f) {
		frame = f;
	}

	public void run() {
		while (Core.isRunning()) {
			while (Core.isPaused()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			frame.refresh();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
