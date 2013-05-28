package panels;

import java.awt.Dimension;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private int width, height;

	public Panel(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
