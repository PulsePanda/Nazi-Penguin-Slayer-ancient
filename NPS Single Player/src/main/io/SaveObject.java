package main.io;

import java.io.Serializable;
import java.util.ArrayList;

import main.sprites.Tile;

public class SaveObject implements Serializable {

	public Tile[][] tiles;
	public ArrayList<Tile> list;

	public SaveObject(Tile[][] tiles, ArrayList<Tile> list) {
		this.tiles = tiles;
		this.list = list;
	}
}
