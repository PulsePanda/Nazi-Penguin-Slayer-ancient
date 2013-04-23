package actionListeners;

import main.Frame;
import files.FILES;
import io.PlaySound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Frame.changePanel("MAINSCREEN");
		Frame.dp.changeImage(1);
		try {
			PlaySound.playSound(FILES.buttonClicked);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
