package main;

import java.util.Scanner;

public class Listener extends MainServerClass implements Runnable {
	public void run() {
		while (true) {
			Scanner s = new Scanner(System.in);
			String st = s.nextLine();
			if (st.equalsIgnoreCase("exit")) {
				shutdown();
			}
		}
	}
}
