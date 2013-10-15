package main.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Properties;

import javax.swing.JOptionPane;

import main.Core;
import main.sprites.Player;

public class KeyClass implements KeyListener {

	private Core core;
	private Properties properties;
	private Player player;
	private boolean jumpPushed = false;

	public KeyClass() {
		core = Core.getCore();
		properties = core.getProperties();
		player = core.getPlayer();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == Integer.parseInt(properties.getProperty("right"))) {
			player.setMoveX(-player.getMoveSpeed());
		} else if (key == Integer.parseInt(properties.getProperty("left"))) {
			player.setMoveX(player.getMoveSpeed());
		} else if (key == Integer.parseInt(properties.getProperty("jump"))) {
			if (!jumpPushed) {
				jumpPushed = true;
				player.jump();
			}
		} else if (key == Integer.parseInt(properties.getProperty("attack"))) {

		} else if (key == Integer.parseInt(properties.getProperty("duck"))) {

		} else if (key == Integer.parseInt(properties.getProperty("inventory"))) {
			core.getInventoryMenu().visible(true);
			core.getInventoryMenu().requestFocus();
		} else if (key == KeyEvent.VK_ESCAPE) {
			core.getExitMenu().visible(true);
			core.getExitMenu().requestFocus();
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
			jumpPushed = false;
		} else if (key == Integer.parseInt(properties.getProperty("attack"))) {

		} else if (key == Integer.parseInt(properties.getProperty("duck"))) {

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
