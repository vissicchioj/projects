/**
 * Prog 10 <br>
 * Due Date and Time: 5/4/20 before 12:00 p.m. <br>
 * 
 * Purpose: A programming assignment that uses the Stack abstract data type to make an 
 * 			adaptation of the card game War using Pokemon Cards with a power level.
 * 
 * Input: The user inputs a file with up to 52 Pokemon Cards that each have a name and power
 * 
 * Output: Outputs the Battle Card Game Summary that includes the starting cards, the amount
 * 		   of plays in the game, whether or not the game ended with a winner, the cards that
 * 		   player 1 and player 2 ended up with, and the winner of the game. 
 * 
 * Certification of Authenticity: <br>
 * I certify that this lab is entirely my own work. <br>
 * 
 * @author Jake Vissicchio
 *
 */

import java.util.*;
import java.io.*;



public class BattleDemoVissicchio 
{
	/**
	 * Declaring keyboard for user input
	 */
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		/**
		 * Creates a play stack and a discard stack for player 1
		 */
		StackVissicchio p1Play = new StackVissicchio();
		StackVissicchio p1Discard = new StackVissicchio();
		
		/**
		 * Creates a play stack and a discard stack for player 2
		 */
		StackVissicchio p2Play = new StackVissicchio();
		StackVissicchio p2Discard = new StackVissicchio();
		
		/**
		 * initializes the object pokemonCard as null
		 */
		PokemonCardVissicchio pokemonCard = null;
		/**
		 * initializes the object pokemonCard that player 1 plays as null
		 */
		PokemonCardVissicchio pokemonCard1 = null;
		/**
		 * initializes the object pokemonCard that player 2 plays as null
		 */
		PokemonCardVissicchio pokemonCard2 = null;
		
		/**
		 * initializes the integer power
		 */
		int power = 0;
		
		/**
		 * initializes the String pName
		 */
		String pName = "none";
		
		/**
		 * initializes the integer startingCount
		 */
		int startingCount = 0;
		
		/**
		 * initializes the integer turnCount
		 */
		int turnCount = 0;
		
		/**
		 * initializes the boolean p1Wins
		 */
		boolean p1Wins = false;
		/**
		 * initializes the boolean p2Wins
		 */
		boolean p2Wins = false;
		/**
		 * initializes the boolean tie
		 */
		boolean tie = false;
		/**
		 * initializes the integer player1Cards
		 */
		int player1Cards = 0;
		/**
		 * initializes the integer player2Cards
		 */
		int player2Cards = 0;
		
		/**
		 * greets the user
		 */
		System.out.println("Welcome to the Pokemon Card Battle Game!");
		
		/**
		 * calls the deal method
		 */
		deal(power, pName, pokemonCard, startingCount, p1Play, p2Play);
		
		/**
		 * Game plays until player 1 runs out of cards or player 2 runs out of cards
		 * or it goes above the turnCount of 1000
		 */
		while(!(p1Play.isEmpty() && p1Discard.isEmpty()) 
			&& (!(p2Play.isEmpty() && p2Discard.isEmpty()))
				&& (turnCount < 1000))
		{
			/**
			 * calls the play method
			 */
			play(p1Play, p2Play, p1Discard, p2Discard, pokemonCard1, pokemonCard2, p1Wins, p2Wins, tie);
			
			/**
			 * if player 1 has cards in their discard pile copy it to their play
			 * stack
			 */
			if((p1Play.isEmpty() == true) && p1Discard.isEmpty() == false)
			{
				/**
				 * calls the copy method
				 */
				copy(p1Play, p1Discard);
			}//if p1 discard stack empty
			
			/**
			 * if player 2 has cards in their discard pile copy it to their play
			 * stack
			 */
			if((p2Play.isEmpty() == true) && p2Discard.isEmpty() == false)
			{
				/**
				 * calls the copy method
				 */
				copy(p2Play, p2Discard);
			}//if p2 discard stack is empty
			
			turnCount++;
		}//while
		
		/**
		 * adds the count of player 1's play stack and discard stack to find
		 * the total number of cards that player 1 has
		 */
		player1Cards = countCards(p1Play) + countCards(p1Discard);
		
		/**
		 * adds the count of player 2's play stack and discard stack to find
		 * the total number of cards that player 2 has
		 */
		player2Cards = countCards(p2Play) + countCards(p2Discard);
		
