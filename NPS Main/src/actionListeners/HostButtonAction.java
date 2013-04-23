package actionListeners;

import files.FILES;
import io.PlaySound;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HostButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {

		try {
			PlaySound.playSound(FILES.buttonClicked);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Desktop desktop = Desktop.getDesktop();
			File openFile = new File(
					"NPS Multi Host.jar");
			desktop.open(openFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
