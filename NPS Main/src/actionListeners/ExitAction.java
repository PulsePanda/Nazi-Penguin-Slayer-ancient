package actionListeners;

import files.FILES;
import io.PlaySound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Properties;

import main.Dialogs;

public class ExitAction implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("properties.properties"));
			p.setProperty("running", "false");
			FILES.saveProperties(p);
			Dialogs.msgDialog("SAVED PROPERTIES FILE");

			PlaySound.playSound(FILES.buttonClicked);
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
