import java.util.ArrayList;

public class GraphVissicchio 
{
    VertexVissicchio vertex = null;
    ArrayList<VertexVissicchio> vertexList;


    //add the edge by linking two verticies together making them neighbors
    public void addEdgeLinkedObjects(VertexVissicchio current, VertexVissicchio newNeighbor)
    {
        //add the newNeighbor vertex to the current vertex's neighbor arraylist
        current.neighbors.add(newNeighbor);
        //add the current vertex to newNeighbor vertex's neighbor arraylist
        newNeighbor.neighbors.add(current);
    }
    
    //makes use of recursion
    //will print out the source's first neighbor, then the first neighbor's first neighbor (unprocessed), and so on
    public void depthFirstTraversal(VertexVissicchio v)
    {
        if (v.isProcessed == false)
        {
            System.out.println(v.id);
            v.isProcessed = true;
        }
        for (int i = 0; i < v.neighbors.size(); i++)
        {
            VertexVissicchio neighbor = v.neighbors.get(i);
            if (neighbor.isProcessed == false)
            {
                depthFirstTraversal(neighbor);
            }
        }
    }
    
    //needed in main to un-process everything before the next traversal
    public void unProcess(VertexVissicchio v)
    {
        if (v.isProcessed == true)
        {
            //System.out.println(v.id);
            v.isProcessed = false;
        }
        for (int i = 0; i < v.neighbors.size(); i++)
        {
            VertexVissicchio neighbor = v.neighbors.get(i);
            if (neighbor.isProcessed == true)
            {
                unProcess(neighbor);
            }
        }
    }

    //makes use of queues since it is first-in, first-out
    //will print in order of the neighbors visited starting from the source vertex
    public void breadthFirstTraversal(VertexVissicchio v)
    {
        QueueVissicchio queue = new QueueVissicchio();
        queue.enqueue(v);
        v.isProcessed = true;
        System.out.println("Breadth First Traversal: ");
        System.out.println("--------------------------------------------");
        while (!queue.isEmpty())
        {
            VertexVissicchio current = queue.dequeue();
            System.out.println(current.id);
            //int n = current.neighbors.size();
            for (int i = 0; i < current.neighbors.size(); i++)
            {
                VertexVissicchio neighbor = current.neighbors.get(i);
                if (neighbor.isProcessed == false)
                {
                    queue.enqueue(neighbor);
                    neighbor.isProcessed = true;
                }
            }
        }
    }


}//GraphVissicchio
