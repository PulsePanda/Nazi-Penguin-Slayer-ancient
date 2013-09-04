package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Connection implements Serializable {
	String IP, port;
	String message = "";
	Socket socket;
	int numberOfFiles;
	ObjectInputStream in;
	ObjectOutputStream out;
	JProgressBar bar;
	static int barVal = 0;

	public Connection(String IP, String port, JProgressBar b, String goal) {
		this.IP = IP;
		this.port = port;
		bar = b;

		try {
			socket = new Socket(IP, Integer.parseInt(port));
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e1) {
			e1.printStackTrace();
			UpdateFrame.serverOnline = false;
			return;
		}

		switch (goal) {
		case "update":
			update();
			break;
		}
	}

	public void update() {
		send("update");
		if (!read().equals("ok"))
			return;

		if (!read().equals("get version"))
			return;
		sendVersion();

		if (!read().equals("updating"))
			return;
		send("ok");

		numberOfFiles = (int) read();
		send("ok");
		bar.setMaximum(numberOfFiles);

		/**
		 * as far as i have gotten....
		 */
		for (int i = 0; i < numberOfFiles; i++) {
			UpdateObject uo = (UpdateObject) read();
			copyUpdate(uo);
			send("ok");
			barVal++;
			bar.setValue(barVal);
			bar.repaint();
		}
		if (!read().equals("done"))
			return;
		send("ok");

		/**
		 * unpack and move the files now
		 */
		// get the list of zips downloaded
		File dir = new File("../patches/");
		String[] list = dir.list();

		for (int i = 0; i < list.length; i++) {
			String fileName = "../patches/" + list[i];
			unZipIt(fileName, "../patches/temp");
		}
		// all of the zip files are now unzipped to the temp folder

		// get a list of all the 'temp' files
		dir = new File("../patches/temp/");
		list = dir.list();

		String binDir = "../bin/";
		String imgDir = "../graphic/";
		String soundDir = "../audio/";

		for (int i = 0; i < list.length; i++) {
			String fileName = list[i];
			String filePath = "../patches/temp/" + fileName;

			// if the file belongs in the bin folder
			if (fileName.contains(".jar") || fileName.contains(".properties")) {
				// delete the old file
				File oldFile = new File(binDir + fileName);
				oldFile.delete();
				// move the new file to the binDir
				File newFile = new File(filePath);
				newFile.renameTo(new File(binDir + fileName));
			}

			// if the file belongs in the graphic folder
			if (fileName.contains(".png") || fileName.contains(".jpg")
					|| fileName.contains(".jpeg") || fileName.contains(".gif")) {
				// delete the old file
				File oldFile = new File(imgDir + fileName);
				oldFile.delete();
				// move the new file to the binDir
				File newFile = new File(filePath);
				newFile.renameTo(new File(imgDir + fileName));
			}

			// if the file belongs in the audio file
			if (fileName.contains(".wav") || fileName.contains(".mp3")
					|| fileName.contains(".mp4")) {
				// delete the old file
				File oldFile = new File(soundDir + fileName);
				oldFile.delete();
				// move the new file to the binDir
				File newFile = new File(filePath);
				newFile.renameTo(new File(soundDir + fileName));
			}

			// delete all the files in 'patch'
			deleteEverythingInPatches();
		}
	}

	private void deleteEverythingInPatches() {
		File dir = new File("../patches/");
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			files[i].delete();
		}
	}

	public void closeEverything() {
		try {
			out.flush();
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copyUpdate(UpdateObject o) {
		try {
			FileOutputStream fileOutStream = new FileOutputStream("../patches/"
					+ o.getFileName());
			fileOutStream.write(o.getFile());
			fileOutStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendVersion() {
		String version = null;
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("properties.properties"));
			version = p.getProperty("version");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// FileReader fileReader;
		// String version = null;
		// try {
		// fileReader = new FileReader(
		// "C:\\Program Files\\AVTECH\\NPS\\Files\\bin\\version.txt");
		// BufferedReader bufferedReader = new BufferedReader(fileReader);
		//
		// version = bufferedReader.readLine();
		//
		// bufferedReader.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		send(version);
	}

	public Object read() {
		Object o = null;
		try {
			o = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public void send(Object o) {
		try {
			out.writeObject(o);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unZipIt(String zipFile, String outputFolder) {
		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(
					new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator
						+ fileName);

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
