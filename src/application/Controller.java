package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Controller {

	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;	
	@FXML
	private TextField User_Username;
	@FXML
	private TextField User_Password;
	@FXML
	private TextField Admin_Username;
	@FXML
	private TextField Admin_Password;
	@FXML
	private TextArea topBooks;
	
	
	@FXML
	// When I press the login button I check if there is a user with the given credentials
	public void loginUser (ActionEvent event) throws IOException {
	  try {
		  
		String U_Username = User_Username.getText();
		String U_Password = User_Password.getText();
		boolean  help = true;
		
		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(U_Username) && U.getPassword().matches(U_Password)) {
				UserScreenFX.setUsername(U_Username);
				help = false;
				  //Code to switch beetween scenes
				root = FXMLLoader.load(getClass().getResource("Sample2.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		}
		if (help) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Error with your input data.");
			alert.setContentText("There is no user with such credentials.Please change them and try again.");
			alert.showAndWait();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
 }
	public void loginAdmin (ActionEvent event) throws IOException {
		  try {
			  
			String Ad_Username = Admin_Username.getText();
			String Ad_Password = Admin_Password.getText();
			
			for (administrator A : Files.getAdmins()) {
				if (A.getUsername().matches(Ad_Username) && A.getPassword().matches(Ad_Password)) {
					root = FXMLLoader.load(getClass().getResource("Sample3.fxml"));
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	public void createNewUser (ActionEvent e) throws IOException {
		try {
			root = FXMLLoader.load(getClass().getResource("CreateNewUserScreen.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void topfiveBooks (ActionEvent e) {
		topBooks.clear();
		List<Book> allBooks = Book.Allbooks();
		// Sort the list of books based on the average grade
		Collections.sort(allBooks, Comparator.comparingDouble(Book::averageGrade).reversed());

		for (int i = 0; i < Math.min(5, allBooks.size()); i++) {
		    Book book = allBooks.get(i);
		    String text = String.format("Title: %s \nAuthor: %s \nIsbn: %d \nAverage grade: %f \nNumber of ratings: %d\n",book.getTitle(),book.getAuthor(),book.getISBN(),book.averageGrade(),book.numberOfRatings());
		    topBooks.appendText(text + '\n');
		}
	}
	

	
	
}
