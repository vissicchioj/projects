/**
 * this is the class definition for KeyedListVissicchio <br>
 * 
 * @author Jake Vissicchio
 *
 */
public class KeyedListVissicchio 
{

	/**
	 * Instance variable for the head of the list
	 */	
	private NodeVissicchio myHead;
	
	/**
	 * default constructor for KeyedListVissicchio that creates an empty list
	 */
	public KeyedListVissicchio()
	{
		
		myHead = null;
	}//ItemVissicchio
	/**
	 * The getter for this list's head
	 * @return the head of the list
	 */
	public NodeVissicchio getHead()
	{
		return myHead;
	}//getHead
	/**
	 * The setter for this list's head
	 * @param newHead the incoming head of the list
	 */
	public void setHead(NodeVissicchio newHead)
	{
		
		myHead = newHead;
	}//setHead
	/**
	 * clears the list
	 */
	public void clear()
	{
		myHead = null;
		System.out.println("Your cart has been cleared.");
	}//clear
	/**
	 * finds the index of the target name of the items in the list
	 * @param keyValue the incoming name for the item name
	 * @return the index of the target name
	 */
	
	/**
	 * adds an item to the list if it is not full and if there is no duplicate
	 * @param product the incoming name of the added item
	 * @return if an item can be added or not
	 */
	public boolean add(ItemVissicchio product)
	{
		boolean canAdd = false;
		NodeVissicchio curr = myHead;
		NodeVissicchio prev = null;
		NodeVissicchio newProduct = null;
		boolean dupe = false;
		boolean found = false;
		
		while((curr != null) && (dupe == false))
		{
		
			if(curr.getData().getName().equalsIgnoreCase(product.getName()))
				{
					dupe = true;
				}//if
			else
			{
				curr=curr.getNext();
			}//else
		}//while
		
		if(dupe == false)
		{

			while((curr != null) && (!found))
			{
				if(curr.getData().getName().compareToIgnoreCase(product.getName()) > 0)
				{
					found = true;
				}//if
				else
				{
					prev = curr;
					curr = curr.getNext();
				}//else
			}//while
			
			newProduct = new NodeVissicchio(product);
			
			if(prev == null)
			{
				newProduct.setNext(myHead);
				myHead = newProduct;
			}//if
			else
			{
				newProduct.setNext(curr);
				prev.setNext(newProduct);
			}//else

			
			canAdd = true;
		}//if
		
		return canAdd;
		
	}//add
	
	/**
	 * removes an item in the list if it exists in the list
	 * @param keyValue the incoming name of the items name
	 * @return if an item can be removed or not
	 */
	public boolean remove(String keyValue)
	{
		boolean canRemove = false;
		NodeVissicchio curr = myHead;
		NodeVissicchio prev = null;
		boolean found = false;
		while ((curr != null) && (found == false))
		{
			if(curr.getData().getName().equalsIgnoreCase(keyValue))
			{
				found = true;
			}//if
			else
			{
				prev = curr;
				curr = curr.getNext();
			}//else
		}//while
		if(found == true)
		{
			if(prev == null)
			{
				myHead = curr.getNext();
			}//if
			else
			{
				prev.setNext(curr.getNext());
			}//else
			canRemove = true;
		}//if
		
		return canRemove;
	}//remove
	/**
	 * retrieves the item in the list if it exists in the list
	 * @param keyValue the incoming name of the items name
	 * @return the retrieved item object
	 */
	public ItemVissicchio retrieve(String keyValue)
	{
		ItemVissicchio findItem = null;
		NodeVissicchio curr = myHead;
		NodeVissicchio prev = null;
		boolean found = false;
		while ((curr != null) && (found == false))
		{
			if(curr.getData().getName().equalsIgnoreCase(keyValue))
			{
				found = true;
			}//if
			else
			{
				prev = curr;
				curr = curr.getNext();
			}//else
		}//while
		if((found = true) && (curr != null))
		{
			findItem = curr.getData();
		}//if
		return findItem;
	}//retrieve
	/**
	 * 
	 * @return if the list is empty or not
	 */
	public boolean isEmpty()
	{
		
		return(myHead == null);
	}//isEmpty
	/**
	 * 
	 * @return if the list is full or not
	 */
	public boolean isFull()
	{
		return false;
	}//isFull
	/**
	 * Prints out every item in the cart and their details
	 */
	public void print()
	{
		NodeVissicchio curr = myHead;
		while(curr != null)
		{
			
			System.out.println(curr.getData().toString());
			curr = curr.getNext();
		}//while
	}//print
	/**
	 * gets the quantity of items in the list
	 * @return the total quantity
	 */
	public int getCount()
	{
		int count = 0;
		NodeVissicchio curr = myHead;
		while(curr != null)
		{
			count += curr.getData().getNums();
			curr = curr.getNext();
		}//while
		return count;
	}//getCount
	/**
	 * calculates the price total of every item in the list
	 * @return the total price
	 */
	public double calcTotal()
	{
		double total = 0.0;
		NodeVissicchio curr = myHead;
		while(curr != null)
		{
			total += (curr.getData().getPrice() * curr.getData().getNums());
			curr = curr.getNext();
		}//while
		
		return total;
	}//calcTotal
}//KeyedListVissicchio
