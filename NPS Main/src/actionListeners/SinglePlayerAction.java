package actionListeners;

import files.FILES;
import io.PlaySound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Dialogs;
import main.Frame;

public class SinglePlayerAction implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		Frame.changePanel("SINGLEPLAYER");
		Frame.dp.changeImage(2);

		try {
			PlaySound.playSound(FILES.buttonClicked);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
