package main;

import java.util.ArrayList;

import io.IO;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import panels.ControlsSelectionFrame;

import files.FILES;

public class Dialogs extends JOptionPane {

	static IO io = new IO();
	public static ControlsSelectionFrame c;

	public static void errorDiagExit(String msg) {
		showMessageDialog(null, msg);
		showMessageDialog(null, "The Game Will Now Shut Down");
		System.exit(0);
	}

	public static void msgDialog(String msg) {
		showMessageDialog(null, msg);
	}

	public static void changeControls() {
		c = new ControlsSelectionFrame();
	}
}
