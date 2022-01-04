//Binary Search is similar to Merge Sort's Divide step without the Conquer Step
public class BinarySearchVissicchio {

    boolean found = false;
    int compCount = 0;

    void binarySearch(String array[], String target, int left, int right)
    {

        //will allow us to divide using recusrsion until left is greater than right
        if (left <= right)
        {
            compCount++;

            /*
            mid represents the middle of the array and is the indicator to know if 
            searching for the target was successful
            */
            int mid = (right + left)/2;

            int result = array[mid].compareToIgnoreCase(target);

            //if array[mid] = target, the target was found and the search is over
            if (result == 0)
            {
                System.out.println("The item, " + target + ", was found at index " + mid);
                found = true;
            }

            //(VERY SIMILAR TO QUICKSORT SPLITTING OFF LEFT AND RIGHT OF THE PARTITION LOCATION)

            //the target is to the left of mid
            //split off to the left of mid and continue the search using recursion
            if (result > 0)
            {
                binarySearch(array, target, left, mid - 1); 
            }
            //the target is to the right of mid
            //split off to the right of mid and continue the search using recursion
            else
            {
                binarySearch(array, target, mid + 1, right);
            }

        }//if

        if (found == false)
        {
            System.out.println("The item, " + target + ", was not found");
            /*
            found is set to true so that the string is not repeatedly printed every step of 
            recursion where the item is not found
            */
            found = true;
        }

    }//binarySearch

    //will print out all of the comparisons found through binary search (should be smaller than linear search)
    public int binaryComp(String array[], String target, int left, int right)
    {
        compCount = 0;
        binarySearch(array, target, left, right);
        System.out.println("Binary Search had " + compCount + " comparisons.");
        System.out.println("");
        return compCount;
    }//binaryComp

}
