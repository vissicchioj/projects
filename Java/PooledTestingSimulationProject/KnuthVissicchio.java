import java.util.Random;

public class KnuthVissicchio 
{
    //swaps two given elements in an array
    void swap(int array[], int a, int b)
	{
		int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
	}//swap
    
    //randomly shuffles the array
    public void shuffle(int array[])
    {
        Random randomize = new Random();
        int n = array.length;
        for (int i = n - 1; i > 0; i--) 
        {
            int j = randomize.nextInt(i + 1);

            //makes use of swap to swap array[i] with array[j]
            swap(array, i, j);
        }//for
    }//shuffle
}//KnuthVissicchio