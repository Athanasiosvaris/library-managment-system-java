package application;
import java.util.*;
import java.io.*;

public class administrator extends Person implements Serializable{
	private static final long serialVersionUID = 1L;

	administrator() {}

	administrator(String username, String password) {
		super(username, password);
	}
	
	public void Serialize() throws IOException {
		String currentDirectory = System.getProperty("user.dir");
		System.out.println(currentDirectory);
		String relativePath = currentDirectory + File.separator + "medialab" + File.separator + "SerializableFiles.ser";
		
		FileOutputStream fileOut = new FileOutputStream(relativePath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this);
		System.out.println("I run");
		out.close();
		fileOut.close();
	}
}

