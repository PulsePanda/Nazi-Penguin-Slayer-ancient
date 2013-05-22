package main;

public class TimeThread implements Runnable {

	Core core;

	public TimeThread() {
		core = Core.getCore();
	}

	public void run() {
		double day = 0.0;

		while (core.isRunning()) {
			while (core.isPaused())
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			try {
				Thread.sleep(Core.threadDelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
