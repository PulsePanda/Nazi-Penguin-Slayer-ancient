package panels;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class NewsPanel extends Panel {
	private JEditorPane display;

	public NewsPanel(int width, int height) {
		super(width, height);

		display = new JEditorPane();
		display.setEditable(false);
		JScrollPane jsp = new JScrollPane(display);
		jsp.setBounds(0, 0, width, height);
		try {
			display.setPage("http://npslayer.tumblr.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		add(jsp);
	}

	public void paint(Graphics g) {
		g.drawRect(0, 0, width, height);
	}
}
