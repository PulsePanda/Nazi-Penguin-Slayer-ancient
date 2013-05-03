package main;

public class RefreshFrame implements Runnable {
	Frame frame;

	public RefreshFrame(Frame f) {
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
