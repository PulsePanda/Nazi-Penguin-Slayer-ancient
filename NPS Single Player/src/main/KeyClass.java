package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Properties;

public class KeyClass implements KeyListener {

	private Core core;
	private Properties properties;
	private Player player;

	public KeyClass() {
		core = Core.getCore();
		properties = core.getProperties();
		player = core.getPlayer();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == Integer.parseInt(properties.getProperty("right"))) {
			player.setMoveX(-player.getMoveSpeed() / 4);
		} else if (key == Integer.parseInt(properties.getProperty("left"))) {
			player.setMoveX(player.getMoveSpeed() / 4);
		} else if (key == Integer.parseInt(properties.getProperty("jump"))) {
			player.jump();
		} else if (key == Integer.parseInt(properties.getProperty("attack"))) {

		} else if (key == Integer.parseInt(properties.getProperty("duck"))) {

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == Integer.parseInt(properties.getProperty("right"))) {
			player.setMoveX(0);
		} else if (key == Integer.parseInt(properties.getProperty("left"))) {
			player.setMoveX(0);
		} else if (key == Integer.parseInt(properties.getProperty("jump"))) {

		} else if (key == Integer.parseInt(properties.getProperty("attack"))) {

		} else if (key == Integer.parseInt(properties.getProperty("duck"))) {

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
