package main.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import main.components.frames.Dialogs;

public class FILES {

	// C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\

	// public static final String dirtBlock = "../graphic/Dirt Block.png";
	// public static final String stoneBlock = "../graphic/Stone Block.png";
	// public static final String grassBlock = "../graphic/Grass Block.png";
	//
	// public static final String backgroundImage =
	// "../graphic/Game Background.png";
	// public static final String ingameMenuBackground =
	// "../graphic/Ingame Menu Background.png";
	// public static final String inventoryImage = "../graphic/Inventory.png";
	// public static final String inventoryBlock =
	// "../graphic/Inventory Block.png";
	// public static final String pauseOverlay = "../graphic/Pause Overlay.png";
	//
	// public static final String playerImage = "../graphic/Player.png";
	//
	// public static final String glockImage = "../graphic/Glock.png";
	// public static final String m1GrandImage = "../graphic/M1 Grand.png";
	//
	// public static final String properties = "properties.properties";
	// public static final String splashScreen = "../graphic/Splash Screen.png";

	public static final String dirtBlock = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Dirt Block.png";
	public static final String stoneBlock = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Stone Block.png";
	public static final String grassBlock = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Grass Block.png";

	public static final String backgroundImage = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Game Background.png";
	public static final String ingameMenuBackground = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Ingame Menu Background.png";
	public static final String inventoryImage = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Inventory.png";
	public static final String pauseOverlay = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Pause Overlay.png";
	public static final String inventoryBlock = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Inventory Block.png";

	public static final String playerImage = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Player.png";

	public static final String glockImage = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Glock.png";
	public static final String m1GrandImage = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\M1 Grand.png";

	public static final String properties = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\bin\\properties.properties";

	public static final String splashScreen = "C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Splash Screen.png";

	// public static final String dirtBlock =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Dirt Block.png";
	// public static final String stoneBlock =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Stone Block.png";
	// public static final String grassBlock =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Grass Block.png";
	//
	// public static final String backgroundImage =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Game Background.png";
	// public static final String ingameMenuBackground =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Ingame Menu Background.png";
	// public static final String inventoryImage =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Inventory.png";
	// public static final String pauseOverlay =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Pause Overlay.png";
	// public static final String inventoryBlock =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Inventory Block.png";
	//
	// public static final String playerImage =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Player.png";
	//
	// public static final String glockImage =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Glock.png";
	// public static final String m1GrandImage =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\M1 Grand.png";
	//
	// public static final String properties =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\bin\\properties.properties";
	//
	// public static final String splashScreen =
	// "D:\\Google Drive\\AVTECH\\NPS\\Files\\graphic\\Splash Screen.png";

	public static String version = "0_0_1";

	@SuppressWarnings("deprecation")
	public static boolean saveProperties(Properties prop) {
		try {
			prop.save(new FileOutputStream(new File(properties)),
					"Properties File for Nazi Penguin Slayer");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Dialogs.errorDiagExit("Cannot find the properties file at "
					+ FILES.properties + ".\nPlease reinstall the game.");
			return false;
		}
	}
}
