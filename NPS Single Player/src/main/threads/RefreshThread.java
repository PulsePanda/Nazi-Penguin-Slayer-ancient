package main.threads;

import main.Core;
import main.components.frames.Frame;

public class RefreshThread implements Runnable {
	private Frame frame;
	private static Core core = Core.getCore();

	public RefreshThread(Frame f) {
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
			Core.getFPSCounter().tick();
			try {
				Thread.sleep(Core.threadDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
