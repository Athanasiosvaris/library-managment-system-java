package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;


public class Main extends Application {
	
	static Scanner s;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon = new Image ("Books.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Library");
			primaryStage.setFullScreen(false);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		

		String currentDirectory = System.getProperty("user.dir");
		System.out.println(currentDirectory);
		String relativePath = currentDirectory + File.separator + "medialab" + File.separator + "SerializableFiles.ser";
		
		SerializableFiles DeserializedFiles = null;
		FileInputStream fileInn = new FileInputStream(relativePath);
		ObjectInputStream inn = new ObjectInputStream(fileInn);
		DeserializedFiles = (SerializableFiles) inn.readObject();
		inn.close();
		fileInn.close();
		
		//Antigrafi DeserializedFiles sto Files 
		Files.setAdmins(DeserializedFiles.getAdmins());
		Files.setUsers(DeserializedFiles.getUsers());
		Category.setCategories(DeserializedFiles.getCategories());
		Book.setBooks(DeserializedFiles.getBooks());
		
		launch(args);	
				 
		SerializableFiles SFiles = new SerializableFiles(Files.getAdmins(), Files.getUsers(),
				Category.getCategories(), Book.Allbooks()); // Edo antigrafo tous neous ADMIN kai USERS stin
															// non-static class SerializableFiles
		SFiles.Serialize(); // Edo kano serialize tin SerializableFiles
		System.exit(0);
		
	}
}




