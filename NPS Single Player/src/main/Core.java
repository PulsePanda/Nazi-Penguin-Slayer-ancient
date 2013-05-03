package main;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Core {
	private static int tilew = 50, tileh = 30;
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
		// loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loadingFrame.setLayout(null);

		JProgressBar bar = new JProgressBar();
		bar.setBounds(20, 50, 255, 25);
		bar.setMaximum(tilew * tileh);
		bar.setMinimum(0);
		bar.setValue(barValue);

		loadingFrame.add(bar);
		loadingFrame.setLocationRelativeTo(null);

		for (int i = 0; i < tilew; i++) {
			for (int a = 0; a < tileh; a++) {
				barValue++;
				bar.setValue(barValue);

				tiles[i][a] = new Tile(1);
			}
		}

		loadingFrame.delete();
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public void setFrame(Frame f) {
		frame = f;
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
