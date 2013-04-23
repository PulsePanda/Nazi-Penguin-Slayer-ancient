package game;

import io.FILES;

import java.awt.DisplayMode;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends GameCore {
	public GameFrame() {
		// frame.setLocationByPlatform(true);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new GamePanel(FRAME_WIDTH, FRAME_HEIGHT));
		frame.addKeyListener(new KeyClass());

		frame.setVisible(true);
	}

	public void disposeFrame() {
		frame.dispose();
	}

	public void refresh() {
		frame.setTitle("NPS " + FILES.VERSION + "    Level: " + GameCore.level);
		frame.repaint();
	}
}
