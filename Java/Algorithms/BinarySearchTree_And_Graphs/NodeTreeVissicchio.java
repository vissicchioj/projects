public class NodeTreeVissicchio 
{
	//This node class is used for the Binary Search Tree

    String myItem;
	NodeTreeVissicchio left;
    NodeTreeVissicchio right;
    NodeTreeVissicchio parent;
	
	//constructor for NodeTreeVissicchio
	public NodeTreeVissicchio(String newItem)
	{
		myItem = newItem;
		left = null;
        right = null;
        parent = null;
	}//Node
}
