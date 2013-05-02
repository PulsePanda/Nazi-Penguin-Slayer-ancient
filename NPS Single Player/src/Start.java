public class Start {
	static Frame mainFrame;
	static Core core;

	public static void main(String[] args) {
		core = new Core();
		core.start();
	}

	public static Frame getFrame() {
		return mainFrame;
	}
}
