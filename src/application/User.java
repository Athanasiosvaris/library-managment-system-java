package application;

import java.util.*;
import java.util.Scanner;

public class User extends Person {

	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String ADT;
	private String email;
	private int numberOfBooksborrowing; // Posa vivlia exei daneistei
	private ArrayList<Book> ListOfBorrowedBooks = new ArrayList<Book>(); // Poia vivlia exei daneistei
	private ArrayList<String> Comments = new ArrayList<String>(); 
	private ArrayList<String> Grades = new ArrayList<String>(); 
	private static ArrayList<String> AllUsernames = new ArrayList<String>();

	public User() {
	}

	User(String username, String password, String firstname, String lastname, String ADT, String email) {
		super(username, password);
		this.firstname = firstname;
		this.lastname = lastname;
		this.ADT = ADT;
		this.email = email;
		this.numberOfBooksborrowing = 0;
		User.AllUsernames.add(username);
	}

	public static void setUsernameAllUsernames(String OldUsername, String newUsername) {
		for (String s : AllUsernames) {
			if (s.matches(OldUsername)) {
				AllUsernames.remove(s);
				AllUsernames.add(newUsername);
			}
		}
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setADT(String ADT) {
		this.ADT = ADT;
	}

	public String getADT() {
		return this.ADT;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public ArrayList<Book> BorrowedBooksByUser() {
		return this.ListOfBorrowedBooks;
	}

	public int getnumberOfBooksborrowing() {
		return this.numberOfBooksborrowing;
	}

	public void increasenumberOfBooksborrowing() {
		this.numberOfBooksborrowing = this.numberOfBooksborrowing + 1;
	}

	public void decreasenumberOfBooksborrowing() {
		this.numberOfBooksborrowing = this.numberOfBooksborrowing - 1;
	}

	public void addIntoListOfBorrowedBooks(Book b) {
		this.ListOfBorrowedBooks.add(b);
	}

	public void removeFromListOfBorrowedBooks(Book b) {
		this.ListOfBorrowedBooks.remove(b);
	}

	public void addGrade(String grade) {
		this.Grades.add(grade);
	}

	public ArrayList<String> myComments() {
		return Comments;
	}


}
