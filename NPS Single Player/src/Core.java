import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

public class Core {

	private static Properties properties = new Properties();
	private static boolean running = false, paused = false;
	private static Frame frame;
	private static PanelMain mainPanel;

	public Core() {
		try {
			properties.load(new FileInputStream("properties.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			Dialogs.errorDiagExit("Could not load properties! Please reinstall or check FAQ's");
		}
	}

	public void start() {
		setRunning(true);
		frame = new Frame("Nazi Penguin Slayer", FILES.taskbarImg, 1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addKeyListener(new KeyClass());

		mainPanel = new PanelMain();
		frame.add(mainPanel);

		Thread t = new Thread(new RefreshFrame());
		t.start();
	}

	public void stop() {
		setRunning(false);
		setPaused(false);
	}

	private void setPaused(boolean b) {
		paused = b;
	}

	public static Frame getFrame() {
		return frame;
	}

	public static void pause() {
		if (isPaused())
			paused = false;
		else if (!isPaused())
			paused = true;
		else
			return;
	}

	public static boolean isPaused() {
		return paused;
	}

	public static void setRunning(boolean b) {
		running = b;
	}

	public static boolean isRunning() {
		return running;
	}
}
