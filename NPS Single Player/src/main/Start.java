package main;

import javax.swing.JProgressBar;

public class Start {
	static Frame f;

	public static void main(String[] args) {
		Core core = new Core();
		core.setCore(core);
		core.setFrame(f);

		f = new Frame("NPS", null, 1000, 600);
		f.add(new Panel());
		f.setLocationRelativeTo(null);
		Thread refreshThread = new Thread(new RefreshFrame(f));
		refreshThread.start();
	}
}
