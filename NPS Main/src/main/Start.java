package main;

import files.FILES;
import io.IO;
import io.PlaySound;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import panels.MainScreenPanel;
import panels.SplashScreen;

public class Start {

	public static Frame mf;
	static IO io = new IO();
	static PlaySound l = new PlaySound();
	static final String version = "0.0.1";
	public static Properties currentProp;

	public static void main(String[] args) {
		// loadEverything();
		loadProperties();
		// if (currentProp.getProperty("running").equals("true")) {
		// Dialogs.msgDialog("Game already running. Either close this game, or close the other one.");
		// System.exit(0);
		// }

		if (!currentProp.getProperty("version").equals(version)) {
			currentProp.setProperty("version", version);
		}

		// if (currentProp.getProperty("running").equals("true")) {
		// startMenuFrame();
		// } else
//		SplashScreen ss = new SplashScreen();
//		try {
//			Thread.sleep(1000);
//		} catch (Exception e) {
//		}
		Login.createFrame(); // use this in final version :D
//		ss.remove();
		// startMenuFrame(); // take this away when cleaning up
	}

	public static void startMenuFrame() {
		currentProp.setProperty("running", "true");
		FILES.saveProperties(currentProp);
		mf = new Frame(800, 500, 0, 0, false, new MainScreenPanel(mf.getW(),
				mf.getH()), "Nazi Penguin Slayer");

		if (currentProp.getProperty("first").equals("true")) {
			Dialogs.msgDialog("Please enter the controls you would like to use.\nYou will only have to do this once.");
			Dialogs.changeControls();
			currentProp.setProperty("first", "false");
			FILES.saveProperties(currentProp);
		}
	}

	private static void loadProperties() {
		currentProp = new Properties();
		try {
			// // currentProp.load(new
			// FileInputStream("properties.properties"));
			// currentProp
			// .load(new FileInputStream(
			// "A:\\Google Drive\\AVTECH\\NPS\\Files\\bin\\properties.properties"));

			currentProp
					.load(new FileInputStream(
							"C:\\Users\\Austin\\Google Drive\\AVTECH\\NPS\\Files\\bin\\properties.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			Dialogs.errorDiagExit("Cannot Find Properties File! Please Reinstall The Game");
		}
	}

	private static void loadEverything() {
		Start s = new Start();
		s.checkImages();
	}

	private void checkImages() {
		String[] f = { FILES.backButton, FILES.backButtonRollover,
				FILES.mainPanelBackgroundImage, FILES.mulitplayerJoinRollover,
				FILES.multiplayerBackground, FILES.multiPlayerButton,
				FILES.multiplayerHostButton, FILES.multiplayerHostRollover,
				FILES.multiplayerJoinButton, FILES.multiPlayerRollover,
				FILES.optionBackground, FILES.optionButton,
				FILES.optionRollover, FILES.optionsControlButton,
				FILES.optionsControlRollover, FILES.singlePlayerBackground,
				FILES.singlePlayerButton, FILES.singlePlayerRollover,
				FILES.singlePlayerStartButton, FILES.singlePlayerStartRollover,
				FILES.exitButton };
		for (int i = 0; i < f.length; i++) {
			URL url = this.getClass().getClassLoader().getResource(f[i]);
			if (url == null) {
				Dialogs.errorDiagExit("Cannot Find the file from the url "
						+ f[i] + "!");
			}
		}
	}
}
