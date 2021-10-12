/**
 * this is the class definition for ItemVissicchio <br>
 * 
 * @author Jake Vissicchio
 *
 */
import java.text.DecimalFormat;

public class ItemVissicchio 
{
	static DecimalFormat moneyStyle = new DecimalFormat("0.00");
	/**
	 * initializes the string myName
	 */
	private String myName;
	/**
	 * initializes the integer myNums
	 */
	private int myNums;
	/**
	 * initializes the double myPrice
	 */
	private double myPrice;
	/**
	 * the default constructor of ItemVissicchio
	 */
	public ItemVissicchio()
	{
		myName = "none";
		myNums = 0;
		myPrice = 0.0;
	}//ItemVissicchio
	/**
	 * the full constructor of ItemVissicchio
	 * @param newName the incoming name of the item
	 * @param newNums the incoming quantity of the item
	 * @param newPrice the incoming price of the item
	 */
	public ItemVissicchio(String newName, int newNums, double newPrice)
	{
		myName = newName;
		myNums = newNums;
		myPrice = newPrice;
	}//ItemVissicchio
	/**
	 * the setter for the item's name
	 * @param newName the incoming name of the item
	 */
	public void setName(String newName)
	{
		myName = newName;
	}//setName
	/**
	 * the setter for the item's quantity
	 * @param newNums the incoming quantity of the item
	 */
	public void setNums(int newNums)
	{
		myNums = newNums;
	}//setNums
	/**
	 * the setter for the item's price
	 * @param newPrice the incoming price of the item
	 */
	public void setPrice(double newPrice)
	{
		myPrice = newPrice;
	}//setPrice
	/**
	 * the getter for the item's name
	 * @return the name of the item
	 */
	public String getName()
	{
		return myName;
	}//getName
	/**
	 * the getter for the item's quantity
	 * @return the quantity of the item
	 */
	public int getNums()
	{
		return myNums;
	}//getNums
	/**
	 * the getter for the item's price
	 * @return the price of the item
	 */
	public double getPrice()
	{
		return myPrice;
	}//getPrice
	/**
	 * returns details about the item as a string
	 */
	public String toString()
	{
		String details = "Item Name: " + myName + "\n";
		details += "Quantity: " + myNums + "\n";
		details += "Price: $" + moneyStyle.format(myPrice) + "\n";
		return details;
	}//toString
	
	
}//ItemVissicchio
