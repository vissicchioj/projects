import java.util.*;

public class VertexVissicchio 
{
    //This node (vertex) class is used for the Linked Objects graph

    int id;
    boolean isProcessed;
    ArrayList<VertexVissicchio> neighbors;

    //constructor for VertexVissicchio
	public VertexVissicchio(int newId)
	{
		id = newId;
		isProcessed = false;
        neighbors = new ArrayList<VertexVissicchio>();
	}//VertexVissicchio

}
