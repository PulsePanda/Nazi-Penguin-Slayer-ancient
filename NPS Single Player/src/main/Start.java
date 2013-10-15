package main;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Start {
	static Frame f;
	private static PauseMenu exitMenu = new PauseMenu("Menu");
	private static InventoryMenu invMenu = new InventoryMenu("Inventory");

	public static void main(String[] args) {
		Core core = new Core();
		core.setCore(core);

		f = new Frame("NPS", null, 1000, 600);
		core.setFrame(f);

		initExitMenu();
		f.add(exitMenu);
		core.setExitMenu(exitMenu);

		initPauseMenu();
		f.add(invMenu);
		core.setInventoryMenu(invMenu);

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

		exitMenu.visible(true);
		exitMenu.visible(false);
	}

	private static void initExitMenu() {
		JFrame f = Core.getFrame();
		int x = (f.getWidth() / 2) - (exitMenu.getWidth() / 2), y = (f
				.getHeight() / 2) - (exitMenu.getHeight() / 2);
		exitMenu.setLocation(x, y);
	}

	private static void initPauseMenu() {
		invMenu.setMoveable(true);
	}
}
