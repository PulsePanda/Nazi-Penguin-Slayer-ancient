package main;

import javax.swing.JProgressBar;

public class Start {
	static Frame f;

	public static void main(String[] args) {
		Core core = new Core();
		core.setCore(core);

		f = new Frame("NPS", null, 1000, 600);
		core.setFrame(f);
		f.add(new Panel());
		f.setLocationRelativeTo(null);
		Thread refreshThread = new Thread(new RefreshFrame(f));
		refreshThread.start();
	}
}
