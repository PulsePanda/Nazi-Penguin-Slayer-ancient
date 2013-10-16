package main;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import main.components.frames.Frame;
import main.components.panels.Panel;
import main.components.panels.PauseMenu;
import main.io.KeyClass;
import main.items.inventory.InventoryMenu;
import main.threads.PlayerThread;
import main.threads.RefreshThread;
import main.threads.TimeThread;

public class Start {
	static Frame f;
	private static PauseMenu pauseMenu = new PauseMenu("Menu");
	private static InventoryMenu invMenu = new InventoryMenu("Inventory");

	public static void main(String[] args) {
		Core core = new Core();
		core.setCore(core);

		f = new Frame("NPS", null, 1000, 600);
		core.setFrame(f);

		initPauseMenu();
		f.add(pauseMenu);
		core.setExitMenu(pauseMenu);

		initInvMenu();
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

		pauseMenu.visible(true);
		pauseMenu.visible(false);
	}

	private static void initPauseMenu() {
		JFrame f = Core.getFrame();
		int x = (f.getWidth() / 2) - (pauseMenu.getWidth() / 2), y = (f
				.getHeight() / 2) - (pauseMenu.getHeight() / 2);
		pauseMenu.setLocation(x, y);
	}

	private static void initInvMenu() {
		invMenu.setLocation(10, 10);
	}
}
