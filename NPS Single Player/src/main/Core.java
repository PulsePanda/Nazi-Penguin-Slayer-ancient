package main;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Core {
	private static int tilew = 50, tileh = 30, day = 0;
	public static Tile[][] tiles = new Tile[tilew][tileh];
	private static Core core;
	private static Frame frame;
	private static boolean running = true, paused = false;

	public Core() {
		initTiles();
	}

	public void initTiles() {
		int barValue = 0;
		Frame loadingFrame = new Frame("Loading World...", null, 300, 200);
		loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loadingFrame.setLayout(null);

		JProgressBar bar = new JProgressBar();
		bar.setBounds(20, 50, 255, 25);
		bar.setMaximum(tilew * tileh);
		bar.setMinimum(0);
		bar.setValue(barValue);

		loadingFrame.add(bar);
		loadingFrame.setLocationRelativeTo(null);

		for (int w = 0; w < tilew; w++) {
			for (int h = 0; h < tileh; h++) {
				barValue++;
				bar.setValue(barValue);
				if (h == tileh / 2)
					tiles[w][h] = new Tile(1);
				else if (h > tileh / 2)
					tiles[w][h] = new Tile(2);
				else
					tiles[w][h] = new Tile(0);
			}
		}

		// loadingFrame.delete();
	}

	public int getOneLessThan(int i) {
		if (i - 1 <= 0)
			return 0;
		else
			return i - 1;
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public void setFrame(Frame f) {
		frame = f;
	}

	public static Frame getFrame() {
		return frame;
	}

	public static int getDay() {
		return day;
	}

	public static Tile[][] getTiles() {
		return tiles;
	}

	public static int getTileArrayWidth() {
		return tilew;
	}

	public static int getTileArrayHeight() {
		return tileh;
	}

	public static boolean isRunning() {
		return running;
	}

	public static boolean isPaused() {
		return paused;
	}
}
