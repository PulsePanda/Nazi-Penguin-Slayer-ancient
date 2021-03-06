package game;

import io.IO;
import javax.swing.JOptionPane;

public class Dialogs extends JOptionPane {

	static IO io = new IO();

	public static void errorDiagExit(String msg) {
		showMessageDialog(null, msg);
		showMessageDialog(null, "The Game Will Now Shut Down");
		System.exit(0);
	}

	public static void msgDialog(String msg) {
		showMessageDialog(null, msg);
	}
}
