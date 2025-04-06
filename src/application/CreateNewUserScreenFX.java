package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class CreateNewUserScreenFX {

	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private TextField NUser_Username;
	@FXML
	private TextField NUser_Password;
	@FXML
	private TextField NUser_Firstname;
	@FXML
	private TextField NUser_Lastname;
	@FXML
	private TextField NUser_ADK;
	@FXML
	private TextField NUser_email;		

	public void signUp (ActionEvent e) throws RuntimeException, IOException {
		try {
		String NNUser_Username = NUser_Username.getText();
		String NNUser_Password = NUser_Password.getText(); 
		String NNUser_Firstname = NUser_Firstname.getText();
		String NNUser_Lastname = NUser_Lastname.getText();
		String NNUser_ADK = NUser_ADK.getText();
		String NNUser_email = NUser_email.getText();
		
		for (User U: Files.getUsers()) {
			if (U.getADT().matches(NNUser_ADK) || U.getEmail().matches(NNUser_email) || U.getUsername().matches(NNUser_Username)) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Error with your input data.");
				alert.setContentText("There is another user with same ADT,email or username.Please change them and try again.");
				alert.showAndWait();
				System.out.println("Hi there");
				return;
			}
		}
			User a = new User(NNUser_Username,NNUser_Password,NNUser_Firstname,NNUser_Lastname,NNUser_ADK,NNUser_email);
			Files.addUser(a);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText(null);
			alert.setContentText("You have sucessfully signed up.Please login to start using the library");
			alert.showAndWait();
			root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
		catch(RuntimeException ex) {
			ex.printStackTrace();
		}
	}
	
	public void back (ActionEvent e) throws RuntimeException, IOException  {
		
		root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
		
	}
}
