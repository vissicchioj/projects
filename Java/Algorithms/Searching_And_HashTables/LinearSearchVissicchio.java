
public class LinearSearchVissicchio 
{

    public int linearSearch(String array[], String target)
    {

        int i = 0;
        boolean found = false;
        int compCount = 0;
        int result = -1;

        //go through array to search for the specified item until we find it or we reach the end
        while (i < array.length && result != 0)
        {
            compCount++;
            result = array[i].compareToIgnoreCase(target);
            if(result == 0)
            {
                //compCount++;
                System.out.println("The item, " + target + ", was found at index " + i);
                found = true;
            }//if
            i++;
        }//while

        if (found == false)
        {
            System.out.println("The item, " + target + ", was not found");
        }
        System.out.println("Linear Search had " + compCount + " comparisons.");
        System.out.println("");

        return compCount;
    }//linearSearch
}//LinearSearchVissicchio
