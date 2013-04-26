package game;

import java.io.Serializable;

import sprites.Block;
import sprites.Player;

public class SaveObject implements Serializable {

	public int FRAME_WIDTH = Core.getWidth();
	public int FRAME_HEIGHT = Core.getHeight();
	public int day = Core.getDay();
	public Player player = Core.getPlayer();
	public int arrayX = Core.arrayX, arrayY = Core.arrayY;
	public int[][] worldData = Core.getWorldData();
	public Block[][] worldBlocks = Core.getWorldBlocks();

	public SaveObject() {

	}
}
