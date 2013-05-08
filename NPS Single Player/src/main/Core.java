package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Core {
	private static int tilew = 50, tileh = 30, day = 0, barValue = 0;
	public static Tile[][] tiles = new Tile[tilew][tileh];
	public static ArrayList list = new ArrayList();
	private static Core core;
	private static Frame frame;
	private static JProgressBar bar = new JProgressBar();
	private static boolean running = true, paused = false;

	public Core() {
		bar.setBounds(20, 50, 255, 25);
		bar.setMaximum(tilew * tileh);
		bar.setMinimum(0);
		bar.setValue(barValue);

		createTiles();
		initTiles();
	}

	public void createTiles() {
		for (int w = 0; w < tilew; w++) {
			for (int h = 0; h < tileh; h++) {
				// barValue++;
				// bar.setValue(barValue);
				tiles[w][h] = new Tile(0);
				list.add(tiles[w][h]);
			}
		}
	}

	public void initTiles() {
		// test!!!
		Frame loadingFrame = new Frame("Loading World...", null, 300, 200);
		loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loadingFrame.setLayout(null);

		loadingFrame.add(bar);
		loadingFrame.setLocationRelativeTo(null);

		// for (int w = 0; w < tilew; w++) {
		// for (int h = 0; h < tileh; h++) {
		// barValue++;
		// bar.setValue(barValue);
		// if (h == tileh / 2)
		// tiles[w][h] = new Tile(1);
		// else if (h > tileh / 2)
		// tiles[w][h] = new Tile(2);
		// else
		// tiles[w][h] = new Tile(0);
		// list.add(tiles[w][h]);
		// }
		// }
		for (int w = 0; w < tilew; w++) {
			for (int h = 0; h < tileh; h++) {
				long time = System.nanoTime();
				barValue++;
				bar.setValue(barValue);
				if (h == tileh / 2) { // set the grass lvl at half way
					if (time % 2 == 0)
						tiles[w][getOneLessThan(h)] = new Tile(1);
					tiles[w][h] = new Tile(1);
				}
				if (tiles[w][getOneLessThan(h)].getID() == 1) {// if tile above
																// is grass
					tiles[w][h] = new Tile(2);
				}
				if (tiles[w][getOneLessThan(h)].getID() == 2) { // if tile above
																// is dirt
					tiles[w][h] = new Tile(2);
				}
				if (h == 25) {
					tiles[w][h] = new Tile(3);
				}
				if (tiles[w][getOneLessThan(h)].getID() == 3) { // if tile above
																// is stone
					tiles[w][h] = new Tile(3);
				}
			}
		}

		loadingFrame.delete();
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

	public ArrayList getList() {
		return list;
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
