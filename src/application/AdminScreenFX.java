package application;

import java.io.IOException;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.Iterator;

//import javax.swing.Icon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

public class AdminScreenFX {
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private ComboBox<String> ViewCategories;
	@FXML
	private TextArea Textarea;
	// New book fields
	@FXML
	private TextField title;
	@FXML
	private TextField author;
	@FXML
	private TextField publicationyear;
	@FXML
	private TextField isbn;
	@FXML
	private TextField category;
	@FXML
	private TextField numberofcopies;
	@FXML
	private TextField publishinghouse;
	@FXML
	private TextField modifyTitle;
	@FXML
	private TextField modifyAttribute;
	@FXML
	private TextField DeleteBookTextField;
	// View book info
	@FXML
	private TextField BooksInfo;

	@FXML
	private void initialize() {
		ArrayList<String> Categories = new ArrayList<String>();

		for (Category c : Category.getCategories()) {
			Categories.add(c.getTitle());
			System.out.println(c.getTitle() + " From inside the initilize");
		}
		ObservableList<String> CategoryList = FXCollections.observableArrayList(Categories);
		ViewCategories.setValue("All categories");
		ViewCategories.setItems(CategoryList);
		ViewCategories.setOnAction(e -> {
			String selectedCategory = ViewCategories.getSelectionModel().getSelectedItem();
			if (selectedCategory != null) {
				// Call your function with the selected category
				handleComboBoxSelection(selectedCategory);
			}
		});
	}

