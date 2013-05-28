package panels;

import java.awt.Dialog;

import javax.swing.*;

public class OptionsWindow {
	private static JFrame parent;

	public OptionsWindow(JFrame frame) {
		parent = frame;
		init();
	}

	private void init() {
		JDialog dialog = new JDialog(parent, true);
		dialog.setSize(400, 220);
//		dialog.setLocationRelativeTo(relativeTo);
		
		dialog.setVisible(true);
	}

	private void addThings() {

	}
}
