package main;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import main.components.frames.Frame;
import main.sprites.Tile;

public class World {

	public static int tilew = 200, tileh = 40, day = 0;

	public static int tileGroupXOff = 0;
	public static int tileGroupYOff = -125;

	public static Tile[][] tiles = new Tile[tilew][tileh];
	public static ArrayList<Tile> list = new ArrayList<Tile>();

	private static Core core = Core.getCore();

	public World() {

	}

	public void initTiles() {
		core.splashScreen.remove();

		Frame loadingFrame = new Frame("Loading World...", null, 300, 200);
		loadingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loadingFrame.setLayout(null);

		loadingFrame.add(core.bar);
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
			core.barValue++;
			core.bar.setValue(core.barValue);
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
				core.barValue++;
				core.bar.setValue(core.barValue);
				moveXDistance++;
				tiles[core.add(x, moveXDistance, 'x')][tempY].setID(1);
				tiles[core.subtract(x, moveXDistance)][tempY].setID(1);
			}
		}

		// make everything in the hills dirt to begin with
		for (int x = 0; x < tilew; x++) {
			core.barValue++;
			core.bar.setValue(core.barValue);
			for (int y = 0; y < tileh; y++) {
				// if the tile above where the pointer is is grass
				if (tiles[x][core.subtract(y, 1)].getID() == 1) {
					tiles[x][y].setID(2);
				}
				// if the tile above where the pointer is is dirt, and it's
				// above the half mark
				if (tiles[x][core.subtract(y, 1)].getID() == 2
						&& y < (tileh / 2)) {
					tiles[x][y].setID(2);
				}
			}
		}

		// get rid of the baseline grass
		for (int x = 0; x < tilew; x++) {
			core.barValue++;
			core.bar.setValue(core.barValue);
			tiles[x][tileh / 2].setID(2);
		}

		for (int x = 0; x < tilew; x++) {
			core.barValue++;
			core.bar.setValue(core.barValue);
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
		if (core.barValue < core.barMax) {
			for (int a = 0; a < core.barMax; a++) {
				core.barValue++;
				core.bar.setValue(core.barValue);
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

	public static ArrayList<Tile> getList() {
		return list;
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
}
