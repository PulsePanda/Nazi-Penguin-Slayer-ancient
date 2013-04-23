package actionListeners;

import files.FILES;
import io.PlaySound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Frame;

public class OptionAction implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		Frame.changePanel("OPTIONS");
		Frame.dp.changeImage(4);
		try {
			PlaySound.playSound(FILES.buttonClicked);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
