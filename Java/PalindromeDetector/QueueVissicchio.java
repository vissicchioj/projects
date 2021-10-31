//class definition of the Queue class using NodeVissicchio

public class QueueVissicchio
{
	//variables that represent the head and the tail of the queue
	NodeVissicchio head;
	NodeVissicchio tail;
	
	public void enqueue(char myItem)
	{
		NodeVissicchio oldTail = tail;
		tail = new NodeVissicchio();
		tail.myItem = myItem;
		tail.next = null;
		if (isEmpty())
		{
			head = tail;
		}
		else 
		{
			oldTail.next = tail;
		}
	}//enqueue
	
	public char dequeue()
	{
		char retVal = '\0';
		if (!isEmpty())
		{
			retVal = head.myItem;
			head = head.next;
			if(isEmpty())
			{
				tail = null;
			}//if
		}//if
		return retVal;
	}//dequeue
	
	public boolean isEmpty()
	{
		if (head == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}//isEmpty
	
}//QueueVissicchio