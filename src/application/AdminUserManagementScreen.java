package application;

import java.io.IOException;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminUserManagementScreen {
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private TextArea UsersTextArea;
	@FXML
	private TextField ViewUserTextfield;
	@FXML
	private TextField UsernameModify;
	@FXML
	private TextField UsernameDelete;
	@FXML
	private TextField NewUsername;
	@FXML
	private TextField NewPassword;
	@FXML
	private TextField NewFirstname;
	@FXML
	private TextField NewLastname;
	@FXML
	private TextField NewADT;
	@FXML
	private TextField NewEmail;
	@FXML
	private TextField UsernameBorrow;
	@FXML
	private TextField UsernameTerminate;
	@FXML
	private TextField BookTerminate;

	@FXML
	public void viewAllUsers() {
		UsersTextArea.clear();
		for (User U : Files.getUsers()) {
			System.out.println(U.getUsername());
			UsersTextArea.appendText(U.getUsername() + "\n");
		}
	}

	@FXML
	public void goToAdminScreen(ActionEvent event) throws IOException {
		System.out.println("Hi there");
		root = FXMLLoader.load(getClass().getResource("Sample3.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void viewUserInfo() {
		String Username = ViewUserTextfield.getText();
		String Title = "";
		String Infos = "";
		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(Username)) {
				Title = String.format("User's %s information", U.getUsername());
				System.out.println(Title);
				Infos = String.format(
						"Useranme: %s \nPassword: %s \nFirstname" + ": %s\nLastname: %s\nADT: %s\n"
								+ "Email: %s\nUser has borrowed: %d books",
						U.getUsername(), U.getPassword(), U.getFirstname(), U.getLastname(), U.getADT(), U.getEmail(),
						U.getnumberOfBooksborrowing());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(Title);
				alert.setHeaderText(null);
				alert.setContentText(Infos);
				alert.showAndWait();
				return;
			}
		}
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("There is no user with that username!");
			alert.showAndWait();
			return;
		}
	}

	@FXML
	public void deleteUser() {
		String UsernameToDelete = UsernameDelete.getText();
		int numberBorrow = 0;
		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(UsernameToDelete)) {
				for (Book b : U.BorrowedBooksByUser()) {
					String Title = b.getTitle();
					for (Book bb : Book.Allbooks()) {
						if (bb.getTitle().matches(Title)) {
							System.out.println("I RUN");
							numberBorrow = bb.getnumber_borrow();
							numberBorrow = numberBorrow - 1;
							bb.setnumber_borrow(numberBorrow);
							break;
						}
					}
				}
			}
		}
		Iterator<User> iterator = Files.getUsers().iterator();
		while (iterator.hasNext()) {
			User u = iterator.next();
			if (u.getUsername().matches(UsernameToDelete)) {
				iterator.remove(); // Safely remove the element using the iterator
				System.out.println("User deleted");
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Success");
				alert.setHeaderText(null);
				alert.setContentText("User deleted!");
				alert.showAndWait();
				return;
			}
		}
	}

	@FXML
	public void modifyUser() {
		String UsernameMod = UsernameModify.getText();
		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(UsernameMod)) {
				if (!(NewUsername.getText().matches(""))) {
					for (User UU : Files.getUsers()) {
						if (UU.getUsername().matches(NewUsername.getText())) {
							Alert alert = new Alert(Alert.AlertType.WARNING);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("There is allready a user with that username!");
							alert.showAndWait();
							return;
						}
					}
					U.setUsername(NewUsername.getText());
					System.out.println("I run");
				}
				if (!(NewPassword.getText().matches(""))) {
					U.setPassword(NewPassword.getText());
				}
				if (!(NewFirstname.getText().matches(""))) {
					U.setFirstname(NewFirstname.getText());
				}
				if (!(NewLastname.getText().matches(""))) {
					U.setLastname(NewLastname.getText());
				}
				if (!(NewADT.getText().matches(""))) {
					U.setADT(NewADT.getText());
				}
				if (!(NewEmail.getText().matches(""))) {
					U.setEmail(NewEmail.getText());
				}
				return;
			}
		}
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Error with your input.");
		alert.setHeaderText(null);
		alert.setContentText("There is no user with that username!");
		alert.showAndWait();
		return;
	}

	@FXML
	public void viewBorrowedBooksByUser() {
		String Username = UsernameBorrow.getText();
		String AlertTitle = "";
		String AlertContextText = "";
		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(Username)) {
				for (Book b : U.BorrowedBooksByUser()) {
					System.out.println("Hi there");
					AlertTitle = String.format("Books you have borrowed:");
					AlertContextText = String.format("Title: %s\nISBN: %d\nAuthor: %s\n", b.getTitle(), b.getISBN(),
							b.getAuthor());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
				}
			}
		}
	}

	@FXML
	public void terminateBorrowing() {
		String Username = UsernameTerminate.getText();
		String BookTitle = BookTerminate.getText();
		boolean help1 = true;
		boolean help2 = true;
		boolean help3 = false;

		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(Username)) {
				help1 = false;
				Iterator<Book> iterator = U.BorrowedBooksByUser().iterator();
				while (iterator.hasNext()) {
				    Book b = iterator.next();
				    if (b.getTitle().matches(BookTitle)) {
				        help2 = false;
				        help3 = true;
				        iterator.remove(); 
				        U.decreasenumberOfBooksborrowing();
						for (Book bb : Book.Allbooks()) {
							if (bb.getTitle().matches(BookTitle)) {
								bb.decreaseNumberBorrow();
							}
						}
					}
				}

			}
		}
		if (help1) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Error with your input.");
			alert.setHeaderText(null);
			alert.setContentText("There is no user with that username!");
			alert.showAndWait();
			return;
		}
		if (help2) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			String text = String.format("%s hasn't borrowed this book in order to give it back.", Username);
			alert.setTitle("Error with your input.");
			alert.setHeaderText(null);
			alert.setContentText(text);
			alert.showAndWait();
			return;
		}
		if (help3) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			String text = String.format("Book : %s has been successfully returned to the library.", BookTitle);
			alert.setTitle("Success.");
			alert.setHeaderText(null);
			alert.setContentText(text);
			alert.showAndWait();
			return;
		}
	}
}
