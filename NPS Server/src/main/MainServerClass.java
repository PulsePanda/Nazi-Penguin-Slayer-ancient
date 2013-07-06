package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

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

	// parts of the frame
	static JFrame frame = new JFrame("NPS Server");
	static JTabbedPane tabbedPane = new JTabbedPane();

	static JPanel outputPanel = new JPanel();
	static JTextArea textArea = new JTextArea();

	static JPanel commandPanel = new JPanel();

	static JButton activateAccount = new JButton("Activate Account");
	static JButton closeServer = new JButton("Close Server");

	public static void main(String[] args) {
		makeFrame();

		filesToUpdateArray = readWholeFile(filesToUpdateDir);
		filesToUpdateFinalArray = readWholeFile(filesToUpdateFinalDir);

		textArea.append("Made by AVTECH Software\n");
		textArea.append("Version: " + MainServerThread.version + "\n");
		textArea.append("Server Started\nType 'exit' to exit.\n");

		try {
			serverSocket = new ServerSocket(6987);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

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

	private static void makeFrame() {
		tabbedPane.addTab("Output", outputPanel);
		tabbedPane.addTab("Commands", commandPanel);

		/**
		 * set up panels
		 */
		// set up the output panel
		textArea.setBounds(0, 0, 500, 400);
		textArea.setEditable(false);
		outputPanel.setBounds(0, 0, 500, 400);
		outputPanel.setLayout(new BorderLayout());
		outputPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

		// set up the commands panel
		commandPanel.setBounds(0, 0, 500, 400);
		commandPanel.setLayout(new FlowLayout());
		activateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		commandPanel.add(activateAccount);

		closeServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		commandPanel.add(closeServer);

		frame.setSize(500, 400);
		frame.add(tabbedPane);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}

	public static ArrayList<String> getConnected() {
		return connectedIPs;
	}

	public static void setListening(boolean b) {
		listening = b;
	}

	public static void shutdown() {
		textArea.append("Shutting Down...");
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
		textArea.append("Shutdown. Goodbye!");
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
