package game;

import io.FILES;
import io.IO;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;

import thread.RefreshGraphics;

public class GameCore {
	public static IO io = new IO();

	public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 600,
			ENEMY_START_X = 1000, INITIAL_PLAYER_DAMAGE = 1;
	public static int playerMoveX = 0, playerMoveY = 0,
			bulletShootSpeedMillis = 1000, level = 1, playerMoveSpeed = 1,
			bulletMoveSpeed = 20;
	public static boolean gameRunning = false, paused = false,
			shooting = false;

	public static BufferedImage gameBackground = io
			.getImage(FILES.gameBackground);
	public static BufferedImage playerImage = io.getImage(FILES.playerImage);
	public static BufferedImage bulletImage = io.getImage(FILES.bulletImage);

	public static Graphics graphics;
	public static GameFrame gameFrame;
	public static JFrame frame = new JFrame();
	public static GamePanel gamePanel = new GamePanel(FRAME_WIDTH, FRAME_HEIGHT);
	public static Player player = new Player(playerImage, 10, 100);
	public static ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	public static ArrayList<Enemy> enemy = new ArrayList<Enemy>();

	public static Random randomGenerator = new Random();

	public static Properties prop = new Properties();

	public static void main(String[] args) {
		try {
			prop.load(new FileInputStream(
					"C:\\Program Files\\AVTECH\\NPS\\Files\\bin\\properties.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	public static void start() {
		if (!gameRunning) {
			resetEverything();

			gameRunning = true;
			/**
			 * this is already used when the game is started
			 */
			gameFrame = new GameFrame();
			Thread t = new Thread(new RefreshGraphics());
			t.start();

			for (int i = 0; i < level; i++) {
				enemy.add(new Enemy(playerImage, 1000, randomGenerator
						.nextInt(650)));
			}
		} else
			return;
	}

	public static void pause() {
		if (paused)
			paused = false;
		else
			paused = true;
	}

	public static void stop() {
		if (gameRunning) {
			gameRunning = false;
			gameFrame.disposeFrame();

			try {
				Desktop desktop = Desktop.getDesktop();
				File openFile = new File(
						"C:\\Program Files\\AVTECH\\NPS\\Files\\bin\\NPS Main.jar");
				desktop.open(openFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			resetEverything();
			System.exit(0);

		} else
			return;
	}

	public static void resetEverything() {
		playerMoveX = 0;
		playerMoveY = 0;
		playerMoveSpeed = 1;
		player.posX = 10;
		player.posY = 500;
		bullet.clear();
		enemy.clear();
	}

	public void escape() {
		// pause();
		stop();
	}
}
