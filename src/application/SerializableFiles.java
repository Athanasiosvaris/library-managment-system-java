package application;
import java.util.*;
import java.io.*;

public class SerializableFiles implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private  ArrayList<administrator> Admins = new ArrayList<administrator>();
	private  ArrayList<User> Users = new ArrayList<User>();
	private  ArrayList<Category> Categories = new ArrayList<Category>();
	private  ArrayList<Book> Books = new ArrayList<Book>();
	
	SerializableFiles (ArrayList<administrator> Admins,ArrayList<User> Users,ArrayList<Category> Categories,ArrayList<Book> Books) throws IOException {
		this.Admins = Admins;
		this.Users = Users;
		this.Categories = Categories;
		this.Books = Books;
	}
	public void Serialize() throws IOException {
		String currentDirectory = System.getProperty("user.dir");
		System.out.println(currentDirectory);
		String relativePath = currentDirectory + File.separator + "medialab" + File.separator + "SerializableFiles.ser";
		
		FileOutputStream fileOut = new FileOutputStream(relativePath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this);
		System.out.println("Sfiles.I run");
		out.close();
		fileOut.close();
	}

	public ArrayList<administrator> getAdmins() {
		return this.Admins;
	}
	
	public ArrayList<User> getUsers() {
		return this.Users;
	}
	
	public ArrayList<Category> getCategories() {
		return this.Categories;
	}
	
	public ArrayList<Book> getBooks() {
		return this.Books;
	}
}
