package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Core {
	private static int tilew = 50, tileh = 30, day = 0, barValue = 0, xOff = 0,
			yOff = 0;
	private static Tile[][] tiles = new Tile[tilew][tileh];
	public static ArrayList<Tile> list = new ArrayList<Tile>();
	private static Core core;
	private static Frame frame;
	private static JProgressBar bar = new JProgressBar();
	private static boolean running = true, paused = false;
	private static Player player;
	private static Properties properties = new Properties();

	public Core() {
		try {
			properties.load(new FileInputStream(FILES.properties));
		} catch (Exception e) {
			e.printStackTrace();
		}

		bar.setBounds(20, 50, 255, 25);
		bar.setMaximum(tilew * tileh);
		bar.setMinimum(0);
		bar.setValue(barValue);

		createTiles();
		initTiles();

		player = new Player();
	}

	public static Core getCore() {
		return core;
	}

	public static Properties getProperties() {
		return properties;
	}

	public void createTiles() {
		for (int w = 0; w < tilew; w++) {
			for (int h = 0; h < tileh; h++) {
				tiles[w][h] = new Tile(0);
			}
		}
	}

	public static Player getPlayer() {
		return player;
	}

	public void initTiles() {
		// test!!!
		Frame loadingFrame = new Frame("Loading World...", null, 300, 200);
		loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loadingFrame.setLayout(null);

		loadingFrame.add(bar);
		loadingFrame.setLocationRelativeTo(null);

		for (int w = 0; w < tilew; w++) {
			for (int h = 0; h < tileh; h++) {
				long time = System.nanoTime();
				barValue++;
				bar.setValue(barValue);
				if (h == tileh / 2) { // set the grass lvl at half way
					if (time % 2 == 0)
						tiles[w][subtract(h, 1)].setID(1);
					tiles[w][h].setID(1);
				}
				if (tiles[w][subtract(h, 1)].getID() == 1) {// if tile above
															// is grass
					tiles[w][h].setID(2);
				}
				if (tiles[w][subtract(h, 1)].getID() == 2) { // if tile above
																// is dirt
					tiles[w][h].setID(2);
				}
				if (h == 25) {
					if (time % 2 == 0)
						tiles[w][subtract(h, 1)].setID(3);
					tiles[w][h].setID(3);
				}
				if (tiles[w][subtract(h, 1)].getID() == 3) { // if tile above
																// is stone
					tiles[w][h].setID(3);
				}

				list.add(tiles[w][h]);
			}
		}

		loadingFrame.delete();
	}

	public static int getXOff() {
		return xOff;
	}

	public static int getYOff() {
		return yOff;
	}

	public int subtract(int initial, int amount) {
		if (initial - amount <= 0)
			return 0;
		else
			return initial - amount;
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public void setFrame(Frame f) {
		frame = f;
	}

	public static ArrayList<Tile> getList() {
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
