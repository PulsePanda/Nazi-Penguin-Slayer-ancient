package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Properties;

public class KeyClass extends Core implements KeyListener {

	Properties settings;

	public KeyClass() {
		settings = getSettings();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == Integer.parseInt(settings.getProperty("up"))) {

		} else if (key == Integer.parseInt(settings.getProperty("down"))) {

		} else if (key == Integer.parseInt(settings.getProperty("left"))) {

		} else if (key == Integer.parseInt(settings.getProperty("right"))) {

		} else if (key == Integer.parseInt(settings.getProperty("attack"))) {

		} else if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (key == KeyEvent.VK_P) {
			if (!isPaused())
				pause();
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == Integer.parseInt(settings.getProperty("up"))) {

		} else if (key == Integer.parseInt(settings.getProperty("down"))) {

		} else if (key == Integer.parseInt(settings.getProperty("left"))) {

		} else if (key == Integer.parseInt(settings.getProperty("right"))) {

		} else if (key == Integer.parseInt(settings.getProperty("attack"))) {

		}
	}

	public void keyTyped(KeyEvent e) {
		int key = e.getKeyCode();

	}

}
