package game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PauseFrame extends JFrame {

	JButton resume, saveAndExit;
	JFrame f = this;

	public PauseFrame() {

		resume = new JButton("resume");
		// resume.setIcon(new ImageIcon(io.getImage(FILES.resumeButtonImage)));
		resume.setBounds((Core.getWidth() / 4) - (250 / 2), 75, 250, 50);
		resume.setBorderPainted(false);
		// resume.setRolloverIcon(new ImageIcon(io
		// .getImage(FILES.resumeButtonOverlay)));
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Core.pause();
			}
		});

		saveAndExit = new JButton("saveAndExit");
		// saveAndExit.setIcon(new ImageIcon(io
		// .getImage(FILES.saveAndExitButtonImage)));
		saveAndExit.setBounds((Core.getWidth() / 4) - (250 / 2), 175, 250, 50);
		saveAndExit.setBorderPainted(false);
		// saveAndExit.setRolloverIcon(new ImageIcon(io
		// .getImage(FILES.saveAndExitButtonOverlay)));
		saveAndExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Core.save();
				Core.stop();
			}
		});

		setLayout(null);
		setAlwaysOnTop(true);
		setSize(Core.getWidth() / 2, Core.getHeight() / 2);
		setUndecorated(true);

		add(resume);
		add(saveAndExit);
		setLocationRelativeTo(Core.frame);
		setVisible(true);
	}
}
