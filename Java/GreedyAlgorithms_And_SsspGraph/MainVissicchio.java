import java.util.*;
import java.io.*;

public class MainVissicchio
{
	public static void main(String[] args) 
	{
        QuickVissicchio qSort = new QuickVissicchio();
        //GraphVissicchio graph2 = new GraphVissicchio();

        File myGraph = new File("graphs2.txt");
		File mySpices = new File("spice.txt");
		int spiceCount = 0;

		try
		{
			//Creates a scanner object that reads the file
            int i = 0;
			Scanner input = new Scanner(mySpices);
			
			while (input.hasNext())
			{
				String line = input.nextLine();
				if (line.contains("spice name"))
				{
					//this first round just allows to know how many spices there are so we 
					//can create an array
					spiceCount++;
				}
			}
		}
		catch (Exception e) {
			
		}
		//Try and catch to open and use the file and if not it will locate the errors caused
		try
		{
			//variables we will need to use for the spice heist
            int i = 0;
			Scanner input = new Scanner(mySpices);
			String name = "";
			double total_price = 0;
			int qty = 0;
            int capacity = 0;
            int scoops = 0;
            double knapsackPrice = 0;
			SpiceVissicchio[] spiceList = new SpiceVissicchio[spiceCount];
            SpiceVissicchio[] tempSpiceList = new SpiceVissicchio[spiceCount];
			
			System.out.println("The Spice Heist: ");
			System.out.println("----------------------------------------------");
			while (input.hasNext())
			{
				String line = input.nextLine();
				if (line.contains("spice name"))
				{
					String parseString = line;
					//System.out.println(parseString);

					//first we replace all of the spaces
					String newParsed = parseString.replaceAll("\\s", "");

					//then we split on semicolon
					String parsing[] = newParsed.split(";");
					/*
					System.out.println(parsing[0]);
					System.out.println(parsing[1]);
					System.out.println(parsing[2]);
					*/

					//then for each value we split on equals
					String nameParsing[] = parsing[0].split("=");
					name = nameParsing[1];
					String priceParsing[] = parsing[1].split("=");
					total_price = Double.parseDouble(priceParsing[1]);
					String qtyParsing[] = parsing[2].split("=");
					qty = Integer.parseInt(qtyParsing[1]);

					//create a new spice
					SpiceVissicchio newSpice = new SpiceVissicchio(name, total_price, qty);

					//tempSpiceList allows us to reset our spices after each heist
					spiceList[i] = newSpice;
                    tempSpiceList[i] = newSpice;
                    i++;

					/*
					System.out.println("");
					System.out.println(name);
					System.out.println(total_price);
					System.out.println(qty);
					System.out.println("");
					*/
				}
                if (line.contains("knapsack capacity"))
                {
                    int j = 0;
                    qSort.quickSort(spiceList, 0, spiceCount - 1);

                    String parseString2 = line;
					//System.out.println(parseString);

					//first remove all spaces and semicolons
					String newParsed2 = parseString2.replaceAll("\\s", "");
                    newParsed2 = newParsed2.replaceAll(";", "");

					//then we split on equals to find the capacity
					String parsing2[] = newParsed2.split("=");
                    capacity = Integer.parseInt(parsing2[1]);
                    int originalCapacity = capacity;

					//This is to make sure we can print correctly without actually knowing how many spices there are
					System.out.print("After ");
					for (j = 0; j < spiceList.length; j++)
					{
                    	while ((scoops < spiceList[j].getQuantity()) && (capacity > 0))
                    	{
                        	scoops++;
                        	knapsackPrice = knapsackPrice + spiceList[j].getUnitPrice();
                        	//spiceList[j].setQuantity(spiceList[j].getQuantity() - 1);
                        	capacity--;
                    	}
						if (scoops != 0)
						{
							if (scoops == 1)
							{
								System.out.print(scoops + " scoop of " + spiceList[j].getColor() + ", ");
							}
							else
							{
								System.out.print(scoops + " scoops of " + spiceList[j].getColor() + ", ");
							}
						}
						scoops = 0;
					}
					System.out.print("the knapsack of capacity, " + originalCapacity + ", is worth " 
						+ knapsackPrice + " quatloos");

                    System.out.print(".");
                    System.out.println("");
                    System.out.println("");
                    //reset values
                    scoops = 0;
                    knapsackPrice = 0;
                    for (int k = 0; k < tempSpiceList.length; k++)
                    {
                        spiceList[k] = tempSpiceList[k];
                    }

                    //if 
                }

			}//while
			
			
			//closes file
			input.close();

		}//try
		
		catch(FileNotFoundException ex)
		{
		  System.out.println("Failed to find file: " + mySpices.getAbsolutePath());
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


		
        //Try and catch to open and use the file and if not it will locate the errors caused
		try
		{
			//Creates a scanner object that reads the file
			Scanner input3 = new Scanner(myGraph);
			
			int i = 0;
			int vertexCount = 1;
			int edgeCount = 0;
			GraphVissicchio graph = new GraphVissicchio(0);
			int newGraphChecker = 0;
			ArrayList<Integer> vertexList = new ArrayList<Integer>();
			vertexList.add(0);
			ArrayList<EdgeVissicchio> edgeList = new ArrayList<EdgeVissicchio>();
			int weight = 0;

			int v = 0;

			//x represents the source vertex and y represents destination vertex
			int x = 0;
			int y = 0;

			while(input3.hasNext())
			{
				String line = input3.nextLine();
				if (line.contains("new graph"))
				{
					//graph = new GraphVissicchio(0);
				}
				if (!line.contains("new graph") || !line.contains("--") || input3.hasNext())
				{
					if (line.contains("add vertex"))
					{
						//count up the amount of verticies to be used to find the total number of verticies in this graph
						vertexCount++;
					}
					if (line.contains("add edge"))
					{
						//this is so that we do not overwrite the graph every time we see "add edge"
                        if (newGraphChecker == 0)
						{
							graph = new GraphVissicchio(vertexCount);
							newGraphChecker = 1;
						}
						String parseString = line;
						//System.out.println(parseString);
						String newParsed = parseString.replaceAll("add edge ", "");
						//System.out.println(newParsed);

						//first we split where there are spaced dashes aka our two verticies
						String edgeNums[] = newParsed.split(" - ");
						x = Integer.parseInt(edgeNums[0]);

						//next we split the second splitted value on spaces to separate the destination vertex and the cost
						String edgeWeightSplit[] = edgeNums[1].split("\\s+");
						y = Integer.parseInt(edgeWeightSplit[0]);
						weight = Integer.parseInt(edgeWeightSplit[1]);

						//add the edge to our graph
						graph.addEdge(graph, x, y, weight);

						/*
						//For testing the parsing
						System.out.println(x);
						System.out.println(y);
						System.out.println(weight);
						*/
					}
				}//if
				if (line.contains("new graph") || !input3.hasNext())
				{
					if (vertexCount > 1)
					{
					//call BellmanFord since the graph has all of the info it needs
					graph.bellmanFord(graph, 1);

					//Now that this graph is complete we need to reset the values to use for the next graph
					vertexCount = 1;
					vertexList = new ArrayList<Integer>();
					vertexList.add(0);
					weight = 0;
					newGraphChecker = 0;
					}
				}
				
			}//while
			
			
			//closes file
			input3.close();

		}//try
		
		catch(FileNotFoundException ex)
		{
		  System.out.println("Failed to find file: " + myGraph.getAbsolutePath());
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
    }//main
}//DynamicGreedyVissicchio