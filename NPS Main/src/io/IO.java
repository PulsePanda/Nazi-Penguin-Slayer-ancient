package io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Dialogs;

public class IO {
	public BufferedImage getImage(String dir) {
		BufferedImage img = null;
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			Dialogs.errorDiagExit("Cannot Find the file from the dir " + dir
					+ "!");
		}
		try {
			img = ImageIO.read(dirFile);
		} catch (IOException e) {
		}
		return img;
	}

	public void copyFile(String dir, String dest, boolean isInJar) {
		ArrayList al = new ArrayList();
		if (!isInJar) {
			al = readWholeFile(dir);

			try {
				eraseFile(dest);

				FileWriter fileWriter = new FileWriter(dest, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				for (int a = 0; a < al.size(); a++) {
					bufferedWriter.write((String) al.get(a));
					bufferedWriter.newLine();
				}

				bufferedWriter.close();
			} catch (IOException ioexception) {
				Dialogs.errorDiagExit("Could not write to " + dest);
			}
		} else {
			int i = 1;
			String s = this.readSpecificFromJar(dir, i);
			do {
				s = readSpecificFromJar(dir, i);
				if (s != null)
					al.add(s);
				i++;
			} while (s != null);
			try {
				eraseFile(dest);

				FileWriter fw = new FileWriter(dest, true);
				BufferedWriter bw = new BufferedWriter(fw);

				for (int a = 0; a < al.size(); a++) {
					bw.write((String) al.get(a));
					bw.newLine();
				}

				bw.close();
			} catch (Exception e) {
				Dialogs.errorDiagExit("Could not write to " + dest);
			}
		}
	}

	public void eraseFile(String dest) {
		FileWriter fw;
		try {
			fw = new FileWriter(dest, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readSpecificLine(String controlsfilefinaldir, int lineNum) {
		String stringRead = "";

		try {
			FileReader fileReader = new FileReader(controlsfilefinaldir);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			stringRead = bufferedReader.readLine();

			for (int i = 1; i < lineNum; i++) {
				stringRead = bufferedReader.readLine(); // read next line
			}

			bufferedReader.close();
		} catch (FileNotFoundException filenotfoundexxeption) {
			Dialogs.errorDiagExit("File " + controlsfilefinaldir
					+ " does not exist");
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
		return stringRead;
	}

	public String readSpecificFromJar(String dir, int line) {
		String read = null;
		try {
			InputStream in = getClass().getResourceAsStream(dir);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(in));

			/**
			 * declare string variable and prime the read
			 */
			read = bufferedReader.readLine();

			for (int i = 1; i < line; i++) {
				read = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException ioexception) {
			Dialogs.errorDiagExit("Could not read txt file from the JAR! Path: "
					+ dir);
		}

		return read;
	}

	public ArrayList readWholeFile(String dir) {
		ArrayList al = new ArrayList();

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
		} catch (FileNotFoundException filenotfoundexxeption) {
			Dialogs.errorDiagExit("File " + dir + " does not exist");
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
		return al;
	}

	public void write(String dir, String write, int lineNum, boolean dow) {
		try {
			FileWriter fileWriter = new FileWriter(dir, dow);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			ArrayList al = readWholeFile(dir);

			if (lineNum != 0) {
				al.set(lineNum - 1, write);
			}
			eraseFile(dir);
			for (int i = 0; i < al.size(); i++) {
				bufferedWriter.write((String) al.get(i));
				bufferedWriter.newLine();
			}

			bufferedWriter.close();

		} catch (IOException ioexception) {
			Dialogs.errorDiagExit("Could not write to " + dir);
		}
	}

	public boolean checkIfFileIsThere(File file) {
		if (!file.exists())
			return false;
		else
			return true;
	}

	public void createDirectory(File file) {
		file.mkdirs();
	}

	public void createActualFile(File file) {
		if (!checkIfFileIsThere(file))
			try {
				file.createNewFile();
			} catch (IOException e) {
				Dialogs.errorDiagExit("Could not create file " + file.getPath());
			}
		else
			return;
	}

	public boolean fileContainsAnything(String dir) {
		boolean contains = false;
		String s = "";
		try {
			s = readSpecificLine(dir, 1);
		} catch (Exception e) {
			Dialogs.errorDiagExit("Could not read the file " + dir);
		}
		try {
			if (s.equals(null) || s.equals(""))
				contains = false;
		} catch (Exception e) {
			contains = false;
			return contains;
		}

		return contains;
	}
}