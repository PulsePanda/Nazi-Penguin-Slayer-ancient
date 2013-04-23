package actionListeners;

import files.FILES;
import io.PlaySound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Dialogs;

import panels.OptionPanel;

public class ControlButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Dialogs.changeControls();
		try {
			PlaySound.playSound(FILES.buttonClicked);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
