package main;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.*;

public class UpdateFrame extends JFrame implements Serializable {
	// JLabel l;
	JLabel title;
	static boolean serverOnline = true;
	JProgressBar bar = new JProgressBar();

	public static void main(String[] args) {
		Properties p = new Properties();
		try {
//			p.load(new FileInputStream("properties.properties"));
			p.load(new FileInputStream("A:\\Google Drive\\AVTECH\\NPS\\Files\\bin\\properties.properties"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// check to see if the game is already running
		if (p.getProperty("running").equals("true")) {
			// if the game is running
			JOptionPane.showMessageDialog(null, "NPS is already running!");
			System.exit(0);
		} else {
			// if the program isnt running
			p.setProperty("running", "true");
			try {
				p.save(new FileOutputStream(new File("A:\\Google Drive\\AVTECH\\NPS\\Files\\bin\\properties.properties")),
						"Properties File for Nazi Penguin Slayer");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

//		SplashScreen ss = new SplashScreen();
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
		}
//		ss.remove();
		UpdateFrame uf = new UpdateFrame();
		// uf.openGame();
	}

	public UpdateFrame() {
		this.setLocationRelativeTo(null);
		setTitle("Checking for Updates...");
		setLayout(null);
		setSize(400, 100);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		setUpAllTheThings();

		Connection u = new Connection("nazipenguinslayer.no-ip.org", "6987",
				bar, "update");

		bar.setMaximum(1);
		bar.setMinimum(0);
		bar.setValue(1);
		bar.repaint();

		// openGame();
		this.dispose();
		if (!serverOnline) {
			JOptionPane
					.showMessageDialog(
							null,
							"Server is offline. Make sure you are\nconnected to the internet, and try again."
									+ "\nIf problem continues, please check our FAQ's");
		} else
			try {
				Desktop desktop = Desktop.getDesktop();
				File openFile = new File("NPS Main.jar");
				desktop.open(openFile);
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void setUpAllTheThings() {
		/**
		 * using a gif image :)
		 */
		// File f = new File(("C:\\Users\\Austin\\Desktop\\hi.gif"));
		// URL url = null;
		// try {
		// url = f.toURI().toURL();
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// l = new JLabel(new ImageIcon(url));
		// setLayout(null);
		// l.setBounds(0, 0, 200, 200);
		// add(l);
		title = new JLabel("Checking For Updates...");
		title.setFont(new Font("Arial", Font.BOLD, 32));
		title.setBounds(10, 0, 500, 50);
		add(title);

		bar.setBounds(10, 45, 370, 20);
		bar.setMinimum(0);
		add(bar);

		// Update c = new Update("174.20.11.236", "6987");
	}

	public void openGame() {
		try {
			Desktop desktop = Desktop.getDesktop();
			File openFile = new File("NPS Main.jar");
			desktop.open(openFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		title.repaint();
		bar.repaint();
	}
}
