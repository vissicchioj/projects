import java.util.*;
import java.io.*;


public class SortVissicchio
{
    
	public static void main(String[] args) 
	{
		//variables
		int i = 0;

		SelectionVissicchio selSort = new SelectionVissicchio();
		InsertionVissicchio insertSort = new InsertionVissicchio();
		MergeVissicchio mSort = new MergeVissicchio();
		QuickVissicchio qSort = new QuickVissicchio();
		KnuthVissicchio knuth = new KnuthVissicchio();
		
		//initializing array of magicitems
		String[] items = new String[666];

		//for use in MergeSort and QuickSort
		int n = items.length - 1;		
		
		
		
		
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

			//shuffle then selection sort
			knuth.shuffle(items);
			selSort.selectionSort(items);

			//shuffle then insertion sort
			knuth.shuffle(items);
			insertSort.insertionSort(items);

			//shuffle then merge sort
			knuth.shuffle(items);
			mSort.mergeComp(items, 0, n);

			//shuffle then quick sort
			knuth.shuffle(items);
			qSort.quickComp(items, 0, n);

			//used this to test that the array was being sorted properly
			//for (i = 0; i < items.length; i++)
			//{
			//	System.out.println(items[i]);
			//}//for


	    
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

}//SortVissicchio
