package game;

import io.FILES;
import io.IO;
import io.PlaySound;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PauseFrame extends JFrame {

	JButton resume, saveAndExit;
	// JLabel resume, saveAndExit;

	JFrame f = this;
	IO io = new IO();

	public PauseFrame() {
	}

	public void start() {

		int fw = Core.getWidth() / 2, fh = Core.getHeight() / 2;

		setLayout(null);
		setAlwaysOnTop(true);
		setSize(fw, fh);
		setUndecorated(true);

		/**
		 * http://stackoverflow.com/questions/2856480/resizing-a-imageicon-in-a-
		 * jbutton look at that website to see how to resize the jbutton images
		 * :D
		 */
		int w = fw - 20, h = (fh / 3) - 20, x = (fw / 2) - (w / 2), y = (fh / 3)
				- (h / 2), offset = 0;

		ImageIcon resumeIcon = new ImageIcon(
				io.getImage(FILES.resumeButtonImage));
		ImageIcon resumeRoll = new ImageIcon(
				io.getImage(FILES.resumeButtonOverlay));
		Image resumeimg = resumeIcon.getImage().getScaledInstance(w + 10, h,
				java.awt.Image.SCALE_SMOOTH);
		Image resumeroll = resumeRoll.getImage().getScaledInstance(w + 10, h,
				java.awt.Image.SCALE_SMOOTH);
		resumeIcon = new ImageIcon(resumeimg);
		resumeRoll = new ImageIcon(resumeroll);

		resume = new JButton("resume");
		resume.setIcon(resumeIcon);
		resume.setBounds(x, y, w, h);
		resume.setBorderPainted(false);
		resume.setRolloverIcon(resumeRoll);
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Core.pause();
			}
		});

		ImageIcon buttonIcon = new ImageIcon(
				io.getImage(FILES.saveAndExitButtonImage));
		ImageIcon buttonRoll = new ImageIcon(
				io.getImage(FILES.saveAndExitButtonOverlay));
		Image newimg = buttonIcon.getImage().getScaledInstance(w + 10, h,
				java.awt.Image.SCALE_SMOOTH);
		Image newroll = buttonRoll.getImage().getScaledInstance(w + 10, h,
				java.awt.Image.SCALE_SMOOTH);
		buttonIcon = new ImageIcon(newimg);
		buttonRoll = new ImageIcon(newroll);

		saveAndExit = new JButton("saveAndExit");
		saveAndExit.setIcon(buttonIcon);
		y = (fh / 3) + (fh / 3) - (h / 2);
		saveAndExit.setBounds(x, y + offset, w, h);
		saveAndExit.setBorderPainted(false);
		saveAndExit.setRolloverIcon(buttonRoll);
		saveAndExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Core.save();
				Core.stop();
			}
		});

		add(resume);
		add(saveAndExit);
		setLocationRelativeTo(Core.frame);
		setVisible(true);
	}
}
