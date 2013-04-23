package actionListeners;

import io.PlaySound;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import main.Dialogs;

import files.FILES;

public class SinglePlayerResumeAction implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {

		try {
			PlaySound.playSound(FILES.buttonClicked);
			Desktop desktop = Desktop.getDesktop();
			File openFile = new File("NPS Single Player.jar");
			desktop.open(openFile);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
