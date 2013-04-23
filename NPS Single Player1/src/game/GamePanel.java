package game;

import io.FILES;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	int width, height;
	static Graphics graphics;
	GameCore core = new GameCore();

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		graphics = getGraphics();
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		core.graphics = getGraphics();
		g.setColor(Color.WHITE);
		g.drawImage(core.gameBackground, 0, 0, width, height, null);
		g.drawImage(core.player.getImage(), core.player.getX(),
				core.player.getY(), core.player.getWidth(),
				core.player.getHeight(), null);
		g.drawString("NPS " + FILES.VERSION + "            AVTECH Software",
				15, 15);
		g.drawString(("Level: " + GameCore.level), 500, 50);

		for (int i = 0; i < core.bullet.size(); i++) {
			Bullet b = core.bullet.get(i);
			if (b.isVisible()) {
				g.drawImage(b.getImage(), b.getX(), b.getY(), b.getWidth(),
						b.getHeight(), null);
			}
		}

		for (int i = 0; i < core.enemy.size(); i++) {
			Enemy e = core.enemy.get(i);
			if (e.isVisible())
				g.drawImage(e.getImage(), e.getX(), e.getY(), e.getWidth(),
						e.getHeight(), null);
		}

	}
}
