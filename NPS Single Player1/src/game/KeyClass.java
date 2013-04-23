package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import thread.Shoot;

public class KeyClass extends GameCore implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		Thread t;
		t = new Thread(new Shoot());
		Shoot s = new Shoot();

		// for (int i = 0; i < commands.length; i++) {
		// switch (commands[i]) {
		// case
		// }
		// }
		if (key == KeyEvent.VK_ESCAPE) {
			escape();
		} else if (key == Integer.parseInt(prop.getProperty("up"))) {
			playerMoveY = -playerMoveSpeed;
		} else if (key == Integer.parseInt(prop.getProperty("down"))) {
			playerMoveY = playerMoveSpeed;
		} else if (key == Integer.parseInt(prop.getProperty("left"))) {
			playerMoveX = -playerMoveSpeed;
		} else if (key == Integer.parseInt(prop.getProperty("right"))) {
			playerMoveX = playerMoveSpeed;
		} else if (key == Integer.parseInt(prop.getProperty("attack"))) {
			if (!shooting && !s.getRunning()) {
				shooting = true;
				t.start();
			}
			// player.shoot();
		} else if (key == KeyEvent.VK_P) {
			pause();
		}
		e.consume();
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == Integer.parseInt(prop.getProperty("up"))) {
			playerMoveY = 0;
		} else if (key == Integer.parseInt(prop.getProperty("down"))) {
			playerMoveY = 0;
		} else if (key == Integer.parseInt(prop.getProperty("left"))) {
			playerMoveX = 0;
		} else if (key == Integer.parseInt(prop.getProperty("right"))) {
			playerMoveX = 0;
		} else if (key == Integer.parseInt(prop.getProperty("attack"))) {
			if (shooting) {
				shooting = false;
			}
		}
		e.consume();
	}

	public void keyTyped(KeyEvent e) {

	}
}
