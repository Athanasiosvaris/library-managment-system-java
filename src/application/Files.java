package application;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Files {
	private static ArrayList<administrator> Admins = new ArrayList<administrator>();
	private static ArrayList<User> Users = new ArrayList<User>();

	public static void addAdmin(administrator a) {
		boolean help = true;
		for (administrator admin : Admins) {
			if (admin.getUsername().matches(a.getUsername())) {
				System.out.println("There is another admin with this name.Please try another.\n");
				help = false;
				break;
			}
		}
		if (help) {
			Admins.add(a);
			System.out.println("A new admin has been added \n");
		}
	}

	public static boolean loginAdmin(String username,String password) {
		boolean help = true;
		for (administrator admin : Admins) {
			if (admin.getUsername().matches(username) && admin.getPassword().matches(password)) {
				System.out.printf("You have entered the correct credentials. Welcome back %s. \n",admin.getUsername());
				help = false;
				return true;
			}
		}
		if(help) {
			System.out.println("There is no admin with those credentials.\n");
			return false;
		}
		return false;
	}
	
	public static boolean loginUser(String username,String password) {
		boolean help = true;
		for (User u : Users) {
			if (u.getUsername().matches(username) && u.getPassword().matches(password)) {
				System.out.printf("You have entered the correct credentials. Welcome back %s.\n",u.getUsername());
				help = false;
				return true;
			}
		}
		if(help) {
			System.out.println("There is no user with those credentials.\n");
			return false;
		}
		return false;
	}

	public static void addUser(User a) {
		boolean help = true;
		for (User u : Users) {
			if (u.getUsername().matches(a.getUsername())) {
				System.out.println("There is another user with this name.Please try another.\n");
				help = false;
				break;
			}
		}
		if (help) {
			Users.add(a);
		}
	}
	
	public static void deleteUser (User u) {
		boolean help = true;
		for (User us : Users) {
			if (us.getUsername().matches(u.getUsername())) {
				Users.remove(us);
				help = false;
				break;
			}
		}
		if (help) {
			System.out.println("There is no match for this username");
		}
	}
	
	public static ArrayList<User> getUsers () {
		return Users;
	}
	
	public static ArrayList<administrator> getAdmins() {
		return Admins;
	}
	
	public static void setAdmins (ArrayList<administrator> new_Admins) {
		Admins = new_Admins;
	}
	
	public static void setUsers (ArrayList<User> new_Users) {
		Users = new_Users;
	}

}