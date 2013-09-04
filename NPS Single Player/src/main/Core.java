package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Core {
	private static int tilew = 200, tileh = 40, day = 0, barValue = 0,
			xOff = -1000, yOff = 0;
	public static int threadDelay = 20;
	private static Tile[][] tiles = new Tile[tilew][tileh];
	public static ArrayList<Tile> list = new ArrayList<Tile>();
	private static Core core;
	private static Frame frame;
	private static GameMenu exitMenu;
	private static JProgressBar bar = new JProgressBar();
	private static boolean running = true, paused = false;
	private static Player player;
	private static Properties properties = new Properties();

	public Core() {
		try {
			properties.load(new FileInputStream(FILES.properties));
		} catch (Exception e) {
			Dialogs.errorDiagExit("Cannot find the properties file at "
					+ FILES.properties + ".\nPlease reinstall the game.");
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

	public void setXOff(int x) {
		xOff += x;
	}

	public void setYOff(int y) {
		yOff += y;
	}

	public Properties getProperties() {
		return properties;
	}

	// actually make the tiles, init all to 0
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
		Frame loadingFrame = new Frame("Loading World...", null, 300, 200);
		loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loadingFrame.setLayout(null);

		loadingFrame.add(bar);
		loadingFrame.setLocationRelativeTo(null);

		/**
		 * actually set what the tiles will actually be also need to make this
		 * do things better
		 */
		for (int w = 0; w < tilew; w++) {
			for (int h = 0; h < tileh; h++) {
				long time = System.nanoTime();
				barValue++;
				bar.setValue(barValue);

				// /**
				// * where it generates everything above the base grass line...
				// */
				// if (h == (tileh / 2) - 1) { // this if statement makes blocks
				// of
				// // 2. 0110
				// if (time % 5 == 0) {
				// tiles[w][h].setID(1);
				// tiles[add(w, 1, 'w')][h].setID(1);
				// }
				// }
				//
				// // this makes blocks 4 on bot, 2 on top
				// if (h == (tileh / 2) - 1) {
				// if (time % 13 == 0) {
				// tiles[w][h].setID(1);
				// tiles[add(w, 1, 'w')][h].setID(2);
				// tiles[add(w, 2, 'w')][h].setID(2);
				// tiles[add(w, 3, 'w')][h].setID(1);
				// tiles[add(w, 1, 'w')][add(h, 1, 'h')].setID(1);
				// tiles[add(w, 2, 'w')][add(h, 2, 'h')].setID(1);
				// }
				// }
				//
				// // this is making that wierd little point thingy
				// if (h == (tileh / 2) - 1) { // (maybe) set the grass lvl at
				// // above half way
				// if (time % 10 == 0) {
				// tiles[w][h].setID(1);
				// tiles[add(w, 1, 'w')][h].setID(1);
				// tiles[add(w, 2, 'w')][h].setID(1);
				// tiles[add(w, 1, 'w')][subtract(h, 1)].setID(1);
				//
				// }
				// }
				//
				// /**
				// * make there not be any 101 blocks
				// */
				// if (h <= tileh / 2) {
				// if (tiles[subtract(w, 2)][h].getID() == 1) {
				// if (tiles[subtract(w, 1)][h].getID() == 0) {
				// if (tiles[w][h].getID() == 1) {
				// tiles[subtract(w, 1)][h].setID(1);
				// tiles[subtract(w, 1)][add(h, 1, 'h')].setID(2);
				// }
				// }
				// }
				// }
				//
				// /**
				// * main part of the generation, keep all of this
				// */
				// if (h == tileh / 2) { // set the grass lvl at half way
				// if (tiles[w][subtract(h, 1)].getID() != 1)
				// tiles[w][h].setID(1);
				// }
				// if (tiles[w][subtract(h, 1)].getID() == 1) {// if tile above
				// // is grass
				// tiles[w][h].setID(2);
				// }
				// if (tiles[w][subtract(h, 1)].getID() == 2) { // if tile above
				// // is dirt
				// tiles[w][h].setID(2);
				// }
				// if (h == 25) { // random generation of stone
				// if (time % 2 == 0)
				// tiles[w][subtract(h, 1)].setID(3);
				// tiles[w][h].setID(3);
				// }
				// if (tiles[w][subtract(h, 1)].getID() == 3) { // if tile above
				// // is stone
				// tiles[w][h].setID(3);
				// }
				//
				// // make sure the top tile is grass
				// if (tiles[w][subtract(h, 1)].getID() == 0) {
				// if (tiles[w][h].getID() != 0) {
				// tiles[w][h].setID(1);
				// }
				// }

				/***************
				 * different try
				 */

				/**
				 * keep this one first
				 */
				// establish the base line of the grass
				if (h == tileh / 2)
					tiles[w][h].setID(1);

				/**
				 * everything else
				 */

				/**
				 * put these two if's last to clean up everything
				 */
				// make sure the top block is grass
				if (tiles[w][subtract(h, 1)].getID() == 0)
					if (tiles[w][h].getID() != 0)
						tiles[w][h].setID(1);

				// make sure that all the blocks until there is a straight line
				// under the grass is dirt
				if (tiles[w][subtract(h, 1)].getID() == 1)
					tiles[w][h].setID(2); // set to dirt if directly under the
											// grass

				// sets a base line of the dirt 1 below the half way point. i
				// dont honestly see a point to this, so i commented it out for
				// now
				// if (h == (tileh / 2) + 1)
				// tiles[w][h].setID(2);

				/*****************
				 * end of different try
				 */

				list.add(tiles[w][h]);
			}

		}

		Random rand = new Random();
		int x, y;
		x = rand.nextInt(tilew);
		y = (tileh / 2) - rand.nextInt(6);

		tiles[x][y].setID(1);
		tiles[x - 1][y - 1].setID(1); // down 1, right 1
		tiles[x - 2][y - 2].setID(1);

		loadingFrame.remove();
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

	public int add(int initial, int amount, char wh) {
		if (wh == 'w') {
			if (initial + amount >= tilew)
				return tilew - 1;
			else
				return initial + amount;
		} else {
			if (initial + amount >= tileh)
				return tileh - 1;
			else
				return initial + amount;
		}
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public void setExitMenu(GameMenu gm) {
		exitMenu = gm;
	}

	public GameMenu getExitMenu() {
		return exitMenu;
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

	public static void addDay() {
		day++;
	}

	public static void setDay(int i) {
		day = i;
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

	public static void setRunning(boolean b) {
		running = b;
	}

	public static boolean isPaused() {
		return paused;
	}

	public static void pause(boolean b) {
		paused = b;
	}
}
