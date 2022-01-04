import java.util.*;

public class GraphAdjListVissicchio 
{
    
    public int numVerticies = 0;
    //adjaceny list as a hashtable (using a modified version of my hashtable from assignment 3 which is an array of queues)
    QueueHashVissicchio[] hashTable;

    public GraphAdjListVissicchio(int size)
    {
        numVerticies = size;
        hashTable = new QueueHashVissicchio[numVerticies];
    }

    public void emptyHash()
    {
        //sets every element in the table to null
        for (int i = 0; i < numVerticies; i++)
        {
            hashTable[i] = null;
        }//for
    }//setUpHash

    //initialize the hashtable to so that each index becomes a new queue 
    public void initializeHash()
    {
        for (int i = 0; i < numVerticies; i++)
        {
            hashTable[i] = new QueueHashVissicchio();
            //the first vertex of every queue must be the index number so that it prints nicely
            hashTable[i].enqueue(i);
        }
    }
    
    //add the edge by linking two verticies together making them neighbors
    public void addEdgeAdjList(int current, int neighbor)
    {
        hashTable[current].enqueue(neighbor);
        hashTable[neighbor].enqueue(current);
    }
    
    //prints out the entire adjaceny list
    public void printAdjList()
    {
        for (int i = 0; i < numVerticies; i++)
        {
            int j = 0;
            while (!hashTable[i].isEmpty())
            {
                System.out.print(hashTable[i].dequeue());
                if (j == 0)
                {
                    if (i < 10)
                    {
                        System.out.print(" ");
                    }
                    System.out.print("| ");
                    j++;
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
