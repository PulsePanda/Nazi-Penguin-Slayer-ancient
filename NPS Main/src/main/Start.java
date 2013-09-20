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
	static String version;
	public static Properties currentProp;

	public static void main(String[] args) {
		loadProperties();

		version = currentProp.getProperty("version");
		FILES.version = version;

		// SplashScreen ss = new SplashScreen();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		Login.createFrame(); // use this in final version :D
		// ss.remove();
		// startMenuFrame(); // take this away when cleaning up
	}

	public static void startMenuFrame() {
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
			currentProp.load(new FileInputStream(FILES.propDir));
		} catch (IOException e) {
			e.printStackTrace();
			Dialogs.errorDiagExit("Cannot Find Properties File! Please Reinstall The Game");
		}
	}
}
