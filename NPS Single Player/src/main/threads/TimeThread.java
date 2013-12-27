package main.threads;

import java.util.Date;

import main.Core;

public class TimeThread implements Runnable {

	Core core;

	public TimeThread() {
		core = Core.getCore();
	}

	public void run() {
		Date date = new Date();
		int day = core.getDay(), initialMins = date.getMinutes(), currentMins = initialMins, minsPassed = 0;

		while (core.isRunning()) {
			while (core.isPaused())
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			// reset the date object to be more current
			date = new Date();

			// begin the time keeping process
			currentMins = date.getMinutes();
			if (currentMins != initialMins) {
				minsPassed += 1;
				initialMins = currentMins; // set the base time to the current
											// time to reset the checker
			}

			// check to change the day in the `core` class
			if (minsPassed == 3) {
				minsPassed = 0;
				core.addDay();
			}
		}
	}

}
