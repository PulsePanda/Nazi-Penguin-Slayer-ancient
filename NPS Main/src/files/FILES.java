package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import main.Dialogs;

public class FILES {

	/**
	 * main menu parts
	 */
	public static final String mainPanelBackgroundImage = "../graphic/Main Background.png";
	public static final String singlePlayerButton = "../graphic/Single Player Button.png";
	public static final String singlePlayerRollover = "../graphic/Single Player Rollover.png";
	public static final String multiPlayerButton = "../graphic/Multi Player Button.png";
	public static final String multiPlayerRollover = "../graphic/Multi Player Rollover.png";
	public static final String optionButton = "../graphic/Option Button.png";
	public static final String optionRollover = "../graphic/Option Rollover.png";
	// end of main menu parts

	/**
	 * single player menu parts
	 */
	public static final String singlePlayerBackground = "../graphic/Single Player Background.png";
	public static final String singlePlayerStartButton = "../graphic/Single Player Start.png";
	public static final String singlePlayerStartRollover = "../graphic/Single Player Start Rollover.png";
	public static final String singlePlayerResumeButton = "../graphic/Single Player Resume.png";
	public static final String singlePlayerResumeRollover = "../graphic/Single Player Resume Rollover.png";
	// end of single player menu parts

	/**
	 * multiplayer menu parts
	 */
	public static final String multiplayerHostButton = "../graphic/Host Button.png";
	public static final String multiplayerHostRollover = "../graphic/Host Rollover.png";
	public static final String multiplayerJoinButton = "../graphic/Join Button.png";
	public static final String multiplayerJoinRollover = "../graphic/Join Rollover.png";
	public static final String multiplayerBackground = "../graphic/Multiplayer Background.png";
	// end of multiplayer menu parts

	/**
	 * option main menu parts
	 */
	public static final String optionBackground = "../graphic/Options Background.png";
	public static final String optionsControlButton = "../graphic/Options Control Button.png";
	public static final String optionsControlRollover = "../graphic/Options Control Rollover.png";
	// end of option main menu parts

	/**
	 * general components
	 */
	public static final String backButton = "../graphic/Back Button.png";
	public static final String backButtonRollover = "../graphic/Back Button Rollover.png";
	public static final String taskbarIcon = "../graphic/Taskbar Icon.png";
	public static final String buttonClicked = "../audio/Button Clicked.wav";
	// end of general components

	/**
	 * drag panel, top bar parts
	 */
	public static final String exitButton = "../graphic/Exit Button.png";
	public static final String exitRollover = "../graphic/Exit Rollover.png";
	public static final String minimizeButton = "../graphic/Minimize Button.png";
	public static final String minimizeRollover = "../graphic/Minimize Rollover.png";
	// end of drag panel, top bar parts

	/**
	 * control window parts
	 */
	public static final String controlsConfirmButton = "../graphic/Controls Confirm Button.png";
	public static final String controlsConfirmButtonRollover = "../graphic/Controls Confirm Rollover.png";
	public static final String controlsCancelButton = "../graphic/Controls Cancel Button.png";
	public static final String controlsCancelButtonRollover = "../graphic/Controls Cancel Rollover.png";
	public static final String controlWindowBackground = "../graphic/control background.png";
	public static final String controlWindowBackgroundButton = "../graphic/control background set.png";
	// end of control window parts

	/**
	 * login window parts
	 */
	public static final String loginBackground = "../graphic/Login Window Background.png";
	// end of login window parts

	/**
	 * directories
	 */
	public static final String mainDir = "../AVTECH/NPS";
	public static final String filesDir = "../AVTECH/NPS/Files";
	public static final String propDir = "properties.properties";
	// end of directories

	/**
	 * version
	 */
	public static final String VERSION = "0.0.1";

	/**
	 * link stuff
	 */
	public static final String IPAddress = "nazipenguinslayer.no-ip.org";
	public static final String port = "6987";

	public static boolean saveProperties(Properties prop) {
		try {
			prop.save(new FileOutputStream(new File(propDir)),
					"Properties File for Nazi Penguin Slayer");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Dialogs.errorDiagExit("Cannot Find Properties File! Please Reinstall The Game");
			return false;
		}
	}
}
