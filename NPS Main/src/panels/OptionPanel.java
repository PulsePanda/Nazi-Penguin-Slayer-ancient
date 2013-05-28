package panels;

import io.IO;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import actionListeners.BackButtonAction;
import actionListeners.ControlButtonAction;
import actionListeners.ExitAction;

import main.Frame;
import files.FILES;

public class OptionPanel extends Panel {

	IO io = new IO();
	JButton controls, back, exit;

	public OptionPanel(int w, int h) {
		super(w, h);
		setLayout(null);
		makeButtons();
	}

	private void makeButtons() {
		back = new JButton();
		back.setIcon(new ImageIcon(io.getImage(FILES.backButton)));
		back.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.backButtonRollover)));
		back.setBorderPainted(false);
		back.addActionListener(new BackButtonAction());
		back.setBounds(275, 325, 250, 50);

		controls = new JButton();
		controls.setIcon(new ImageIcon(io.getImage(FILES.optionsControlButton)));
		controls.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.optionsControlRollover)));
		controls.setBorderPainted(false);
		controls.addActionListener(new ControlButtonAction());
		controls.setBounds(275, 250, 250, 50);

		// exit = new JButton();
		// exit.setIcon(new ImageIcon(io.getImage(FILES.exitButton)));
		// exit.setBounds(750, 0, 25, 25);
		// exit.setBorderPainted(false);
		// exit.setRolloverIcon(new ImageIcon(io.getImage(FILES.exitRollover)));
		// exit.addActionListener(new ExitAction());

		// add(exit);
		add(back);
		add(controls);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(io.getImage(FILES.optionBackground), 0, -40, Frame.getH(),
				Frame.getW(), null);
	}
}
