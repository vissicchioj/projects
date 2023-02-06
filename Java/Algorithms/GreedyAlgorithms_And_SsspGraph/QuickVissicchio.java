
//Using a reverse QuickSort to sort the spices from highest unit_price to lowest unit_price
public class QuickVissicchio 
{
    int comparisonCount = 0;

    //swaps two given elements in an array
    void swap(SpiceVissicchio array[], int a, int b)
    {
        SpiceVissicchio temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }//swap

    //jediPartion attempts to make sure that we do not have Sith Lord partitioning (not choosing worst possible pivot which are
    //is the minimum or maximum value)
    int jediPartition(SpiceVissicchio array[], int min, int max)
    {
        SpiceVissicchio pivot = array[max];

        int i = (min - 1);

        for (int j = min; j <= max - 1; j++)
        {
            comparisonCount++;
            //int result = array[j].compareToIgnoreCase(pivot);
            if (array[j].getUnitPrice() > pivot.getUnitPrice())
            {
                comparisonCount++;
                i++;
                swap(array, i, j);
            }//if
        }//for

        //makes use of swap to swap array[i+1] with array[max]
        swap(array, i + 1, max);
        return (i + 1);
    }//jediPartition

    //will sort the given array by making use of partition()
    void quickSort(SpiceVissicchio array[], int min, int max)
    {
        if (min < max)
        {
            comparisonCount++;
            //the location within the array where the partition is taking place
            int partLocation = jediPartition(array, min, max);

            //sort the elements before and after the partition location
            quickSort(array, min, partLocation - 1);
            quickSort(array, partLocation + 1, max);
        }//if
    }//quickSort


}//QuickVissicchio