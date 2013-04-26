package game;

import io.FILES;
import io.IO;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	JButton resume, options, exit;
	int w, h;
	static Graphics graphics;
	private Core core = new Core();
	private IO io = new IO();

	public GamePanel(int w, int h) {
		setUpButtons();
		graphics = getGraphics();
		setLayout(null);
		this.w = w;
		this.h = h;
	}

	private void setUpButtons() {
		resume = new JButton();
		// resume.setIcon(new ImageIcon(io.getImage(""))); // add!!!!
		resume.setBounds(core.getWidth() / 2 - (250 / 2),
				(core.getHeight() / 4), 250, 50);
		resume.setBorderPainted(false);
		// resume.setRolloverIcon(new ImageIcon(io.getImage(""))); // add!!!!
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				core.pause();
			}
		});
		resume.setVisible(false);

		options = new JButton();
		// options.setIcon(new ImageIcon(io.getImage(""))); // add!!!!
		options.setBounds(core.getWidth() / 2 - (250 / 2),
				(core.getHeight() / 4) * 2, 250, 50);
		options.setBorderPainted(false);
		// options.setRolloverIcon(new ImageIcon(io.getImage(""))); // add!!!!
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		options.setVisible(false);

		exit = new JButton();
		// exit.setIcon(new ImageIcon(io.getImage(""))); // add!!!!
		exit.setBounds(core.getWidth() / 2 - (250 / 2),
				(core.getHeight() / 4) * 3, 250, 50);
		// exit.setBounds(275, 400, 250, 50);
		exit.setBorderPainted(false);
		// exit.setRolloverIcon(new ImageIcon(io.getImage(""))); // add!!!!
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				core.stop();
			}
		});
		exit.setVisible(false);

		add(resume);
		add(options);
		add(exit);
	}

	public Dimension getPreferredSize() {
		return new Dimension(w, h);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// everything else
		exit.setVisible(false);
		resume.setVisible(false);
		options.setVisible(false);
		g.drawImage(io.getImage(FILES.gameBackground), 0, 0, core.getWidth(),
				core.getHeight(), null);

		// if paused, show overlay
		// if (core.isPaused()) {
		// g.drawImage(io.getImage(FILES.pauseOverlay), 0, 0, core.getWidth(),
		// core.getHeight(), null);
		//
		// resume.setVisible(true);
		// options.setVisible(true);
		// exit.setVisible(true);
		// }
	}
}