		/**
		 * find the total cards by adding player 1 and player 2's cards
		 */
		startingCount = player1Cards + player2Cards;
		
		/**
		 * calls the printResults method
		 */
		printResults(startingCount, turnCount, player1Cards, player2Cards);
		
		/**
		 * Closes keyboard
		 */
		keyboard.close();
	}//main

	/**
	 * The deal method reads the cards from the input file and deals the cards to the
	 * player's play stacks
	 * @param pow the incoming name for the Pokemon's power
	 * @param name the incoming name for the Pokemon's name
	 * @param pCard the incoming name for the PokemonCardVissicchio object
	 * @param startCount the incoming name for the starting count
	 * @param onePlay the incoming name for player 1's stack
	 * @param twoPlay the incoming name for player 2's stack
	 */
	public static void deal(int pow, String name, PokemonCardVissicchio pCard, int startCount,
			StackVissicchio onePlay, StackVissicchio twoPlay)
	{
		/**
		 * starting deck card count
		 */
		startCount = 0;
		
		/**
		 * initializes the integer drawTurn
		 */
		int drawTurn = 0;
		
		/**
		 * Asks user for the path and name to the file				
		 */
		System.out.println("Enter a file name of Pokemon Cards: ");
		String fileName = keyboard.next();
		
		/**
		 * Creates the reference to the file
		 */
		File myFile = new File(fileName);
		
		/**
		 * Try to open and use the file the user inputted if possible
		 */
		try
		{
			/**
			 * Creates a scanner object that reads the file
			 */
			Scanner input = new Scanner(myFile);
			
			/**
			 * Reads to the end of file and it must be up to 52 cards
			 * creates new PokemonCardVissicchio objects and pushes them to player 1
			 * and player 2
			 */
			while((input.hasNext()) && (startCount <= 52))
			{
				pow = input.nextInt();
				
				name = input.next();
				
				pCard = new PokemonCardVissicchio(pow, name);
				
				drawTurn++;
				
				if(drawTurn % 2 != 0)
				{
					
					onePlay.push(pCard);
				}
				else if(drawTurn % 2 == 0)
				{
					
					twoPlay.push(pCard);
				}//else
				
				startCount++;
			}//while
			
			/**
			 * closes file
			 */
			input.close();
			
		}//try
		/**
		 * catchers will try to locate any errors that were caused when trying to read the file
	     */
		catch(FileNotFoundException ex)
		{
		  System.out.println("Failed to find file: " + myFile.getAbsolutePath());
		}//catch
		catch(InputMismatchException ex)
		{
			System.out.println("Type mismatch for song details I just tried to read.");
			System.out.println(ex.getMessage());
		}//catch
		catch(NumberFormatException ex)
		{
			System.out.println("Failed to convert String text into an integer value.");
			System.out.println(ex.getMessage());
		}//catch 
		catch(NullPointerException ex)
		{
			System.out.println("Null pointer exception.");
			System.out.println(ex.getMessage());
		}//catch
		catch(Exception ex)
		{
			System.out.println("Something went wrong");
			ex.printStackTrace();
		}//catch
		
	}//deal
	
	/**
	 * gets a card from each player
	 * @param play1 the incoming name for the player 1's play stack
	 * @param play2 the incoming name for the player 2's play stack
	 * @param dis1 the incoming name for the player 1's discard stack
	 * @param dis2 the incoming name for the player 2's discard stack
	 * @param card1 the incoming name for the card player 1 plays
	 * @param card2 the incoming name for the card player 2 plays
	 * @param oneWin the incoming name for player 1 winning
	 * @param twoWin the incoming name for player 2 winning
	 * @param equals the incoming name for a tie
	 */
	public static void play(StackVissicchio play1, StackVissicchio play2, 
			StackVissicchio dis1, StackVissicchio dis2, PokemonCardVissicchio card1,
			PokemonCardVissicchio card2, boolean oneWin, boolean twoWin, boolean equals)
	{
		
		card1 = play1.pop();
		
		card2 = play2.pop();
		
		/**
		 * calls the compare method
		 */
		compare(dis1, dis2, card1, card2, oneWin, twoWin, equals);
		
	}//play
	
	/**
	 * compares the two cards and determines the winner
	 * @param discardPile1 the incoming name for the player 1's discard stack
	 * @param discardPile2 the incoming name for the player 2's discard stack
	 * @param pcard1 the incoming name for the card player 1 plays
	 * @param pcard2 the incoming name for the card player 2 plays
	 * @param p1Win the incoming name for player 1 winning
	 * @param p2Win the incoming name for player 2 winning
	 * @param equal the incoming name for a tie
	 */
	public static void compare(StackVissicchio discardPile1, StackVissicchio discardPile2,
			PokemonCardVissicchio pcard1, PokemonCardVissicchio pcard2, boolean p1Win, 
			boolean p2Win, boolean equal)
	{
		p1Win = false;
		p2Win = false;
		equal = false;
		
		int pow1 = pcard1.getPower();
		int pow2 = pcard2.getPower();
		
		if(pow1 > pow2)
		{
			
			p1Win = true;
		}//if p1 wins
		else if(pow2 > pow1)
		{
			
			p2Win = true;
		}//else if p2 wins
		else
		{
			
			equal = true;
		}//else tie
		
		/**
		 * calls the winPlay method
		 */
		winPlay(discardPile1, discardPile2, pcard1, pcard2, p1Win, p2Win, equal);
		
	}//compare
	
	/**
	 * gives the cards to the winner 
	 * @param discard1 the incoming name for the player 1's discard stack
	 * @param discard2 the incoming name for the player 2's discard stack
	 * @param pcard1 the incoming name for the card player 1 plays
	 * @param pcard2 the incoming name for the card player 2 plays
	 * @param oneWins the incoming name for player 1 winning
	 * @param twoWins the incoming name for player 2 winning
	 * @param ties the incoming name for a tie
	 */
	public static void winPlay(StackVissicchio discard1, StackVissicchio discard2,
			PokemonCardVissicchio pcard1, PokemonCardVissicchio pcard2,
			boolean oneWins, boolean twoWins, boolean ties)
	{
		
		if(oneWins == true)
		{

			discard1.push(pcard1);
			discard1.push(pcard2);
		}//if
		else if(twoWins == true)
		{
			
			discard2.push(pcard2);
			discard2.push(pcard1);
		}//else if
		else
		{
			
			discard1.push(pcard1);
			discard2.push(pcard2);
		}//else
	}//winPlay
	
	/**
	 * copy a player's discard stack into her play stack
	 * @param playPile the incoming name for the play stack
	 * @param discardPile the incoming name for discard stack
	 */
	public static void copy(StackVissicchio playPile, StackVissicchio discardPile)
	{
		
		StackVissicchio temp = new StackVissicchio();
		while(!discardPile.isEmpty())
		{
			
			temp.push(discardPile.pop());
		}//while
		while(!temp.isEmpty())
		{
			
			playPile.push(temp.pop());
		}//while
	}//copy
	
	/**
	 * counts the number of cards in a single stack
	 * @param deck the incoming name of the StackVissicchio of PokemonCardVissicchio objects
	 * @return the number of cards in a stack
	 */
	public static int countCards(StackVissicchio deck)
	{
		
		int count = 0;
		
		StackVissicchio temp = new StackVissicchio();
		
		while(!deck.isEmpty())
		{
			
			count++;
			temp.push(deck.pop());
		}//while
		while(!temp.isEmpty())
		{

			deck.push(temp.pop());
		}//while
		return count;
		
	}//countCards
	
	/**
	 * prints the results of the game
	 * @param sCount the starting deck count
	 * @param tCount the turn number count
	 * @param p1Count the card count of player 1
	 * @param p2Count the card count of player 2
	 */
	public static void printResults(int sCount, int tCount, int p1Count, int p2Count)
	{
		System.out.println("Battle Card Game Summary");
		System.out.println("========================");
		System.out.println("The game started with " + sCount + " cards.");
		System.out.println("There were " + tCount + " plays in the game.");
		if((p1Count > p2Count) || (p2Count > p1Count))
		{
			System.out.println("The game ended with a clear winner.");
		}
		else
		{
			System.out.println("The game took too long.");
		}//else
		System.out.println("Player 1 ended up with " + p1Count + " cards.");
		System.out.println("Player 2 ended up with " + p2Count + " cards.");
		if(p1Count > p2Count)
		{
			System.out.println("The winner was Player 1");
		}//if
		else if(p2Count > p1Count)
		{
			System.out.println("The winner was Player 2");
		}//else if
		else
		{
			
			System.out.println("It was a tie!");
		}//else
	}
}//BattleDemoVissicchio
