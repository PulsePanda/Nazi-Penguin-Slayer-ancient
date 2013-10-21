package main.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import main.components.frames.Dialogs;

public class FILES {
	public static final String dirtBlock = "../graphic/Dirt Block.png";
	public static final String stoneBlock = "../graphic/Stone Block.png";
	public static final String grassBlock = "../graphic/Grass Block.png";

	public static final String backgroundImage = "../graphic/Game Background.png";
	public static final String ingameMenuBackground = "../graphic/Ingame Menu Background.png";
	public static final String inventoryImage = "../graphic/Inventory.png";

	public static final String playerImage = "../graphic/Player.png";

	public static final String glockImage = "../graphic/Glock.png";
	public static final String m1GrandImage = "../graphic/M1 Grand.png";

	public static final String properties = "properties.properties";

	public static final String version = "0.0.1";

	@SuppressWarnings("deprecation")
	public static boolean saveProperties(Properties prop) {
		try {
			prop.save(new FileOutputStream(new File(properties)), "Properties File for Nazi Penguin Slayer");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Dialogs.errorDiagExit("Cannot find the properties file at " + FILES.properties + ".\nPlease reinstall the game.");
			return false;
		}
	}
}
