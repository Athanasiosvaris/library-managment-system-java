package application;
import java.io.Serializable;
import java.util.*;
import java.io.*;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private ArrayList<String> BooksofthatCategory = new ArrayList<String>();
	private static ArrayList<Category> categoryList = new ArrayList<Category>();

	Category(String title) {
		if (categoryList == null ) {
		categoryList = new ArrayList<Category>();
		}
		this.title = title;
		categoryList.add(this);
	}

	public Category() {
	}

	void setTitle(String title) {
		this.title = title;
	}

	String getTitle() {
		return this.title;
	}	
	

	static void addBook(String title, String category) {
		for (Category cat : Category.categoryList) {
			if (cat.getTitle().matches(category)) {
				cat.BooksofthatCategory.add(title);
			}
		}
	}
	public void modifyBooksofthatCategory (String oldtitle,String newtitle,String category,String newcat,String title) {
		for (Category cat : Category.categoryList) {
			if (cat.getTitle().matches(category)) {
				cat.BooksofthatCategory.remove(oldtitle);
				if (!(newtitle == ""))cat.BooksofthatCategory.add(newtitle);
//				return;
			} 
			if (cat.getTitle().matches(newcat)) {
			if(!(newcat == "")) cat.BooksofthatCategory.add(title);
			}
		}
	}
	
	public void modifyCategoryofthatBooks (String oldCategory,String newCategory) {}
	
	public void removeBook(String title, String category) {
		for (Category cat : Category.categoryList) {
			if (cat.getTitle().matches(category)) {
				cat.BooksofthatCategory.remove(title);
				System.out.println("I got removed from BooksofthatCategory");
				return;
			}
		}
		 System.out.println("Category not found or book not present in the category.");
	}

	public boolean deleteCategory(String category) {
		if (!(this.BooksofthatCategory.isEmpty())) {
			System.out.printf("Before deleting %s category,delete the following books : \n", category);
			this.BooksofthatCategory.forEach(item -> System.out.println(item));
			return false;
		} else {
			for (Category cat : Category.categoryList) {
				if(cat.getTitle().matches(category)) {
					Category.categoryList.remove(cat);
					break;
				}
			}
			System.out.printf("The %s category has been successfuly deleted. \n", category);
			return true;
		}
	}

	public static void setCategories(ArrayList<Category> newCategories) {
		categoryList = newCategories;
	}

	public static ArrayList<Category> getCategories() {
		return categoryList;
	}

	public static void printCategories() {
		if (Category.categoryList == null || Category.categoryList.isEmpty()) {
			System.out.println("There aren't any book categories created yet.The category list is empty.");
		} else {
			for (Category cat : Category.categoryList) {
				System.out.printf("%s \n", cat.getTitle());
			}
		}
	}
	
	public ArrayList<String> getBooksOfCategory () {
		return this.BooksofthatCategory;
	}
}
