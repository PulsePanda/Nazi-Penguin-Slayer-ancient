package panels;

import files.FILES;
import io.IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Properties;

import javax.swing.JFrame;

import main.Start;

public class ControlKeyClass implements KeyListener {

	String button;
	JFrame frame;
	int key, lineNum;

	public ControlKeyClass(String s, JFrame f) {
		button = s;
		frame = f;
	}

	public void keyPressed(KeyEvent arg0) {
		key = arg0.getKeyCode();
		writeControls();
		frame.dispose();
		new ControlsSelectionFrame();

		arg0.consume();
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void writeControls() {
		IO io = new IO();
		Properties prop = Start.currentProp;
		prop.setProperty(button, Integer.toString(key));
		System.out.println(button);
		FILES.saveProperties(prop);
	}
}