	public void newCategory() {
		// create a text input dialog
		TextInputDialog td = new TextInputDialog("Enter new category name");
		td.setHeaderText("New category");
		td.showAndWait();
		TextField input = td.getEditor();
		String inputt = input.getText();

		for (Category c : Category.getCategories()) {
			if (c.getTitle().matches(inputt)) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Error with your input data.");
				alert.setContentText("This category allready exists.");
				alert.showAndWait();
				return;
			}
		}
		Category cat = new Category(inputt);
		updateCategoryList();
	}

	private void updateCategoryList() {
		ArrayList<String> Categories = new ArrayList<>();

		for (Category c : Category.getCategories()) {
			Categories.add(c.getTitle());
		}

		ObservableList<String> CategoryList = FXCollections.observableArrayList(Categories);
		ViewCategories.setItems(CategoryList);
	}

	private void handleComboBoxSelection(String selectedCategory) {
		Textarea.clear();
		for (Category c : Category.getCategories()) {
			if (c.getTitle().equals(selectedCategory)) {
				System.out.println("" + c.getTitle());
				for (String b : c.getBooksOfCategory()) {
					System.out.println(b);
					Textarea.appendText(b + '\n');
				}
				System.out.println();
			}
		}
	}

	public void logout(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void viewBookInfo() {
		String BookTitle = BooksInfo.getText();
		String AlertTitle = String.format("%s's information", BookTitle);
		String AlertContextText = "";
		for (Book B : Book.Allbooks()) {
			if (B.getTitle().matches(BookTitle)) {
				AlertContextText = String.format(
						"Title: %s\nAuthor: %s\nPublishing house: %s\n" + "Category: %s\nPublishing year: %d\n"
								+ "ISBN: %d\nNumber of copies: %d\nHow many are borrowed: %d",
						B.getTitle(), B.getAuthor(), B.getPublishing_house(), B.getCategory(), B.getPublication_year(),
						B.getISBN(), B.getNumberCopies(), B.getnumber_borrow());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle(AlertTitle);
				alert.setHeaderText(null);
				alert.setContentText(AlertContextText);
				alert.showAndWait();
			}
		}
	}

	public void modifyName() {
		TextInputDialog td = new TextInputDialog("Enter the name of the category which you want to change the name");
		td.setHeaderText("Modify category");
		td.showAndWait();
		TextField categoryName = td.getEditor();
		String categoryNameS = categoryName.getText();
		boolean help = true;

		for (Category c : Category.getCategories()) {
			if (c.getTitle().matches(categoryNameS)) {
				help = false;
				break;
			}
		}
		if (help) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Error with your input data.");
			alert.setContentText("There is no category with the input name you gave");
			alert.showAndWait();
			return;
		}

		TextInputDialog tdd = new TextInputDialog("Enter the name of the category which you want to change the name");
		tdd.setHeaderText("Modify category");
		tdd.showAndWait();
		TextField newName = tdd.getEditor();
		String newNameS = newName.getText();

		for (Category c : Category.getCategories()) {
			if (c.getTitle().matches(categoryNameS)) {
				c.setTitle(newNameS);
				updateCategoryList();
				break;
			}
		}
		for (Book b : Book.Allbooks()) {
			if (b.getCategory().matches(categoryNameS)) {
				b.setCategory(newNameS);
			}
		}
		return;
	}

	public void deleteCategory() {
		TextInputDialog td = new TextInputDialog("Enter the name of the category which you want to delete");
		td.setHeaderText("Delete category");
		td.showAndWait();
		TextField categoryName = td.getEditor();
		String categoryNameS = categoryName.getText();

		for (Category c : Category.getCategories()) {
			if (c.getTitle().matches(categoryNameS)) {
				if (c.deleteCategory(c.getTitle())) {
					updateCategoryList();
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Success");
					alert.setHeaderText("Successfull deletion.");
					alert.setContentText("You have successfuly delete " + c.getTitle());
					alert.showAndWait();
					return;
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Failure");
					alert.setHeaderText("Failure to delete.");
					alert.setContentText("You have to delete first all the books of the category and then try again!");
					alert.showAndWait();
					return;
				}
			}
		}
	}

	@FXML
	public void createNewBook() throws Exception {
		String titleS = title.getText();
		String authorS = author.getText();
		String publishinghouseS = publishinghouse.getText();
		String categoryS = category.getText();
		int isbnN;
		int publicationyearN;
		int numberofcopiesN;
		try {
			isbnN = Integer.parseInt(isbn.getText());
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Failure");
			alert.setHeaderText("Failure to create a new book.");
			alert.setContentText("Please give me just a number for ISBN!");
			alert.showAndWait();
			return;
		}
		try {
			publicationyearN = Integer.parseInt(publicationyear.getText());
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Failure");
			alert.setHeaderText("Failure to create a new book.");
			alert.setContentText("Please give me just a number for publication year!");
			alert.showAndWait();
			return;
		}
		try {
			numberofcopiesN = Integer.parseInt(numberofcopies.getText());
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Failure");
			alert.setHeaderText("Failure to create a new book.");
			alert.setContentText("Please give me just a number for number of copies!");
			alert.showAndWait();
			return;
		}
		if (titleS.isEmpty() || authorS.isEmpty() || publishinghouseS.isEmpty() || categoryS.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Failure");
			alert.setHeaderText("Failure to create a new book.");
			alert.setContentText("Please ensure that every field has correct value!");
			alert.showAndWait();
			return;
		}
		try {
			Book b = new Book(titleS, authorS, publishinghouseS, isbnN, publicationyearN, categoryS, numberofcopiesN);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText(null);
			alert.setContentText("You successfully added a new book to the library");
			alert.showAndWait();
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Failure");
			alert.setHeaderText("Failure to create a new book.");
			alert.setContentText(
					"Please create the category for this book or check if this book allready exists and then try again.");
			alert.showAndWait();
			return;
		}
		return;
	}

	public String help_function() {
		TextInputDialog td = new TextInputDialog("Enter the new value!");
		td.setHeaderText("Modify");
		td.showAndWait();
		TextField modifiedValue = td.getEditor();
		String modifiedValueS = modifiedValue.getText();
		return modifiedValueS;
	}

	@FXML
	public void modifyBook() {
		String OldTitle = modifyTitle.getText();
		String attribute = modifyAttribute.getText();
		String NewTitle;
		String CategoryOfThebook;
		for (Book book : Book.Allbooks()) {
			if (book.getTitle().matches(OldTitle)) {
				CategoryOfThebook = book.getCategory();
				if (attribute.matches("Title")) { // 1
					NewTitle = help_function();
					book.setTitle(NewTitle);
					Category help = new Category();
					help.modifyBooksofthatCategory(OldTitle, NewTitle, CategoryOfThebook,"","");
					help.deleteCategory("help");
					updateCategoryList();
					return;
				} else if (attribute.matches("Author")) { // 2
					book.setAuthor(help_function());
					return;
				} else if (attribute.matches("Publication year")) { // 3
					book.setPublication_year(Integer.parseInt(help_function()));
					return;
				} else if (attribute.matches("ISBN")) { // 4
					book.setISBN(Integer.parseInt(help_function()));
					return;
				} else if (attribute.matches("Category")) { // 5
					String newCat = help_function();
					book.setCategory(newCat);
					String title = book.getTitle();
					Category help = new Category();
					help.modifyBooksofthatCategory(OldTitle, "", CategoryOfThebook,newCat,title);
					updateCategoryList();
					return;
				} else if (attribute.matches("Number of copies")) { // 6
					book.setNumberCopies(Integer.parseInt(help_function()));
					return;
				} else if (attribute.matches("Publishing house")) { // 7
					book.setPublishing_house(help_function());
					return;
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Failure");
					alert.setHeaderText("Failure to modify book.");
					alert.setContentText("Please check that you have spelled correct the attribute.");
					alert.showAndWait();
					return;
				}
			}
		}
	}

	@FXML
	public void deleteBook() {
		String BookTitle = DeleteBookTextField.getText();
		int numberBorrow = 0;
		for (User U : Files.getUsers()) {
			Iterator<Book> iterator = U.BorrowedBooksByUser().iterator();
			
			while (iterator.hasNext()) {
				Book b = iterator.next();
				if (b.getTitle().matches(BookTitle)) {
					U.decreasenumberOfBooksborrowing();
					numberBorrow = b.getnumber_borrow();
					numberBorrow = numberBorrow - 1;
					b.setnumber_borrow(numberBorrow);
					iterator.remove(); 
					updateCategoryList();
					System.out.println("I run");
				}
			}
		}
		for (Book book : Book.Allbooks()) {
			if (book.getTitle().matches(BookTitle)) {
				String text = String.format("'%s' has been successfully deleted.", BookTitle);
				book.deletionofBook(book);
				updateCategoryList();
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Success");
				alert.setHeaderText(null);
				alert.setContentText(text);
				alert.showAndWait();
				return;
			}
		}
	}

	@FXML
	public void goToUsersManagementScreen(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("User'sManagementScreen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
