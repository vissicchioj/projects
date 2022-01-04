//class definition of the Queue class using NodeVissicchio

public class QueueHashVissicchio
{
	//This queue will be used for the adjaceny list
	
	//variables that represent the head and the tail of the queue
	NodeHashVissicchio head;
	NodeHashVissicchio tail;
	
	public void enqueue(int myNum)
	{
		NodeHashVissicchio oldTail = tail;
		tail = new NodeHashVissicchio();
		tail.myNum = myNum;
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
	
    
	public int dequeue()
	{
		int retNum = 0;
		if (!isEmpty())
		{
			retNum = head.myNum;
			head = head.next;
			if(isEmpty())
			{
				tail = null;
			}//if
		}//if
		return retNum;
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