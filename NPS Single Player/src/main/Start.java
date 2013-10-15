package main;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Start {
	static Frame f;
	private static GameMenu exitMenu = new GameMenu("Menu");

	public static void main(String[] args) {
		Core core = new Core();
		core.setCore(core);

		f = new Frame("NPS", null, 1000, 600);
		core.setFrame(f);
		initExitMenu();
		f.add(exitMenu);
		core.setExitMenu(exitMenu);
		f.add(new Panel());
		f.setLocationRelativeTo(null);
		f.addKeyListener(new KeyClass());
		Thread refreshThread = new Thread(new RefreshThread(f));
		Thread playerThread = new Thread(new PlayerThread());
		Thread timeThread = new Thread(new TimeThread());

		refreshThread.setName("refresh thread");
		refreshThread.start();

		playerThread.setName("player Thread");
		playerThread.start();

		timeThread.setName("time thread");
		timeThread.start();

		exitMenu.setVisible(true);
	}

	private static void initExitMenu() {
		JFrame f = Core.getFrame();
		int x = (f.getWidth() / 2) - (exitMenu.getWidth() / 2), y = (f
				.getHeight() / 2) - (exitMenu.getHeight() / 2);
		exitMenu.setLocation(x, y);
	}
}
