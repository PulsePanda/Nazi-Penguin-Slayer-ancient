package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainServerClass implements Serializable {

	static ServerSocket serverSocket = null;
	static Socket clientSocket = null;
	static ArrayList<Thread> threads = new ArrayList<Thread>();
	static boolean listening = true;
	public static ArrayList<String> filesToUpdateArray = new ArrayList<String>();
	public static ArrayList<String> filesToUpdateFinalArray = new ArrayList<String>();
	public static ArrayList<String> connectedIPs = new ArrayList<String>();
	static String filesToUpdateDir = "A:\\test\\Files to Update.txt";
	static String filesToUpdateFinalDir = "A:\\test\\Files to Update final Dirs.txt";
	static String logFileDir = "A:\\test\\logs";
	public static String updateDir = "A:\\test\\updates";
	public static ArrayList<String> loggedin = new ArrayList<String>();

	public static String time, date;
	public static File logFile;

	// parts of the frame
	static JFrame frame = new JFrame("NPS Server");
	static JTabbedPane tabbedPane = new JTabbedPane();

	static JPanel outputPanel = new JPanel();
	static JTextArea textArea = new JTextArea();

	static JPanel commandPanel = new JPanel();

	static JButton activateAccount = new JButton("Activate Account");
	static JButton closeServer = new JButton("Close Server");
	static JButton changeVersion = new JButton("Change Server Version");

	public static void main(String[] args) {
		makeFrame();

		filesToUpdateArray = readWholeFile(filesToUpdateDir);
		filesToUpdateFinalArray = readWholeFile(filesToUpdateFinalDir);

		updateArea("Made by AVTECH Software");
		updateArea("Version: " + MainServerThread.version);
		updateArea("Server Started");

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
		createNewLogFile();
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
				activateAccount();
			}
		});
		commandPanel.add(activateAccount);

		closeServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shutdown();
			}
		});
		commandPanel.add(closeServer);

		changeVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

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
		updateArea("Shutting Down...");
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
		updateArea("Shutdown. Goodbye!");
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

	public static void activateAccount() {
		final JDialog j = new JDialog();
		j.setLayout(null);
		j.setSize(330, 100);
		j.setResizable(false);

		final JTextField email = new JTextField();
		email.setBounds(10, 10, 300, 25);

		JButton activate = new JButton("Activate");
		activate.setBounds(10, 35, 300, 25);
		activate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Properties p = new Properties();
				String[] alf;
				File originDir = new File("A:\\test\\users");
				alf = originDir.list();

				for (int i = 0; i < alf.length; i++) {
					try {
						String dir = originDir.getPath() + "\\" + alf[i];
						p.load(new FileInputStream(dir));

						if (p.getProperty("email").equals(email.getText())) {
							p.setProperty("activated", "true");
							String user = p.getProperty("username");
							p.save(new FileOutputStream(new File(dir)), user
									+ " user properties");
							updateArea("User account " + user
									+ " has been activated.");
						}
					} catch (Exception e) {
						updateArea("User was failed to be activated.");
						e.printStackTrace();
					}
				}

				j.dispose();
			}
		});

		j.add(email);
		j.add(activate);

		j.setLocationRelativeTo(frame);
		j.setVisible(true);
	}

	private static void createNewLogFile() {
		String filename;
		time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance()
				.getTime());
		date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance()
				.getTime());

		filename = logFileDir + "\\" + date + ".txt";

		logFile = new File(filename);
		if (!logFile.exists())
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				updateArea("Unable to create the new log file at " + filename);
			}
	}

	public static void updateArea(String s) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(logFile, true));
			writer.write(time + ": " + s);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			textArea.append(time + ": " + "Unable to write to the log file!\n");
			e.printStackTrace();
		}
		textArea.append(time + ": " + s + "\n");
	}
}
