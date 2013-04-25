package game;

import java.io.File;

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
		core.save();
		core.start();
	}
}
