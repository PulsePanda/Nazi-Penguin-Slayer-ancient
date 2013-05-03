package main;

public class Core {
	private static int tilew = 10, tileh = 5;
	public static Tile[][] tiles = new Tile[10][5];
	private static Core core;
	private static Frame frame;

	public Core() {
		initTiles();
	}

	public void initTiles() {
		for (int i = 0; i < tilew; i++) {
			tiles[i][0] = new Tile("C:\\img.png");
		}
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public void setFrame(Frame f) {
		frame = f;
	}

	public static Tile[][] getTiles() {
		return tiles;
	}

	public static int getTileArrayWidth() {
		return tilew;
	}

	public static int getTileArrayHeight() {
		return tileh;
	}
}
