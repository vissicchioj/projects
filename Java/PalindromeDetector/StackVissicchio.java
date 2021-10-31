//class definition of the Stack class using NodeVissicchio

public class StackVissicchio
{
	//variable that represents the top of the stack
	private NodeVissicchio top = null;
	
	public void push(char item)
	{
		NodeVissicchio oldTop = top;
		top = new NodeVissicchio();
		top.myItem = item;
		top.next = oldTop;
	}//push
	
	public char pop()
	{
		char retVal = '\0';
		if(!isEmpty())
		{
			retVal = top.myItem;
			top = top.next;
			
		}//if
		return retVal;
	}//pop
	
	public boolean isEmpty()
	{
		if (top == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}//isEmpty
	
}//Stack