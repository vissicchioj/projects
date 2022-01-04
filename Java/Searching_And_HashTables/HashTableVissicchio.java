

public class HashTableVissicchio 
{
    Hashing hash = new Hashing();

    int HASH_TABLE_SIZE = 250;

    QueueVissicchio[] hashTable = new QueueVissicchio[HASH_TABLE_SIZE];

    public void emptyHash()
    {
        //sets every element in the table to null
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
        {
            hashTable[i] = null;
        }//for
    }//setUpHash

    public void insertHash(String newItem)
    {
        //find the index for the item to be inserted to based on the hashing function
        int index = hash.makeHashCode(newItem);

        //the index is null, declare it as a new queue then enqueue the newItem into it
        if (hashTable[index] == null)
        {
            hashTable[index] = new QueueVissicchio();
            hashTable[index].enqueue(newItem);
        }
        //The queue already exists in this index so enqueue the newItem into it to create chaining
        else
        {
            hashTable[index].enqueue(newItem);
        }
    }//insertHash

    public int getHash(String target)
    {
        boolean found = false;
        int compCount = 0;
        int result = -1;

        //find the index where the target item is based on the hashing function
        int index = hash.makeHashCode(target);

        //this means that the index is null and does not have a queue therefore the target item is not found
        if (hashTable[index] == null)
        {
            compCount++;
            found = false;
        }
        else
        {
            result = hashTable[index].dequeue().compareToIgnoreCase(target);
            //Go through the queue within the index until the target item is found or the queue is empty
            while (!hashTable[index].isEmpty() && result != 0)
            {
                compCount++;
                result = hashTable[index].dequeue().compareToIgnoreCase(target);
            }
            if (result == 0)
            {
                compCount++;
                found = true;
                System.out.println("The item, " + target + " was found.");
            }
            else
            {
                compCount++;
                found = false;
            }
        }
        if (found == false)
        {
            System.out.println("The item, " + target + " was  not found.");
        }

        System.out.println("Hash search had " + compCount + " comparisons.");
        System.out.println("");
        return compCount;
    }    

}//HashTableVissicchio
