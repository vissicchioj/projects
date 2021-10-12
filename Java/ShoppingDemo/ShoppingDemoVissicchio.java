/**
 * Prog 9 <br>
 * Due Date and Time: 4/20/20 before 12:00 p.m. <br>
 * 
 * Purpose: This program uses the Linked List implementation of Prog 8, which was
 * 			a shopping demo that has a menu of 10 functions. <br>
 * 
 * Input: The user can choose between 10 menu options for what function they want to use.
 * 		  It also allows the user to input the name, quantity and price of an item and add
 * 		  the item into their cart. The user can also input what item they want to
 * 		  remove or retrieve an item in the cart by entering it's name.<br>
 * 
 * Output:  Outputs whether or not an item was successfully added or removed from the
 * 			list. Prints every item in the list. Prints the user specified item in the 
 * 			list if found. Outputs the total number of items in the list. Outputs the
 * 			total price of items in the list. Determines whether or not the list is 
 * 			empty or full. Outputs that the list was cleared.<br>
 * 
 * Certification of Authenticity: <br>
 * 	I certify that this lab is entirely my own work. <br>
 * 
 * @author Jake Vissicchio
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;

public class ShoppingDemoVissicchio 
{
	/**
	 * Declaring keyboard for user input
	 * Formats decimal values to be read as money
	 */
	static Scanner keyboard = new Scanner(System.in);
	static DecimalFormat moneyStyle = new DecimalFormat("0.00");
	
	public static void main(String[] args) 
	{
		/**
		 * creates a new keyedList object
		 */
		KeyedListVissicchio keyedList = new KeyedListVissicchio();
		/**
		 * initializes the object newItem as null
		 */
		ItemVissicchio newItem = null;
		/**
		 * initializes the string fake
		 */
		String fake;
		/**
		 * initializes the character choice
		 */
		char choice;
		/**
		 * initializes the string name
		 */
		String name = "none";
		/**
		 * initializes the integer quant
		 */
		int quant = 0;
		/**
		 * initializes the double price
		 */
		double price = 0.0;
		/**
		 * greets the user
		 */
		System.out.println("Welcome to the Shopping Demo Program!");
		
		/**
		 * Asks user for the path and name to the file				
		 */
		System.out.print("Enter a file name: ");
		String fileName = keyboard.next();
		
		/**
		 * Creates the reference to the file
		 */
		File myFile = new File(fileName);
		  
		/**
		 * Try to open and use the file the user inputted if possible
		 */
		try
		{
		  /**
		   * Creates a scanner object that reads the file
		   */
		  Scanner input = new Scanner(myFile);

		  int numItems = 0;
			  
		  /**
		   * Reads the first line of the text file to see how many songs are in it
		   */
		  numItems = input.nextInt();
			  
		  for (int i = 0; i < numItems; i++)
		  {
			name = input.next();
			quant = input.nextInt();
			price = input.nextDouble();
			newItem = new ItemVissicchio(name, quant, price);
			if (keyedList.add(newItem) == true)
			  {
				System.out.println(name + " was added to the cart.");
			  }//if
			else
			  {
				System.out.println(name + " cannot be added because it is either a "
				  		+ "duplicate or the cart is full.");
			  }//else
		  }//for i
			  
		  /**
		   * closes file
		   */
		  input.close();
		  
		  }//try
		  /**
		   * catchers will try to locate any errors that were caused when trying to read the file
		   */
		  catch(FileNotFoundException ex)
		  {
			  System.out.println("Failed to find file: " + myFile.getAbsolutePath());
		  }//catch
		  catch(InputMismatchException ex)
		  {
			  System.out.println("Type mismatch for song details I just tried to read.");
			  System.out.println(ex.getMessage());
		  }//catch
		  catch(NumberFormatException ex)
		  {
			  System.out.println("Failed to convert String text into an integer value.");
			  System.out.println(ex.getMessage());
		  }//catch 
		  catch(NullPointerException ex)
		  {
			  System.out.println("Null pointer exception.");
			  System.out.println(ex.getMessage());
		  }//catch
		  catch(Exception ex)
		  {
			  System.out.println("Something went wrong");
			  ex.printStackTrace();
		  }//catch

		do
		{
			/**
			 * Prints a menu with 10 possible choices that allows users to
			 * keep making choices until they input '0'
			 */
			System.out.println("Menu");
			System.out.println("1: Add an item to the list");
			System.out.println("2: Delete an item from the list");
			System.out.println("3: Print each item in the list");
			System.out.println("4: Search for a user-specified item in the list");
			System.out.println("5: Count the total number of items in the list");
			System.out.println("6: Total the cost of the items in the list");
			System.out.println("7: Determine whether the list is empty");
			System.out.println("8: Determine whether the list is full");
			System.out.println("9: Clear the list");
			System.out.println("0: Quit");
			System.out.println("Enter the number that corresponds with "
					+ "your choice.");
			fake = keyboard.next();
			choice = fake.charAt(0);
							
			switch(choice)
			{
			/**
			 * User can add an item by inputting the name, quantity, and price
			 * of the item. This will not be added to the list if it is already full.
			 */
			case '1': System.out.println("Please provide information about the item to add.");
					  System.out.println("Enter the item name: ");
					  name = keyboard.next();
					  System.out.println("Enter the quantity: ");
					  quant = keyboard.nextInt();
					  System.out.println("Enter the unit price: ");
					  price = keyboard.nextDouble();
					  newItem = new ItemVissicchio(name, quant, price);
					  if(keyedList.add(newItem) == true)
					  {
						  System.out.println(name + " was added to the cart.");
					  }//if
					  else
					  {
						  System.out.println(name + " cannot be added because it is either a "
						  		+ "duplicate or the cart is full.");
					  }//else
					  break;
			/**
			 * User can remove an item of choice by inputting the name of the item. This will not
			 * work if the item is not in the list
			 */
			case '2': System.out.println("Please enter the name of item to delete from your cart: ");
					  name = keyboard.next();
					  if(keyedList.remove(name) == true)
					  {
						  System.out.println(name + " was removed from the cart.");
					  }//if
					  else
					  {
						  System.out.println("Sorry, but your cart does not contain the item " + name);
					  }//else
					  break;
			/**
			 * Prints out every item in the cart and their details
			 */
			case '3': System.out.println("Your Cart: ");
					  keyedList.print();
					  break;
			/**
			 * User can retrieve an item of choice by inputting the name of the item. This will
			 * not work if the item is not in the list
			 */
			case '4': System.out.println("Please enter the name of the item to find in your cart: ");
					  name = keyboard.next();
					  if(keyedList.retrieve(name) != null)
					  {
					   System.out.println("Yes, you have " + keyedList.retrieve(name).getNums() + " " +
							   	keyedList.retrieve(name).getName() + "(s) at $" + 
							   	moneyStyle.format(keyedList.retrieve(name).getPrice()) + " each.");
					  }//if
					  else
					  {
						  System.out.println("Sorry your cart does not contain the item " + name);
					  }//else
					  break;
			/**
			 * Prints out the total quantity of items in the user's cart
			 */
			case '5': System.out.println("Your cart contains a "
					+ "total of " + keyedList.getCount() + " items.");
					  break;
			/**
			 * Prints out the total price of the items in the user's cart
			 */
			case '6': System.out.println("The total price of all of your "
					+ "items is $" +  moneyStyle.format(keyedList.calcTotal()));
					  break;
			/**
			 * Lets the user know if the list is empty or not
			 */
			case '7': if(keyedList.isEmpty() == true)
			          {
					  	  System.out.println("Your cart is empty.");
			          }//if
					  else
					  {
						  System.out.println("Your cart is not empty.");
					  }//else
					  break;
			/**
			 * Lets the user know if the list is full or not
			 */
			case '8': if(keyedList.isFull() == true)
	          		  {
			  			  System.out.println("Your cart is full.");
	          		  }//if
			  		  else
			  		  {
			  			  System.out.println("Your cart is not full.");
			  		  }//else 
					  break;
			/**
			 * Clears the list
			 */
			case '9': keyedList.clear();
					  break;
			/**
			 * If the user inputs '0' the program will end
			 */
			case '0': System.out.println("Goodbye!");
			  		break;
			/**
			 * If invalid input is given for the menu, the user will be
			 * given an error message and be able to try other input for
			 * the menu.
			 */
			default: System.out.println("Error. That is invalid input");
			}//switch
		}//do
		while(choice != '0');
		
		/**
		 * Closes keyboard
		 */
		keyboard.close();
	}//main

}//ShoppingDemoVissicchio
