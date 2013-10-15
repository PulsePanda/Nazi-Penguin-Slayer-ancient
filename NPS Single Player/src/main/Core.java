package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Core {
	private static int tilew = 200, tileh = 40, day = 0, barValue = 0,
			tileGroupXOff = -1000, tileGroupYOff = -125;
	public static int threadDelay = 20;
	private static Tile[][] tiles = new Tile[tilew][tileh];
	public static ArrayList<Tile> list = new ArrayList<Tile>();
	private static Core core;
	private static Frame frame;
	private static PauseMenu exitMenu;
	private static InventoryMenu invMenu;
	private static JProgressBar bar = new JProgressBar();
	private static boolean running = true, paused = false;
	private static Player player;
	private static Properties properties = new Properties();
	private static FPSCounter fpsCounter = new FPSCounter();

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

		player = new Player(FILES.playerImage, 493, 20, 20, 20);
	}

	public static FPSCounter getFPSCounter() {
		return fpsCounter;
	}

	public static Core getCore() {
		return core;
	}

	public void setTileGroupXOff(int x) {
		tileGroupXOff += x;
	}

	public void setPlayerYOff(int y) {
		// tileGroupYOff += y;
		Player.y -= y;
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

				// establish the base line of the grass
				if (h == tileh / 2)
					tiles[w][h].setID(1);

				list.add(tiles[w][h]);
			}
		}

		// pull up a random number of hills random heights and at random spots
		final int maxNumberOfHills = tilew - 10, minNumberOfHills = tilew - 100, maxHeight = 6;
		Random rand = new Random();
		int numberOfHills = rand.nextInt(maxNumberOfHills) + 1;

		while (numberOfHills < minNumberOfHills)
			numberOfHills = rand.nextInt(maxNumberOfHills) + 1;
		for (int i = 0; i < numberOfHills; i++) {
			int x = rand.nextInt(tilew) + 1, y = (tileh / 2)
					- rand.nextInt(maxHeight);
			// set the tip of the hill
			try {
				tiles[x][y].setID(1);
			} catch (Exception e) {
			}

			// make the sides of the hill
			// while the height is above the halfway baseline
			int moveXDistance = 0;
			for (int tempY = y; tempY < (tileh / 2); tempY++) {
				moveXDistance++;
				tiles[add(x, moveXDistance, 'x')][tempY].setID(1);
				tiles[subtract(x, moveXDistance)][tempY].setID(1);
			}
		}

		// make everything in the hills dirt to begin with
		for (int x = 0; x < tilew; x++) {
			for (int y = 0; y < tileh; y++) {
				// if the tile above where the pointer is is grass
				if (tiles[x][subtract(y, 1)].getID() == 1) {
					tiles[x][y].setID(2);
				}
				// if the tile above where the pointer is is dirt, and it's
				// above the half mark
				if (tiles[x][subtract(y, 1)].getID() == 2 && y < (tileh / 2)) {
					tiles[x][y].setID(2);
				}
			}
		}

		// get rid of the baseline grass
		for (int x = 0; x < tilew; x++) {
			tiles[x][tileh / 2].setID(2);
		}

		for (int x = 0; x < tilew; x++) {
			for (int y = 0; y < tileh; y++) {
				if (y >= tileh / 2)
					tiles[x][y].setID(2);
			}
		}

		loadingFrame.remove();
	}

	public static int getXOff() {
		return tileGroupXOff;
	}

	public static int getYOff() {
		return tileGroupYOff;
	}

	public int subtract(int initial, int amount) {
		if (initial - amount <= 0)
			return 0;
		else
			return initial - amount;
	}

	public int add(int initial, int amount, char xy) {
		if (xy == 'x') {
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

	public void setExitMenu(PauseMenu gm) {
		exitMenu = gm;
	}

	public PauseMenu getExitMenu() {
		return exitMenu;
	}

	public void setInventoryMenu(InventoryMenu invM) {
		invMenu = invM;
	}

	public InventoryMenu getInventoryMenu() {
		return invMenu;
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
