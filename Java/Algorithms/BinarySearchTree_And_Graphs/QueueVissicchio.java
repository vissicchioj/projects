//class definition of the Queue class using NodeVissicchio

public class QueueVissicchio
{
	//This queue will be used for BFS Traversal
	
	//variables that represent the head and the tail of the queue
	NodeVissicchio head;
	NodeVissicchio tail;
	
	public void enqueue(VertexVissicchio vertex)
	{
		NodeVissicchio oldTail = tail;
		tail = new NodeVissicchio();
		tail.vertex = vertex;
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
	
    
	public VertexVissicchio dequeue()
	{
		VertexVissicchio retVertex = null;
		if (!isEmpty())
		{
			retVertex = head.vertex;
			head = head.next;
			if(isEmpty())
			{
				tail = null;
			}//if
		}//if
		return retVertex;
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