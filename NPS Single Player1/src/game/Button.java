package game;

import java.awt.Rectangle;
import java.awt.event.MouseListener;

import io.FILES;
import io.IO;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Button {
	IO io = new IO();
	String imgDir;
	JLabel label;

	public Button(String imgDir) {
		this.imgDir = imgDir;
		label = new JLabel(new ImageIcon(io.getImage(imgDir)));
	}

	public void addMouseListener(MouseListener ml) {
		label.addMouseListener(ml);
	}

	public void setBounds(Rectangle r) {
		label.setBounds(r);
	}

	public void setVisible(boolean b) {
		label.setVisible(b);
	}

	public boolean isVisible() {
		return label.isVisible();
	}

	public String getImageDir() {
		return imgDir;
	}

}
