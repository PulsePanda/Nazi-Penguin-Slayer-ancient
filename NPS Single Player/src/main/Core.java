package main;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import main.components.frames.Dialogs;
import main.components.frames.Frame;
import main.components.panels.PauseMenu;
import main.io.FILES;
import main.io.IO;
import main.items.inventory.InventoryMenu;
import main.items.inventory.InventoryOverlay;
import main.sprites.Player;
import main.sprites.Tile;

public class Core {
	private static int tilew = 200, tileh = 40, day = 0, barValue = 0, tileGroupXOff = 0, tileGroupYOff = -125, barMax = 2500;
	public static final int frameW = 1000, frameH = 600;
	public static int threadDelay = 20;
	private static Tile[][] tiles = new Tile[tilew][tileh];
	public static ArrayList<Tile> list = new ArrayList<Tile>();
	public static BufferedImage playerImage;
	private static IO io = new IO();
	private static Core core;
	private static Frame frame;
	private static PauseMenu exitMenu;
	private static InventoryMenu invMenu;
	private static InventoryOverlay invOverlay;
	private static JProgressBar bar = new JProgressBar();
	private static boolean running = true, paused = false;
	private static Player player;
	private static Properties properties = new Properties();
	private static FPSCounter fpsCounter = new FPSCounter();
	public static int inventoryWidth = 300;

	public Core() {
		try {
			properties.load(new FileInputStream(FILES.properties));
		} catch (Exception e) {
			Dialogs.errorDiagExit("Cannot find the properties file at " + FILES.properties + ".\nPlease reinstall the game.");
			e.printStackTrace();
		}

		FILES.version = properties.getProperty("version");

		bar.setBounds(20, 50, 255, 25);
		bar.setMaximum(barMax);
		bar.setMinimum(0);
		bar.setValue(barValue);

		createTiles();
		initTiles();

		playerImage = io.getImage(FILES.playerImage);
		player = new Player(playerImage, 493, 20, playerImage.getWidth(), playerImage.getHeight());
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
		 * actually set what the tiles will actually be
		 */
		for (int w = 0; w < tilew; w++) {
			for (int h = 0; h < tileh; h++) {
				long time = System.nanoTime();

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
			barValue++;
			bar.setValue(barValue);
			int x = rand.nextInt(tilew) + 1, y = (tileh / 2) - rand.nextInt(maxHeight);
			// set the tip of the hill
			try {
				tiles[x][y].setID(1);
			} catch (Exception e) {
			}

			// make the sides of the hill
			// while the height is above the halfway baseline
			int moveXDistance = 0;
			for (int tempY = y; tempY < (tileh / 2); tempY++) {
				barValue++;
				bar.setValue(barValue);
				moveXDistance++;
				tiles[add(x, moveXDistance, 'x')][tempY].setID(1);
				tiles[subtract(x, moveXDistance)][tempY].setID(1);
			}
		}

		// make everything in the hills dirt to begin with
		for (int x = 0; x < tilew; x++) {
			barValue++;
			bar.setValue(barValue);
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
			barValue++;
			bar.setValue(barValue);
			tiles[x][tileh / 2].setID(2);
		}

		for (int x = 0; x < tilew; x++) {
			barValue++;
			bar.setValue(barValue);
			for (int y = 0; y < tileh; y++) {
				if (y >= tileh / 2)
					tiles[x][y].setID(2);
			}
		}

		/*
		 * make the base for allies
		 */
		for (int i = 0; i < 22; i++) {
			tiles[i][tileh / 2 - 4].setID(3); // set the base line for the base
			for (int a = (tileh / 2 - 5); a > 0; a--) { // remove everything
														// above it for the rest
				tiles[i][a].setID(0);
			}
			for (int y = (tileh / 2 - 3); y < tileh / 2; y++) {
				tiles[i][y].setID(2); // make everything under the base line
										// dirt
			}
			tiles[i][22].setID(3);
		}
		tiles[0][tileh / 2 - 4].setID(1);
		tiles[1][tileh / 2 - 4].setID(1);

		tiles[0][22].setID(3);
		tiles[0][21].setID(3);
		tiles[0][20].setID(3);
		tiles[0][19].setID(3);
		tiles[1][19].setID(3);

		for (int i = 19; i >= 11; i--) {
			tiles[2][i].setID(3);
		}

		for (int i = 3; i < 23; i++) {
			tiles[i][11].setID(3);
		}

		tiles[21][12].setID(3);
		tiles[21][13].setID(3);
		tiles[21][14].setID(2);
		tiles[21][15].setID(2);

		for (int i = 17; i < 23; i++) {
			tiles[21][i].setID(3);
		}

		// make the loading bar reach 100%
		if (barValue < barMax) {
			for (int a = 0; a < barMax; a++) {
				barValue++;
				bar.setValue(barValue);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

	public static void setInvOver(InventoryOverlay invOver) {
		invOverlay = invOver;
	}
}
