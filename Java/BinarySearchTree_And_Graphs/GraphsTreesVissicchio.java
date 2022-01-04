import java.util.*;
import java.io.*;

public class GraphsTreesVissicchio
{
    
	public static void main(String[] args) 
	{
		//variables
		int i = 0;
		float avgBST = 0;
		
		//initializing array of magicitems
		String[] items = new String[666];
		String[] randoItems = new String[42];
		
		BinarySearchTreeVissicchio bst = new BinarySearchTreeVissicchio();
		GraphVissicchio graph1 = new GraphVissicchio();
		
		
		//Creates the reference to the file magicitems.txt
		File myFile = new File("magicitems.txt");
		File myFile2 = new File("magicitems-find-in-bst.txt");
		File myGraph = new File("graphs1.txt");
		
		//Try and catch to open and use the file and if not it will locate the errors caused
		try
		{
			//Creates a scanner object that reads the file
			Scanner input = new Scanner(myFile);
			
			//Populates the array of items
			while(input.hasNext())
			{
				
				items[i] = input.nextLine();
				i++;
			}//while
			
			
			//closes file
			input.close();

		}//try
		
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

		//Try and catch to open and use the file and if not it will locate the errors caused
		try
		{
			//Creates a scanner object that reads the file
			Scanner input2 = new Scanner(myFile2);
			
			i = 0;

			//Populates the array of items
			while(input2.hasNext())
			{
				
				randoItems[i] = input2.nextLine();
				i++;
			}//while
			
			
			//closes file
			input2.close();

		}//try
		
		catch(FileNotFoundException ex)
		{
		  System.out.println("Failed to find file: " + myFile2.getAbsolutePath());
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

		/*
		read out second file to see if it is working correctly
		for (i = 0; i < randoItems.length; i++)
		{
			System.out.println(randoItems[i]);
		}
	    */

		
		//insert nodes into the BST
		System.out.println("Insertion for the Binary Search Tree: ");
		System.out.println("-------------------------------------------------------------");
		for (i = 0; i < items.length; i++)
		{
			NodeTreeVissicchio newNode = new NodeTreeVissicchio(items[i]);
			bst.BSTInsert(bst, newNode);
		}
		System.out.println("");
		System.out.println("In Order Traversal for Binary Search Tree: ");
		System.out.println("-------------------------------------------------------------");
		bst.inOrderTraversal(bst.root);
		System.out.println("");
		System.out.println("Search for Binary Search Tree: ");
		System.out.println("-------------------------------------------------------------");
		for (i = 0; i < randoItems.length; i++)
		{
			//bst.BSTSearch(bst.root, randoItems[i]);
			//System.out.println("");
			avgBST = avgBST + bst.searchComp(bst.root, randoItems[i]);
		}
		//avg should be around 5 since logb2(666) = 9-10ish
		avgBST = avgBST/randoItems.length;
		System.out.println(avgBST + " is the average number of comparisons for BST Search");
		System.out.println("");
		

		//graph practice stuff
		/*
		VertexVissicchio newVertex1 = new VertexVissicchio(1);
		VertexVissicchio newVertex2 = new VertexVissicchio(2);
		VertexVissicchio newVertex3 = new VertexVissicchio(3);
		VertexVissicchio newVertex4 = new VertexVissicchio(4);
		VertexVissicchio newVertex5 = new VertexVissicchio(5);
		VertexVissicchio newVertex6 = new VertexVissicchio(6);
		VertexVissicchio newVertex7 = new VertexVissicchio(7);

		graph1.addEdgeLinkedObjects(newVertex1, newVertex2);
		graph1.addEdgeLinkedObjects(newVertex1, newVertex5);
		graph1.addEdgeLinkedObjects(newVertex1, newVertex6);
		graph1.addEdgeLinkedObjects(newVertex2, newVertex3);
		graph1.addEdgeLinkedObjects(newVertex2, newVertex5);
		graph1.addEdgeLinkedObjects(newVertex2, newVertex6);
		graph1.addEdgeLinkedObjects(newVertex3, newVertex4);
		graph1.addEdgeLinkedObjects(newVertex4, newVertex5);
		graph1.addEdgeLinkedObjects(newVertex5, newVertex6);
		graph1.addEdgeLinkedObjects(newVertex5, newVertex7);
		graph1.addEdgeLinkedObjects(newVertex6, newVertex7);

		System.out.println("Depth First Traversal: ");
        System.out.println("--------------------------------------------");
		graph1.depthFirstTraversal(newVertex1);
		System.out.println("");
		graph1.unProcess(newVertex1);
		graph1.breadthFirstTraversal(newVertex1);
		System.out.println("");

		
		GraphAdjListVissicchio adjList = new GraphAdjListVissicchio(8);
		adjList.initializeHash();

		adjList.addEdgeAdjList(1, 2);
		adjList.addEdgeAdjList(1, 5);
		adjList.addEdgeAdjList(1, 6);
		adjList.addEdgeAdjList(2, 3);
		adjList.addEdgeAdjList(2, 5);
		adjList.addEdgeAdjList(2, 6);
		adjList.addEdgeAdjList(3, 4);
		adjList.addEdgeAdjList(4, 5);
		adjList.addEdgeAdjList(5, 6);
		adjList.addEdgeAdjList(5, 7);
		adjList.addEdgeAdjList(6, 7);

		System.out.println("Adjacency List: ");
        System.out.println("--------------------------------------------");
		adjList.printAdjList();
		adjList.emptyHash();
		System.out.println("");

		
		GraphMatrixVissicchio gMatrix = new GraphMatrixVissicchio(8);

		gMatrix.addEdgeMatrix(1, 2);
		gMatrix.addEdgeMatrix(1, 5);
		gMatrix.addEdgeMatrix(1, 6);
		gMatrix.addEdgeMatrix(2, 3);
		gMatrix.addEdgeMatrix(2, 5);
		gMatrix.addEdgeMatrix(2, 6);
		gMatrix.addEdgeMatrix(3, 4);
		gMatrix.addEdgeMatrix(4, 5);
		gMatrix.addEdgeMatrix(5, 6);
		gMatrix.addEdgeMatrix(5, 7);
		gMatrix.addEdgeMatrix(6, 7);

		System.out.println("Matrix: ");
        System.out.println("--------------------------------------------");
		gMatrix.printMatrix();
		*/




		
		//Try and catch to open and use the file and if not it will locate the errors caused
		try
		{
			//Creates a scanner object that reads the file
			Scanner input3 = new Scanner(myGraph);
			
			i = 0;
			int vertexCount = 1;
			GraphMatrixVissicchio gMatrix = new GraphMatrixVissicchio(0);
			GraphAdjListVissicchio gAdjList = new GraphAdjListVissicchio(0);
			GraphVissicchio gLinkedObjects = new GraphVissicchio();
			ArrayList<VertexVissicchio> vertexList = new ArrayList<VertexVissicchio>();
			vertexList.add(null);

			int v = 0;

			//x and y represent neighboring verticies
			int x = 0;
			int y = 0;

			//input3.nextLine();
			//Populates the array of items
			while(input3.hasNext())
			{
				String line = input3.nextLine();
				if (line.contains("new graph"))
				{
						gLinkedObjects = new GraphVissicchio();
				}
				if (!line.contains("new graph") || !line.contains("--") || input3.hasNext())
				{
					if (line.contains("add vertex"))
					{
						String parseString = line;
						//System.out.println(parseString);
						String newParsed = parseString.replaceAll("[^\\d]", "");
						v = Integer.parseInt(newParsed);
						//if a vertex 0 is added it will destroy the index handiling so this if will attempt to prevent that
						if (line.contains("add vertex 0"))
						{
							vertexCount--;
							vertexList.remove(0);
						}
						VertexVissicchio newVertex = new VertexVissicchio(v);
						vertexList.add(newVertex);
						//vertex count will be needed to set the size of the 2D array and Hash Table
						vertexCount++;
					}
					if (line.contains("add edge"))
					{
						//create a new matrix if it was not created yet
						if (gMatrix.numVerticies == 0)
						{
							gMatrix = new GraphMatrixVissicchio(vertexCount);
						}
						//create a new adjacency list if it was not created yet
						if (gAdjList.numVerticies == 0)
						{
							gAdjList = new GraphAdjListVissicchio(vertexCount);
							gAdjList.initializeHash();
						}
						String parseString = line;
						//System.out.println(parseString);
						String newParsed = parseString.replaceAll("[^\\d-]", "");
						//System.out.println(newParsed);
						String edgeNums[] = newParsed.split("-");
						x = Integer.parseInt(edgeNums[0]);
						y = Integer.parseInt(edgeNums[1]);
						//add the edges for the three graphs
						gMatrix.addEdgeMatrix(x, y);
						gAdjList.addEdgeAdjList(x, y);
						gLinkedObjects.addEdgeLinkedObjects(vertexList.get(x), vertexList.get(y));
					}
				}//if
				if (line.contains("new graph") || !input3.hasNext())
				{
					if (vertexCount > 1)
					{
					//once we reach a "--" or the end, we can print out everything then reset it for the next loop
					System.out.println("Adjaceny List: ");
					System.out.println("--------------------------------------------");
					gAdjList.printAdjList();
					gAdjList.emptyHash();
					System.out.println("");

					System.out.println("Matrix: ");
        			System.out.println("--------------------------------------------");
					gMatrix.printMatrix();
					System.out.println("");

					System.out.println("Depth First Traversal: ");
        			System.out.println("--------------------------------------------");
					graph1.depthFirstTraversal(vertexList.get(1));
					System.out.println("");

					graph1.unProcess(vertexList.get(1));

					graph1.breadthFirstTraversal(vertexList.get(1));
					System.out.println("");

					vertexCount = 1;
					gMatrix = new GraphMatrixVissicchio(0);
					gAdjList = new GraphAdjListVissicchio(0);
					vertexList = new ArrayList<VertexVissicchio>();
					vertexList.add(null);
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
		/*
		catch(NullPointerException ex)
		{
			System.out.println("Null pointer exception.");
			System.out.println(ex.getMessage());
		}//catch*/
		catch(Exception ex)
		{
			System.out.println("Something went wrong");
			ex.printStackTrace();
		}//catch
		

	}//main
	
}//GraphsTreesVissicchio