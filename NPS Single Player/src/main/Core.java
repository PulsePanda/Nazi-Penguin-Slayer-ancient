package main;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JProgressBar;

import main.components.frames.Dialogs;
import main.components.frames.Frame;
import main.components.frames.SplashScreen;
import main.components.panels.PauseMenu;
import main.io.FILES;
import main.io.IO;
import main.items.inventory.InventoryMenu;
import main.items.inventory.InventoryOverlay;
import main.sprites.Player;

public class Core {
	public static int barValue = 0;
	public static int barMax = 2500;
	public static final int frameW = 1000, frameH = 600;
	public static int threadDelay = 20;
	public static BufferedImage playerImage;
	private static IO io = new IO();
	private static Core core;
	private static Frame frame;
	public static SplashScreen splashScreen;
	private static PauseMenu exitMenu;
	private static InventoryMenu invMenu;
	public static InventoryOverlay invOverlay;
	public static JProgressBar bar = new JProgressBar();
	private static boolean running = true, paused = false;
	private static Player player;
	private static Properties properties = new Properties();
	private static FPSCounter fpsCounter = new FPSCounter();
	public static int inventoryWidth = 300;
	private static World world;

	public Core() {
		try {
			properties.load(new FileInputStream(FILES.properties));
		} catch (Exception e) {
			Dialogs.errorDiagExit("Cannot find the properties file at "
					+ FILES.properties + ".\nPlease reinstall the game.");
			e.printStackTrace();
		}

		FILES.version = properties.getProperty("version");

		bar.setBounds(20, 50, 255, 25);
		bar.setMaximum(barMax);
		bar.setMinimum(0);
		bar.setValue(barValue);

		world = new World();
		playerImage = io.getImage(FILES.playerImage);
		player = new Player(playerImage, 493, 20, playerImage.getWidth(),
				playerImage.getHeight());
	}

	public static FPSCounter getFPSCounter() {
		return fpsCounter;
	}

	public static Core getCore() {
		return core;
	}

	public void setTileGroupXOff(int x) {
		world.tileGroupXOff += x;
	}

	public void setPlayerYOff(int y) {
		// tileGroupYOff += y;
		Player.y -= y;
	}

	public Properties getProperties() {
		return properties;
	}

	public static Player getPlayer() {
		return player;
	}

	public int subtract(int initial, int amount) {
		if (initial - amount <= 0)
			return 0;
		else
			return initial - amount;
	}

	public int add(int initial, int amount, char xy) {
		if (xy == 'x') {
			if (initial + amount >= world.tilew)
				return world.tilew - 1;
			else
				return initial + amount;
		} else {
			if (initial + amount >= world.tileh)
				return world.tileh - 1;
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

	public static Frame getFrame() {
		return frame;
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

	public static InventoryOverlay getInvOver() {
		return invOverlay;
	}

	public static void setSplashScreen(SplashScreen f) {
		splashScreen = f;
	}

	public static World getWorld() {
		return world;
	}
}
