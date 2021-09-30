/**
 * this is the class definition for PokemonCardVissicchio <br>
 * 
 * @author Jake Vissicchio
 *
 */
public class PokemonCardVissicchio 
{
	
	/**
	 * initializes the integer myPower
	 */
	private int myPower;
	
	/**
	 * initializes the string myName
	 */
	private String myName;
	
	/**
	 * the default constructor of PokemonCardVissicchio
	 */
	public PokemonCardVissicchio()
	{
		myPower = 0;
		
		myName = "none";
		
	}//PokemonCardVissicchio
	
	/**
	 * The full constructor for PokemonCardVissicchio
	 * @param newPower the incoming name of the Pokemon's power
	 * @param newName the incoming name of the Pokemon's name
	 */
	public PokemonCardVissicchio(int newPower, String newName)
	{
		myName = newName;
		
		myPower = newPower;
		
	}//PokemonCardVissicchio

	/**
	 * The setter for the Pokemon's power
	 * @param newPower the incoming name of the Pokemon's power
	 */
	public void setPower(int newPower)
	{
		myPower = newPower;
	}//setNums
	
	/**
	 * The setter for the Pokemon's name
	 * @param newName the incoming name of the Pokemon's name
	 */
	public void setName(String newName)
	{
		myName = newName;
	}//setName
	
	/**
	 * The getter for the Pokemon's power
	 * @return the power of the Pokemon
	 */
	public int getPower()
	{
		return myPower;
	}//getNums
	
	/**
	 * The getter for the Pokemon's power
	 * @return the name of the Pokemon
	 */
	public String getName()
	{
		return myName;
	}//getName
	
	/**
	 * returns details about the Pokemon Card as a string
	 */
	public String toString()
	{
		String details = myPower + myName;
		return details;
		
	}//toString
	
}//PokemonCardVissicchio
