package actionListeners;

import files.FILES;
import io.PlaySound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.DragPanel;

import main.Frame;

public class MultiPlayerAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Frame.changePanel("MULTIPLAYER");
		Frame.dp.changeImage(3);
		try {
			PlaySound.playSound(FILES.buttonClicked);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
