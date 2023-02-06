
public class SelectionVissicchio
{
	//swaps two given elements in an array
	void swap(String array[], int a, int b)
	{
		String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
	}//swap


    public void selectionSort(String array[]) 
	{
        int comparisonCount = 0;
		int n = array.length;

		for (int i = 0; i <= n - 2; i++)
		{
            comparisonCount++;
			//smallPos will keep track of what is the min value in the array(or in this case coming first in the alphabet)
			int smallPos = i;
			//attempting to find a new smallPos in the array
			for (int j = i + 1; j <= n - 1; j++) 
			{
                comparisonCount++;
				int result = array[j].compareToIgnoreCase(array[smallPos]);
				if (result < 0)
				{
                    comparisonCount++;
					smallPos = j;	
				}//if
			}//for j
			//makes use of swap to swap the new smallPos with array[i]
			swap(array, smallPos, i);
		}//for i
		
        System.out.println("Selection Sort had " + comparisonCount + " comparisons.");
	}//selectionSort

}//SelectionVissicchio