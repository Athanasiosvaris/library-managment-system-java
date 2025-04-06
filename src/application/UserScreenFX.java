package application;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserScreenFX {

	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	// Search books
	@FXML
	private TextField SearchTitle;
	@FXML
	private TextField SearchAuthor;
	@FXML
	private TextField SearchPublicationYear;
	// AllBookInfo
	@FXML
	private TextField ViewTitle;
	// BorrowBook
	@FXML
	private TextField BorrowBook;
	@FXML
	private TextField DurationOfBorrowing;
	// CommentAndGrade
	@FXML
	private TextField BookTitleGradeComment;
	@FXML
	private TextField BookGrade;
	@FXML
	private TextArea BookComment;

	static String Username = "";

	static public void setUsername(String Username_initial_page) {
		Username = Username_initial_page;
	}

	// logout function
	public void logout(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Search book
	@FXML
	public void searchBook() {
		String SearchTitlee = SearchTitle.getText();
		String SearchAuhtorr = SearchAuthor.getText();
		String SearchPublicationYearr = SearchPublicationYear.getText();

		String AlertTitle = "";
		String AlertContextText = "";
		boolean help = true;

		// search ONLY by Title
		if (SearchAuhtorr.matches("") && SearchPublicationYearr.matches("") && !(SearchTitlee.matches(""))) {
			for (Book b : Book.Allbooks()) {
				if (b.getTitle().matches(SearchTitlee)) {
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format(
							"Title: %s\nAuthor: %s\n"
									+ "ISBN: %d\n\nHow many users have borrowed this book: %d\nAverage book rating: %f",
							b.getTitle(), b.getAuthor(), b.getISBN(), b.getnumber_borrow(), b.averageGrade());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
					help = false;
				}
			}
		}
		// search only by Author
		if (!(SearchAuhtorr.matches("")) && SearchPublicationYearr.matches("") && SearchTitlee.matches("")) {
			for (Book b : Book.Allbooks()) {
				if (b.getAuthor().matches(SearchAuhtorr)) {
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format(
							"Title: %s\nAuthor: %s\n"
									+ "ISBN: %d\n\nHow many users have borrowed this book: %d\nAverage book rating: %f",
							b.getTitle(), b.getAuthor(), b.getISBN(), b.getnumber_borrow(), b.averageGrade());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
					help = false;
				}
			}
		}

		// search only by publication year
		if (SearchAuhtorr.matches("") && !(SearchPublicationYearr.matches("")) && SearchTitlee.matches("")) {
			int SearchPublicationYearINT = Integer.parseInt(SearchPublicationYearr);
			for (Book b : Book.Allbooks()) {
				if (b.getPublication_year() == SearchPublicationYearINT) {
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format(
							"Title: %s\nAuthor: %s\n"
									+ "ISBN: %d\n\nHow many users have borrowed this book: %d\nAverage book rating: %f",
							b.getTitle(), b.getAuthor(), b.getISBN(), b.getnumber_borrow(), b.averageGrade());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
					help = false;
				}
			}
		}
		// search by Title and Author
		if (!(SearchAuhtorr.matches("")) && SearchPublicationYearr.matches("") && !(SearchTitlee.matches(""))) {
			System.out.println("I run");
			for (Book b : Book.Allbooks()) {
				if (b.getTitle().matches(SearchTitlee) && b.getAuthor().matches(SearchAuhtorr)) {
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format(
							"Title: %s\nAuthor: %s\n"
									+ "ISBN: %d\n\nHow many users have borrowed this book: %d\nAverage book rating: %f",
							b.getTitle(), b.getAuthor(), b.getISBN(), b.getnumber_borrow(), b.averageGrade());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
					help = false;
				}
			}
		}
		// search by Title and PublicationYear
		if (SearchAuhtorr.matches("") && !(SearchPublicationYearr.matches("")) && (!SearchTitlee.matches(""))) {
			int SearchPublicationYearINT = Integer.parseInt(SearchPublicationYearr);
			for (Book b : Book.Allbooks()) {
				if (b.getTitle().matches(SearchTitlee) && b.getPublication_year() == SearchPublicationYearINT) {
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format(
							"Title: %s\nAuthor: %s\n"
									+ "ISBN: %d\n\nHow many users have borrowed this book: %d\nAverage book rating: %f",
							b.getTitle(), b.getAuthor(), b.getISBN(), b.getnumber_borrow(), b.averageGrade());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
					help = false;
				}
			}
		}
		// search by Author and PublicationYear
		if (!(SearchAuhtorr.matches("")) && !(SearchPublicationYearr.matches("")) && SearchTitlee.matches("")) {
			int SearchPublicationYearINT = Integer.parseInt(SearchPublicationYearr);
			for (Book b : Book.Allbooks()) {
				if (b.getAuthor().matches(SearchAuhtorr) && b.getPublication_year() == SearchPublicationYearINT) {
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format(
							"Title: %s\nAuthor: %s\n"
									+ "ISBN: %d\n\nHow many users have borrowed this book: %d\nAverage book rating: %f",
							b.getTitle(), b.getAuthor(), b.getISBN(), b.getnumber_borrow(), b.averageGrade());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
					help = false;
				}
			}
		}
		// search by Title,Author and PublicationYear
		if (!(SearchAuhtorr.matches("")) && !(SearchPublicationYearr.matches("")) && (!(SearchTitlee.matches("")))) {
			int SearchPublicationYearINT = Integer.parseInt(SearchPublicationYearr);
			for (Book b : Book.Allbooks()) {
				if (b.getAuthor().matches(SearchAuhtorr) && b.getPublication_year() == SearchPublicationYearINT
						&& b.getTitle().matches(SearchTitlee)) {
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format(
							"Title: %s\nAuthor: %s\n"
									+ "ISBN: %d\n\nHow many users have borrowed this book: %d\nAverage book rating: %f",
							b.getTitle(), b.getAuthor(), b.getISBN(), b.getnumber_borrow(), b.averageGrade());
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
					help = false;
				}
			}
		}
		if (help) {
			helpfunct();
			return;
		}
	}

	// Na tin ksanakoitakso
	@FXML
	public void allBookInfo() {
		String Title = ViewTitle.getText();
		String AlertTitle = "";
		String AlertContextText = "";
		ArrayList<String> Comments = new ArrayList();
		boolean help = true;

		for (Book B : Book.Allbooks()) {
			if (B.getTitle().matches(Title)) {

				// Auto to kommati kodika den to exo tsekari
				Comments = B.BookComments();
				// Concatenate strings using a loop with newline character
				StringBuilder result = new StringBuilder();
				for (String str : Comments) {
					result.append(str).append("\n");
				}
				// Print the concatenated string
				System.out.println(result.toString());

				AlertTitle = String.format("%s's information", B.getTitle());
				AlertContextText = String.format("Title: %s\nAuthor: %s\nPublishing house: %s\n"
						+ "Category: %s\nPublishing year: %d\n"
						+ "ISBN: %d\nNumber of copies: %d\nHow many are borrowed: %d\nAverage book rating: %f\nComments :\n%s"
						+ "", B.getTitle(), B.getAuthor(), B.getPublishing_house(), B.getCategory(),
						B.getPublication_year(), B.getISBN(), B.getNumberCopies(), B.getnumber_borrow(),
						B.averageGrade(), result.toString());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle(AlertTitle);
				alert.setHeaderText(null);
				alert.setContentText(AlertContextText);
				alert.showAndWait();
				help = false;
			}
		}
		if (help) {
			helpfunct();
			return;
		}
	}

	@FXML
	public void borrowBook() { // Fainetai na douleuei sosta. Tin exo testarei
		String BorrowBookTitle = BorrowBook.getText();
		String durationOfBorrowingS = DurationOfBorrowing.getText();
		int durationOfBorrowing = 0;
		if (!(durationOfBorrowingS.matches("")))
			durationOfBorrowing = Integer.parseInt(DurationOfBorrowing.getText());

		boolean help = true;
		boolean help1 = true;
		// Elegxos gia diarkeia daneismou
		if (durationOfBorrowing > 5) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText(
					"The time duration limit is 5 days. Please decrease the days you want to borrow this book.");
			alert.showAndWait();
			return;
		}
		// Elegxos gia to posa vivlia exo daneistei
		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(Username)) {
				if (U.getnumberOfBooksborrowing() >= 2) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText(
							"You cannot borrow more than 2 books.Please return one book if you want to borrow another.");
					alert.showAndWait();
					return;
				}
			}
		}

		for (Book b : Book.Allbooks()) {
			if (b.getTitle().matches(BorrowBookTitle)) {
				help1 = false;
				if (help = b.number_borrow()) {
					for (User U : Files.getUsers()) {
						if (U.getUsername().matches(Username)) {
							U.increasenumberOfBooksborrowing();
							U.addIntoListOfBorrowedBooks(b);
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Success");
							alert.setHeaderText(null);
							alert.setContentText(
									"You successfully borrowed the book you wanted.");
							alert.showAndWait();
							return;
						}
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText(
							"There not enough book copies available at the moment. Please try again later.");
					alert.showAndWait();
				}
			}
		}
		if (help1) {
			helpfunct();
		}
	}

	@FXML // Fainetai na douleuei sosta
	public void CommentAndGrade() {
		String BookTitle = BookTitleGradeComment.getText();
		String GradeS = BookGrade.getText();
		float Grade = 0;
		
		if (!(GradeS.matches("")))
		Grade = Float.parseFloat(GradeS);
		String Comment = BookComment.getText();
		boolean help = true;

		if (!(GradeS.matches(""))) {
			if (Grade > 5 || Grade < 1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("The grade must be beetween 1 and 5.Please try again");
				alert.showAndWait();
				return;
			}
		}

		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(Username)) {
				for (Book b : U.BorrowedBooksByUser()) {
					if (b.getTitle().matches(BookTitle)) {
						help = false;
						if (!(GradeS.matches("")) && !(Comment.matches(""))) {
							for (Book bb : Book.Allbooks()) {
								if (bb.getTitle().matches(BookTitle)) {
									bb.addGrade(GradeS);
									Comment = "'" + Comment + "'" + " " + "commented user: " + Username;
									System.out.println(Comment);
									bb.addComment(Comment);
									System.out.println("I RUN");
									return;

								}
							}
						}
						if (!(GradeS.matches("")) && Comment.matches("")) {
							for (Book bb : Book.Allbooks()) {
								if (bb.getTitle().matches(BookTitle)) {
									bb.addGrade(GradeS);
									System.out.println("I RUN");
									return;
								}
							}
						}
						if (GradeS.matches("") && (!Comment.matches(""))) {
							for (Book bb : Book.Allbooks()) {
								if (bb.getTitle().matches(BookTitle)) {
									Comment = "'" + Comment + "'" + " " + "commented user: " + Username;
									System.out.println(Comment);
									bb.addComment(Comment);
									System.out.println("I RUN");
									return;
								}
							}
						}
					}
				}
			}
		}
		if (help) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("You haven't borrowed this book yet.Please borrow it and then try again.");
			alert.showAndWait();
		}
	}
	
	public void allMyBooks() {
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate currentDatee = LocalDate.now();
		LocalDate dateFiveDaysLater = currentDatee.plusDays(5);
		String formattedDateFiveDaysLater = dateFiveDaysLater.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String AlertTitle = "";
		String AlertContextText = "";
		boolean help1 = true; 
		
		for (User U : Files.getUsers()) {
			if (U.getUsername().matches(Username)) {
				for (Book b : U.BorrowedBooksByUser()) {
					help1 = false;
					System.out.println("Hi there");
					AlertTitle = String.format("%s's information", b.getTitle());
					AlertContextText = String.format("Title: %s\nISBN: %d\nDate you borrowed the book: %s,\nDate you have to bring back the book: %s",b.getTitle(), b.getISBN(),currentDate,formattedDateFiveDaysLater);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(AlertTitle);
					alert.setHeaderText(null);
					alert.setContentText(AlertContextText);
					alert.showAndWait();
				}
			}
		}
		if (help1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("You haven't borrowed any books yet!");
			alert.showAndWait();
		}
	}
	private void helpfunct() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("There is no book entry in the library with those credentials!");
		alert.showAndWait();
	}
}
