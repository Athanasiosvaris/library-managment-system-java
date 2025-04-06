//import java.io.Serializable;
package application;

import java.util.*;
import java.io.*;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String publishing_house;
	private int ISBN;
	private int publication_year;
	private String category;
	private int numberCopies; // ANTITIPA TOU VIVLIOU
	private int number_borrow = 0; 
	private ArrayList<String> ratings = new ArrayList<String>();
	private ArrayList<String> user_comments = new ArrayList<String>();
	private static ArrayList<Book> Books = new ArrayList<Book>();
	private boolean help = true;

	Book(String title, String author, String publishing_house, int ISBN, int publication_year, String category,
			int numberCopies) {
		for (Category cat : Category.getCategories()) {
			if ((cat.getTitle().equals(category))) {
				help = false;
			}
		}
		if (help) {
			throw new IllegalArgumentException();
		}
		if (Book.Books == null) {
			Books = new ArrayList<Book>();
		}
		for (Book b : Book.Allbooks()) {
			if (b.getTitle().matches(title)) {
				throw new IllegalArgumentException();
			}
		}
		this.title = title;
		this.author = author;
		this.publishing_house = publishing_house;
		this.ISBN = ISBN;
		this.publication_year = publication_year;
		this.category = category; // Otan dimiourgo ena neo vivlio tsekaro an i katigoria tou yparxei idi.
		this.numberCopies = numberCopies;
		Books.add(this);
		Category.addBook(title, category);
		System.out.println("New book object has been created");
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishing_house() {
		return this.publishing_house;
	}

	public void setPublishing_house(String publishing_house) {
		this.publishing_house = publishing_house;
	}

	public int getISBN() {
		return this.ISBN;
	}

	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}

	public int getPublication_year() {
		return this.publication_year;
	}

	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		for (Category cat : Category.getCategories()) {
			if ((cat.getTitle().equals(category))) {
				help = false;
			}
		}
		if (help) {
			throw new IllegalArgumentException();
		}
		this.category = category;
	}

	public int getNumberCopies() {
		return this.numberCopies;
	}

	public void setNumberCopies(int numbercopies) {
		this.numberCopies = numbercopies;
	}

	public int getnumber_borrow() {
		return this.number_borrow;
	}

	public void setnumber_borrow(int number_borrows) {
		this.number_borrow = number_borrows;
	}

	public void addComment(String comment) {
		this.user_comments.add(comment);
	}

	public void printAllComments() {
		for (String S : user_comments) {
			System.out.printf("%s \n", S);
		}
	}
	
	public ArrayList<String> BookComments() {
		return user_comments;
	}

	public void addGrade(String grade) {
		this.ratings.add(grade);
	}
	
	public float averageGrade() {
		float averageGrade = 0; 
		float helpgrade = 0 ;
		float counter = 0;
		for (String Grade : ratings) {
			helpgrade = Float.parseFloat(Grade);
			averageGrade = averageGrade + helpgrade;
			counter++;
		}
		if (!(counter == 0))
		averageGrade = averageGrade/counter;
		return averageGrade;
	}
	
	public int numberOfRatings () {
		int number = this.ratings.size();
		return number;
	}
	public static ArrayList<Book> Allbooks() {
		return Books;
	}

	public static void setBooks(ArrayList<Book> modifiedBooks) {
		Books = modifiedBooks;
	}

	public void decreaseNumberBorrow() {
		this.number_borrow--;
	}

	public boolean number_borrow() {
		if (this.numberCopies > this.number_borrow) {
			this.number_borrow++;
			return true;
		} else {
			System.out.println("There not enough book copies available at the moment. Please try again later. \n");
			return false;
		}
	}

	public void deletionofBook(Book a) {
		Iterator<Book> iterator = Books.iterator();

		while (iterator.hasNext()) {
			Book book = iterator.next();

			if (book.equals(a)) {
				System.out.print("I will get deleted : ");
				System.out.println(book.getTitle());
				book.number_borrow = 0;
				iterator.remove(); // Safe removal using iterator
				Category removeCat = new Category ();
				removeCat.removeBook(book.getTitle(), book.getCategory());
				removeCat.deleteCategory("removeCat");
				return;
			}
		}
	}
}
