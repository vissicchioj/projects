
public class MergeVissicchio 
{
    int comparisonCount = 0;

    //merge puts together two temp arrays
    void merge(String array[], int left, int mid, int right)
    {

        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        String leftArray[] = new String[leftSize];
        String rightArray[] = new String[rightSize];

        //fill data for our two temp arrays (leftArray and rightArray)
        for (int i = 0; i < leftSize; i++)
        {
            leftArray[i] = array[left + i];
        }//for
        for (int j = 0; j < rightSize; j++)
        {
            rightArray[j] = array[mid + 1 + j]; 
        }//for

        int i = 0;
        int j = 0;
        int k = left;

        //puts together the two leftArray and rightArray into our main array
        while (i < leftSize && j < rightSize)
        {
            int result = leftArray[i].compareToIgnoreCase(rightArray[j]);
            if(result < 0)
            {
                comparisonCount++;
                array[k] = leftArray[i];
                i++;
            }//if
            else
            {
                comparisonCount++;
                array[k] = rightArray[j];
                j++;
            }//else

            k++;
        }//while
        while (i < leftSize)
        {
            array[k] = leftArray[i];
            i++;
            k++;
        }//while
        while (j < rightSize)
        {
            array[k] = rightArray[j];
            j++;
            k++;
        }//while
    }//merge

    //sorts a given array by making use of merge()
    void mergeSort(String array[], int left, int right)
    {
        //will allow us to divide the array to temp arrays until each array has a length of one
        if (left < right)
        {
            //mid represents the end of the left array while mid2 represents the beginning of the right array
            int mid = (left + right)/2;
            int mid2 = mid + 1;

            //divides the array into temp arrays and sorts them
            mergeSort(array, left, mid);
            mergeSort(array, mid2, right);

            //using merge to merge the temp arrays
            merge(array, left, mid, right);
        }//if
    }//mergeSort
    
    //will print out all of the comparisons found through quick sort (should def be smaller than selection and insertion)
    public void mergeComp(String array[], int left, int right)
    {
        mergeSort(array, left, right);
        System.out.println("Merge Sort had " + comparisonCount + " comparisons.");
    }//mergeComp
}//MergeVissicchio
