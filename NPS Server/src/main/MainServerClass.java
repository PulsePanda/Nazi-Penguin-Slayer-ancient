package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

public class MainServerClass implements Serializable {

	static ServerSocket serverSocket = null;
	static Socket clientSocket = null;
	static ArrayList<Thread> threads = new ArrayList<Thread>();
	static boolean listening = true;
	public static ArrayList<String> filesToUpdateArray = new ArrayList<String>();
	public static ArrayList<String> filesToUpdateFinalArray = new ArrayList<String>();
	public static ArrayList<String> connectedIPs = new ArrayList<String>();
	static String filesToUpdateDir = "/home/pi/Programming/NPS/Files/bin/Files to Update.txt";
	static String filesToUpdateFinalDir = "/home/pi/Programming/NPS/Files/bin/Files to Update final Dirs.txt";
	public static ArrayList<String> loggedin = new ArrayList<String>();

	public static void main(String[] args) {

		filesToUpdateArray = readWholeFile(filesToUpdateDir);
		filesToUpdateFinalArray = readWholeFile(filesToUpdateFinalDir);

		System.out.println("Made by AVTECH Software\n");
		System.out.println("Version: " + MainServerThread.version + "\n");
		System.out.println("Server Started\nType 'exit' to exit.\n");

		try {
			serverSocket = new ServerSocket(6987);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		setup();

		while (listening) {
			try {
				clientSocket = serverSocket.accept();
				Thread t = new Thread(new MainServerThread(clientSocket));
				threads.add(t);
				t.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void setup() {
		// Frame f = new Frame("Server", 300, 300, false);
		Thread t = new Thread(new Listener());
		t.start();
	}

	public static ArrayList<String> getConnected() {
		return connectedIPs;
	}

	public static void setListening(boolean b) {
		listening = b;
	}

	public static void shutdown() {
		System.out.println("Shutting Down...");
		setListening(false);
		boolean isOneAlive = true;
		while (isOneAlive) {
			isOneAlive = false;
			for (int i = 0; i < threads.size(); i++) {
				if (threads.get(i).isAlive()) {
					isOneAlive = true;
				}
			}
		}
		System.out.println("Shutdown. Goodbye!");
		System.exit(0);
	}

	public static ArrayList<String> readWholeFile(String dir) {
		ArrayList<String> al = new ArrayList<String>();

		try {
			FileReader fileReader = new FileReader(dir);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// declare string variable and prime the read
			String stringRead = bufferedReader.readLine();

			while (stringRead != null) // end of the file
			{
				al.add(stringRead);
				stringRead = bufferedReader.readLine(); // read next line
			}

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
}
