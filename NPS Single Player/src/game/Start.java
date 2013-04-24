package game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.*;

public class Start {

	static Core core;

	public static void main(String[] args0) {
		core = new Core();
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// core.setWidth(screenSize.width);
		// core.setHeight(screenSize.height);
		File f = new File("../worlds/world.dat");
		if (f.exists())
			core.load();
		core.start();
	}
}
