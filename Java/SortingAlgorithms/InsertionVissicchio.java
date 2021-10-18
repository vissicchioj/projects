
public class InsertionVissicchio 
{

    public void insertionSort(String array[]) 
	{
        int comparisonCount = 0;
		int n = array.length;
		for (int i = 1; i < n; i++)
		{
            comparisonCount++;
			String key = array[i];
            int j = i - 1;
            //if the element is later in the alphabet compared to the key, move the element one spot from
            //the current position
            while (j >= 0 && array[j].compareToIgnoreCase(key)  > 0)
            {
                comparisonCount++;
                array[j+1] = array[j];
                j = j - 1;
            }//while
            array[j+1] = key;
		}//for i
        System.out.println("Insertion Sort had " + comparisonCount + " comparisons.");
	}//insertionSort
}//InsertionSort
