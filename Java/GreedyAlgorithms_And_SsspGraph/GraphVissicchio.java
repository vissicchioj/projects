import java.util.ArrayList;

public class GraphVissicchio 
{
    public ArrayList<EdgeVissicchio> edgeList;
    public int vertexTotal;

    //constructor for GraphVissicchio
    public GraphVissicchio(int vertexCount)
    {
        //vertexTotal allows us to know the total amount of edges within the graph
        vertexTotal = vertexCount;
        edgeList = new ArrayList<EdgeVissicchio>();
    }

    
    //add the edge by creating a new edge and adding it to the edgeList
    public void addEdge(GraphVissicchio graph, int from, int to, int cost)
    {
        EdgeVissicchio newEdge = new EdgeVissicchio(from, to, cost);
        graph.edgeList.add(newEdge);

    }

    public boolean bellmanFord(GraphVissicchio graph, int source)
    {
        //distance holds an array of integers that represent the distance from and to each vertex
        int distance[] = new int[vertexTotal];
        
        //Initialize single source
        for (int l = 0; l < vertexTotal; l++)
        {
            //set each distance to infinity, or in this case the maximum possible integer
            distance[l] = Integer.MAX_VALUE;
        }
        //the distance from the source to itself should be 0 since it should not have to go anywhere
        //(unless of course there is a negative weight cycle, then the distance to itself would be infinitely negative)
        distance[source] = 0;

        for (int i = 1; i < vertexTotal - 1; i++)
        {
            for (int j = 0; j < graph.edgeList.size(); j++)
            {
                //Relax:
                //if the source vertex is infinite skip it for now
                //if the set distance to a destination vertex is larger than the current source + the cost 
                //overwrite the destination vertex
                if (distance[graph.edgeList.get(j).from] != Integer.MAX_VALUE && 
                    distance[graph.edgeList.get(j).to] > distance[graph.edgeList.get(j).from] + graph.edgeList.get(j).cost)
                {
                    distance[graph.edgeList.get(j).to] = distance[graph.edgeList.get(j).from] + graph.edgeList.get(j).cost;
                }//if
            }
        }
        //Checking for negative weight cycles, if we get a negative weight cycle return false
        for (int k = 0; k < graph.edgeList.size(); k++)
        {
            if (distance[graph.edgeList.get(k).from] != Integer.MAX_VALUE && 
                distance[graph.edgeList.get(k).to] > distance[graph.edgeList.get(k).from] + graph.edgeList.get(k).cost)
            {
                return false;
            }
        }
        //Printing out the results
        //Lets us know the cost of going to each vertex starting from the source vertex
        System.out.println("Results:");
        System.out.println("----------------------------------------------");
        for (int i = 1; i < vertexTotal; ++i)
        {
            System.out.println(source + "---->" + i + " cost is " + distance[i]);
        }
        System.out.println("");
        return true;
    }

}//GraphVissicchio