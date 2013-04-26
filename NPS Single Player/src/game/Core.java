package game;

import io.FILES;
import io.IO;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;

import sprites.Block;
import sprites.Player;
import thread.Refresh;

public class Core implements Serializable {

	// integers
	private static int FRAME_WIDTH = 1000;
	private static int FRAME_HEIGHT = 600;
	private static int day = 0;

	// objects
	protected static JFrame frame = new JFrame();
	private static Properties settings = new Properties();
	public static IO io = new IO();
	private static Frame gameFrame;
	private static Player player;
	private static PauseFrame pf = new PauseFrame();
	private static ArrayList objects = new ArrayList();

	// images

	// booleans
	private static boolean gameRunning = false;
	private static boolean gamePaused = false;

	// threads
	private Thread refreshThread;

	// world values
	public static int arrayX = 200, arrayY = 120;
	private static int[][] worldData = new int[arrayX][arrayY];
	private static Block[][] worldBlocks = new Block[arrayX][arrayY];

	public Core() {
	}

	public Core(int w, int h) {
		FRAME_WIDTH = w;
		FRAME_HEIGHT = h;
	}

	private void setUp() {

	}

	public static boolean save() {
		try {
			String dir = "../worlds/world.dat";
			// String dir =
			// "A:\\Google Drive\\AVTECH\\NPS\\Files\\worlds\\world.dat";
			// FileOutputStream fos = new
			// FileOutputStream("../worlds/world.dat");
			FileOutputStream fos = new FileOutputStream(dir);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			File f = new File(dir);
			if (f.exists())
				f.delete();

			SaveObject so = new SaveObject();

			oos.writeObject(so);

			oos.flush();
			oos.close();
		} catch (Exception e) {
			Dialogs.msgDialog("Could not save!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * NEEDS SOME WORK!!!!!!
	 */
	public boolean load() {
		try {
			// FileInputStream fis = new FileInputStream("../worlds/world.dat");
			FileInputStream fis = new FileInputStream("../worlds/world.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			/**
			 * add something to get the data from the so object to the core
			 * values
			 */
			SaveObject so = (SaveObject) ois.readObject();
			FRAME_WIDTH = so.FRAME_WIDTH;
			FRAME_HEIGHT = so.FRAME_HEIGHT;
			day = so.day;
			player = so.player;
			arrayX = so.arrayX;
			arrayY = so.arrayY;
			worldData = so.worldData;
			worldBlocks = so.worldBlocks;
			/**
			 * start the world or whatever
			 */
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
			Dialogs.errorDiagExit("Could not load world! Please make new one!");
			return false;
		}
		return true;
	}

	public boolean start(boolean loaded) {
		if (gameRunning)
			return false;
		else {
			try {
				gameRunning = true;
				settings.load(new FileInputStream(FILES.properties));
				gameFrame = new Frame();
				refreshThread = new Thread(new Refresh());
				refreshThread.start();

				// make sprites and world
//				if (!loaded) {
					player = new Player(io.getImage(FILES.playerImage), 50, 50,
							50, 50, 10.0, 10, 1.0);
					initializeArray();
					setUpObjectsArray();
//				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	private void setUpObjectsArray() {
		for (int i = 0; i < arrayX; i++) { // work on world blocks to add
			for (int a = 0; a < arrayY; a++) {
				objects.add(worldBlocks[a][i]);
			}
		}

	}

	public ArrayList getObjects() {
		return objects;
	}

	private void initializeArray() {
		// make the whole array full of 0's
		for (int i = 0; i < arrayY; i++) {
			for (int a = 0; a < arrayX; a++) {
				worldData[a][i] = 0;
			}
		}

		// make the filler values (ie. dirt and grass and such)
		for (int i = arrayY; i > 0; i++) { // start from the max value and
											// move down
			for (int a = 0; a > arrayX; i++) { // start like normal, left to
												// right
				// if statements for assignment:
				if (i <= arrayY / 4) { // if i is less than or equal to .25 the
										// height of array
					worldData[a][i] = 3; // make the array value stone
				}
				if (i > arrayY / 4 && i < arrayY / 2) { // if the array is
														// above the stone,
														// but below half,
					worldData[a][i] = 2; // make the array value 2 (dirt)
				}
				if (i == arrayY / 2) {
					worldData[a][i] = 1; // make the value that of grass
				}
			}
		}

		// now make the worldBlocks array...
		for (int i = 0; i < arrayY; i++) {
			for (int a = 0; a < arrayX; a++) {
				worldBlocks[a][i] = new Block(worldData[a][i]);
			}
		}
	}

	public static boolean stop() {
		if (!gameRunning)
			return false;
		else {
			try {
				gameRunning = false;

				Desktop desktop = Desktop.getDesktop();
				File openFile = new File(
						"C:\\Program Files\\AVTECH\\NPS\\Files\\bin\\NPS Main.jar");
				desktop.open(openFile);

				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	public static boolean pause() {
		if (gamePaused) {
			gamePaused = false;
		} else if (!gamePaused) {
			gamePaused = true;
			pf.start();
		}

		return gamePaused;
	}

	public boolean isRunning() {
		return gameRunning;
	}

	public boolean isPaused() {
		return gamePaused;
	}

	public static int getDay() {
		return day;
	}

	public static PauseFrame getPauseFrame() {
		return pf;
	}

	public static Frame getFrame() {
		return gameFrame;
	}

	public static int getWidth() {
		return FRAME_WIDTH;
	}

	public void setWidth(int w) {
		FRAME_WIDTH = w;
	}

	public static int getHeight() {
		return FRAME_HEIGHT;
	}

	public void setHeight(int h) {
		FRAME_HEIGHT = h;
	}

	public Properties getSettings() {
		return settings;
	}

	public static Player getPlayer() {
		return player;
	}

	public static int[][] getWorldData() {
		return worldData;
	}

	public static Block[][] getWorldBlocks() {
		return worldBlocks;
	}

	public boolean saveProperties(Properties prop) {
		try {
			prop.save(new FileOutputStream(new File("properties.properties")),
					"Properties File for Nazi Penguin Slayer");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}