package application;
import java.io.*;

public class Person implements Serializable {
	protected String username;
	protected String password;
	
	/**
	 * Constructs a new Person object with no attributes assigned to it
	 * (Without being initialized).
	 */
	Person() {
	}
	/**
	 * Constructs a new Person object with the given username and password.
	 * @param username the String to assign to the username field of the Person object
	 * @param password the String to assign to the password field of the Person object
	 */
	Person(String username, String password) {
		this.username = username;
		this.password = password;
	}
	  /**
	   * This function is a setter function for the username attribute 
	   * of an object of the class Person.
	   * It sets the username attribute of the person.
	   * @param username the String that will be set to be the username  
     */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	   * This function is a getter function for the username attribute of an 
	   * object of the class Person.
	   * It retrieves the username attribute of the person.
	   * @return the username attribute of the person  
   */
	public String getUsername() {
		return this.username;
	}
	 /**
	   * This function is a setter for the password attribute of an 
	   * object of the class Person.
	   * It sets the password attribute of the person.
	   * @param password the String that will be set to be the username  
   */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	   * This function is a getter function for the password attribute of an 
	   * object of the class Person.
	   * It retrieves the password attribute of the person.
	   * @return the password attribute of the person  
 */
	public String getPassword() {
		return this.password;
	}

}