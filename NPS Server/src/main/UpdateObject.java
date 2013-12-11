package main;

import java.io.Serializable;

public class UpdateObject implements Serializable {

	byte[] bytes;
	String fileName;

	public UpdateObject(String fileName, byte[] bytes) {
		this.bytes = bytes;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getFile() {
		return bytes;
	}
}
