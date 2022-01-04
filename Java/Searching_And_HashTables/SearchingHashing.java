import java.util.*;
import java.io.*;

public class SearchingHashing {
    public static void main(String[] args) 
	{

        //variables
		int i = 0;
		float avgLinear = 0;
		float avgBinary = 0;
		float avgHash = 0;

		QuickSortVissicchio qSort = new QuickSortVissicchio();
		LinearSearchVissicchio lSearch = new LinearSearchVissicchio();
		BinarySearchVissicchio bSearch = new BinarySearchVissicchio();
		KnuthVissicchio knuth = new KnuthVissicchio();
		HashTableVissicchio hashTable = new HashTableVissicchio();


        //initializing array of magicitems
		String[] items = new String[666];
		String[] tempArray = new String[666];
		String[] randoItems = new String[42];

		//for use in QuickSort and Binary Search
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

			/*
				Making use of KnuthShuffle to randomly take 42 items from the temp array
				and place them into a new array of 42 items to use for searching
				while still keeping the original array sorted to be used for binary search.
			*/
			for (i = 0; i < tempArray.length; i++)
			{
				tempArray[i] = items[i];
			}
			knuth.shuffle(tempArray);
			for (i = 0; i < randoItems.length; i++)
			{
				randoItems[i] = tempArray[i];
			}

            qSort.quickSort(items, 0, n);

			
			System.out.println("Linear Search: ");
			System.out.println("-------------------------------------------------------");
			for (i = 0; i < 42; i++)
			{
				avgLinear = avgLinear + lSearch.linearSearch(items, randoItems[i]);
			}
			avgLinear = avgLinear/42;
			System.out.println(avgLinear + " is the average number of comparisons for Linear Search");
			System.out.println("");


			System.out.println("Binary Search: ");
			System.out.println("-------------------------------------------------------");
			for (i = 0; i < 42; i++)
			{
				avgBinary = avgBinary + bSearch.binaryComp(items, randoItems[i], 0, n);
			}
			avgBinary = avgBinary/42;
			System.out.println(avgBinary + " is the average number of comparisons for Binary Search");
			System.out.println("");

			for (i = 0; i < items.length; i++)
			{
				hashTable.insertHash(items[i]);
			}	
			///*
			System.out.println("Hash Table Search: ");
			System.out.println("-------------------------------------------------------");
			for (i = 0; i < 42; i++)
			{
				//because dequeue will remove the items in the process of getting the item
				//the table needs to be reset with all of the items in between every search
				avgHash = avgHash + hashTable.getHash(randoItems[i]);
				hashTable.emptyHash();
				for (int j = 0; j < items.length; j++)
				{
					hashTable.insertHash(items[j]);
				}
			}
			avgHash = avgHash/42;
			System.out.println(avgHash + " is the average number of comparisons for the searches in the Hash Table");

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

}//SearchingHashing