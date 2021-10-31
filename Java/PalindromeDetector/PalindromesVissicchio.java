import java.util.*;
import java.io.*;


public class PalindromesVissicchio
{
    
	public static void main(String[] args) 
	{
		//variables
		StackVissicchio stack = new StackVissicchio();
		QueueVissicchio queue = new QueueVissicchio();
		
		int i = 0;
		String normalString = "";
		String reverseString = "";
		
		//initializing array of magicitems
		String[] items = new String[666];
		
		
		
		
		
		//Creates the reference to the file magicitems.txt
		File myFile = new File("magicitems.txt");
		
		//Try and catch to open and use the file and if not it will locate the errors caused
		try
		{
			//Creates a scanner object that reads the file
			Scanner input = new Scanner(myFile);
			
			//Populates the array of items
			while(input.hasNext())
			{
				
				items[i] = input.nextLine();
				i++;
			}//while
			
			
			//Goes through every line of magicitems.txt
			for (int j = 0; j < items.length; j++)
			{
				/*
				Goes through each character of the line while making sure 
				the length is the item string without spaces
				*/
				for (int k = 0; k < items[j].replace(" ", "").length(); k++)
				{
					/*
					Push and enqueue each character of the string while 
					removing spaces and making it lower case
					*/
					stack.push(items[j].replace(" ", "").toLowerCase().charAt(k));
					queue.enqueue(items[j].replace(" ", "").toLowerCase().charAt(k));
				
	
				}//for k

				/*
				Dequeues and pops until empty. The dequeue will create the normal string, 
				the pop will create the reversed string
				*/
				while (!queue.isEmpty())
				{	
					
					normalString = normalString + queue.dequeue();
					reverseString = reverseString + stack.pop();
				}

				/*
				If the normal string and the reversed string are equal to eachother that magicitem is a palindrome
				*/
				if (reverseString.contentEquals(normalString))
				{
					
					System.out.println(items[j] + " is a palindrome!");
				}
				normalString = "";
				reverseString = "";
			}//for j
			
			//closes file
			input.close();

		}//try
		
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
	    
	}//main
	
}//PalindromesVissicchio

