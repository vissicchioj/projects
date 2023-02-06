public class GraphMatrixVissicchio 
{
    //if line contains vertex 0 remove 1 count because i will always have one extra count 

    //matrix is a 2D array
    public int matrix [][];
    public int numVerticies = 0;

    //takes in the count of total verticies to find the total size
    public GraphMatrixVissicchio(int size)
    {
        numVerticies = size;
        matrix = new int[numVerticies][numVerticies];
    }

    //add the edge by linking two verticies together making them neighbors
    public void addEdgeMatrix(int current, int neighbor)
    {
        matrix[current][neighbor] = 1;
        matrix[neighbor][current] = 1;
    }

    //prints out the entire matrix
    public void printMatrix()
    {
        //System.out.println("Graph Matrix: ");
        System.out.print("   ");
        //System.out.print(" ");
        for (int k = 0; k < numVerticies; k++)
        {
            System.out.print(k + " ");
            if (k < 10)
            {
                System.out.print(" ");
            }
        }
        System.out.println("");
        for (int i = 0; i < numVerticies; i++)
        {
            System.out.print(i + " ");
            if (i < 10)
            {
                System.out.print(" ");
            }
            for (int j = 0; j < numVerticies; j++)
            {
                //System.out.print(j);
                System.out.print(matrix[i][j] + "  ");
                //if (i < 10)
                //{
                //System.out.print(" ");
                //}
            }
            System.out.println("");
        }
        
    }


}
