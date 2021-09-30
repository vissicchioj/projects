/**
 * this is the class definition for NodeVissicchio <br>
 * 
 * @author Jake Vissicchio
 *
 */
public class NodeVissicchio 
{
	/**
	 * initializes the PokemonCardVissicchio myData
	 */
	private PokemonCardVissicchio myData;
	/**
	 * initializes the NodeVissicchio myNext
	 */
	private NodeVissicchio myNext;
	/**
	 * The default constructor for NodeVissicchio
	 */
	public NodeVissicchio()
	{
		myData = null;
		
		myNext = null;
		
	}//NodeVissicchio
	/**
	 * The full constructor for NodeVissicchio
	 * @param newData the incoming data for this node
	 */
	public NodeVissicchio(PokemonCardVissicchio newData)
	{
		
		myData = newData;
		
		myNext = null;
		
	}//NodeVissicchio
	/**
	 * the getter for this node's data
	 * @return the data for this node
	 */
	public PokemonCardVissicchio getData()
	{
		
		return myData;
	}//getData
	/**
	 * the setter for this node's data
	 * @param newData the incoming data for this node
	 */
	public void setData(PokemonCardVissicchio newData)
	{
		
		myData = newData;
	}//setData
	/**
	 * the getter for the next node
	 * @return the next node
	 */
	public NodeVissicchio getNext()
	{
		
		return myNext;
	}//getNext
	/**
	 * the setter for the next node
	 * @param newNext the incoming name for the next node
	 */
	public void setNext(NodeVissicchio newNext)
	{
		
		myNext = newNext;
	}//setNext
	
}//NodeVissicchio