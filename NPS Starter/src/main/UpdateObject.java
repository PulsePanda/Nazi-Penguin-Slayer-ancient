package main;

import java.io.Serializable;

public class UpdateObject implements Serializable {

	byte[] bytes;
	String path;

	public UpdateObject(String finalDir, byte[] bytes) {
		this.bytes = bytes;
		path = finalDir;
	}

	public String getPath() {
		return path;
	}

	public byte[] getFile() {
		return bytes;
	}
}
