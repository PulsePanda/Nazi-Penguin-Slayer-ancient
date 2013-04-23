package actionListeners;

import files.FILES;
import io.PlaySound;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import main.Dialogs;

public class SinglePlayerStartAction implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		// GameCore.start();
		/**
		 * delete the current world...
		 */
		File f = new File("../worlds/world.dat");
		if (f.exists())
			if (!f.delete())
				Dialogs.errorDiagExit("Could not start a new world! Please try again or check the FAQ");
		/**
		 * start the single player jar file, and close this program
		 */
		try {
			PlaySound.playSound(FILES.buttonClicked);
			Desktop desktop = Desktop.getDesktop();
			File openFile = new File("NPS Single Player.jar");
			desktop.open(openFile);
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
