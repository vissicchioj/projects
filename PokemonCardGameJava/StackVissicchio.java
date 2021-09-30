/**
 * this is the class definition for StackVissicchio <br>
 * 
 * @author Jake Vissicchio
 *
 */

public class StackVissicchio 
{
	/**
	 * Instance variable for the top of the stack
	 */	
	private NodeVissicchio myTop;
	
	/**
	 * default constructor for StackVissicchio that creates an empty stack
	 */
	public StackVissicchio()
	{
		
		myTop = null;
	}//StackVissicchio
	
	/**
	 * pushes a card to the stack
	 * @param mon the incoming name of the PokemonCardVissicchio object
	 * @return if a card can be pushed or not
	 */
	public boolean push(PokemonCardVissicchio mon)
	{
		
		boolean ans = false;
		
		NodeVissicchio newMon = null;
		
		if(isFull() == false)
		{
			
			ans = true;
			
			newMon = new NodeVissicchio();
			
			newMon.setData(mon);
			
			newMon.setNext(myTop);
			
			myTop = newMon;
		}//if
		
		return ans;
		
	}//push
	
	/**
	 * pops a card from the stack
	 * @return the PokemonCardVissicchio object that was popped
	 */
	public PokemonCardVissicchio pop()
	{
		
		PokemonCardVissicchio ans = null;
		if(isEmpty() == false)
		{
			ans = myTop.getData();
			
			myTop = myTop.getNext();
		}//if
		
		return ans;
		
	}//pop
	
	/**
	 * @return if the stack is empty or not
	 */
	public boolean isEmpty()
	{
		
		return(myTop == null);
	}//isEmpty
	
	/**
	 * @return if the stack is full or not
	 */
	public boolean isFull()
	{
		
		return false;
	}//isFull
}//StackVissicchio
