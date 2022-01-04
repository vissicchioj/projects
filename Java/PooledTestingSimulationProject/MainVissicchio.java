import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.reflect.Array;


public class MainVissicchio
{
	public static void main(String[] args) 
	{
        KnuthVissicchio knuth = new KnuthVissicchio();

        //the total population size
        int POPULATION_SIZE = 1000;

        //the size of each pool
        int POOL_SIZE = 8;

        //the group size represents the total amount of pools based on the population size
        int GROUP_SIZE = POPULATION_SIZE/POOL_SIZE;

        //an array of integers 0 or 1 that holds the whole population
        //0 is negative, 1 is positive
        int popArray[] = new int[POPULATION_SIZE];

        //setting debug mode to true allows us to watch what case each pool is
        boolean debugMode = true;

        //counting the total amount of tests in the simulation
        int testCount = 0;

        //counting the total amount of each of the cases
        int case1Count = 0;
        int case2Count = 0;
        int case3Count = 0;

        //checks if we find any infected individuals within the pool
        boolean isFound = false;
        boolean isFound2 = false;
        boolean isFound3 = false;

        int i = 0;

        //the total amount of infections within the population: currently 2% of the population
        double infections = popArray.length * 0.02;

        //the total amount of infections within the population rounded since individuals cannot be half infected
        int infectionRate = (int)(Math.round(infections));

        if (debugMode == true)
        {
            //prints out the total amount of infected students
            System.out.println("Total infected students: " + infectionRate);
        }

        //fill up the first 2% of the population array with infected students
        for (i = 0; i < infectionRate; i++)
        {
            popArray[i] = 1;
        }//for

        //fill up the rest of the population array with non-infected students
        for (i = infectionRate + 1; i < popArray.length; i++)
        {
            popArray[i] = 0;
        }

        //shuffles the infections so that the first 2% are not guaranteed to be infected which increases the realism
        knuth.shuffle(popArray);

        //2D array that will represent 125 pools of 8
        int[][] groupsOfPools = new int [GROUP_SIZE][POOL_SIZE];
        int j = 0;
        int k = 0;

        //fill up the 2D Array using the (now randomized) Population array
        for (i = 0; i < popArray.length; i++)
        {
            groupsOfPools[j][k] = popArray[i];
            k++;
            if ((i + 1) % 8 == 0)
            {
                j++;
                k = 0;
            }
        }

        
        //Prints the table displaying 125 pools of 8
        if (debugMode == true)
        {
        for (i = 0; i < 125; i++)
        {
            System.out.print(i + " ");
            for (j = 0; j < 8; j++)
            {
                System.out.print(groupsOfPools[i][j] + "  ");
            }
            System.out.println("");
        }
        }//debugMode

        System.out.println("");
        System.out.println("Begin Testing: ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Total Population: " + POPULATION_SIZE);
        System.out.println(GROUP_SIZE + " pools of " + POOL_SIZE);

        System.out.println("Testing...");

        //go through every pool until we reach the total amount of pools for the population
        for (j = 0; j < GROUP_SIZE; j++)
        {
            for (k = 0; k < POOL_SIZE; k++)
            {
                //if a positive case was found in the pool
                if (groupsOfPools[j][k] == 1)
                {
                    isFound = true;
                }
            }
            //one pool uses one test
            testCount++;

            //there was a postive case within the pool, now we must split into 2 pools of 4
            if (isFound == true)
            {
                
                for (k = 0; k < POOL_SIZE/2; k++)
                {
                    //if a positive case was found in the first sub-pool
                    if (groupsOfPools[j][k] == 1)
                    {
                        isFound2 = true;
                    }
                    
                }
                //the half sized pool uses one test
                testCount++;

                for (k = (POOL_SIZE/2); k < POOL_SIZE; k++)
                {
                    //if a positive case was found in the second sub-pool
                    if (groupsOfPools[j][k] == 1)
                    {
                        isFound3 = true;
                        
                    }
                }
                //the half sized pool uses one test
                testCount++;

                //infected samples in only one of the subpools (case 2)
                if ((isFound2 == true && isFound3 == false) || (isFound2 == false && isFound3 == true))
                {
                    if (debugMode == true)
                    {
                    System.out.println(j +": Case 2");
                    }
                    //this is a case 2 so increment the count by one
                    case2Count++;
                    
                    //go through the first sub-pool since that was where the infections were found
                    if (isFound2 == true)
                    {
                        for (k = 0; k < POOL_SIZE/2; k++)
                        {
                            //if a positive case was found in the pool
                            if (groupsOfPools[j][k] == 1)
                            {
                                if (debugMode == true)
                                {
                                System.out.println("The infected individual was number " + k + " in pool number " + j);
                                }
                            }

                            //we are now indivdually testing each person within the smaller pool so it is inside the for loop
                            testCount++;
                        }
                    }
                    //go through the second sub-pool since that was where the infections were found
                    else
                    {
                        for (k = (POOL_SIZE/2); k < POOL_SIZE; k++)
                        {
                            //if a positive case was found in the pool
                            if (groupsOfPools[j][k] == 1)
                            {
                                //isFound3 = true;
                                if (debugMode == true)
                                {
                                System.out.println("The infected individual was number " + k + " in pool number " + j);
                                }
                            }

                            //we are now indivdually testing each person within the smaller pool so it is inside the for loop
                            testCount++;
                        }
                    }
                }//if

                //infected samples found in both sub-pools
                if (isFound2 == true && isFound3 == true)
                {
                    if (debugMode == true)
                    {
                    System.out.println(j +": Case 3");
                    }
                    //this is a case 3 so increment the count by one
                    case3Count++;

                    //we must test everyone from the original pool again since we know that there are multiple cases
                    //that exist with at least 1 in each sub-pool
                    for (k = 0; k < POOL_SIZE; k++)
                    {
                        //if a positive case was found in the pool
                        if (groupsOfPools[j][k] == 1)
                        {
                            //isFound = true;
                            if (debugMode == true)
                            {
                            System.out.println("The infected individual was number " + k + " in pool number " + j);
                            }
                        }
                        //we are now indivdually testing each person within both smaller pools so it is inside the for loop
                        testCount++;
                    }
                }
            }
            //the whole pool was not infected :)
            else
            {
                //Case 1
                if (debugMode == true)
                {
                System.out.println(j +": Case 1");
                }
                //this is a case 1 so increment the count by one
                case1Count++;
            }

            //reset boolean values for next pool
            isFound = false;
            isFound2 = false;
            isFound3 = false;
        }

        /*
        System.out.println("");
        System.out.println("Results: ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Total Population: " + POPULATION_SIZE);
        System.out.println(GROUP_SIZE + " pools of " + POOL_SIZE);

        System.out.println("Testing...");
        */
        //System.out.println("");
        System.out.println("Complete!");
        System.out.println("");
        System.out.println("Results: ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Case 1: " + case1Count + " which requires " + case1Count*1 + " tests.");
        System.out.println("Case 2: " + case2Count + " which requires " + case2Count*7 + " tests.");
        System.out.println("Case 3: " + case3Count + " which requires " + case3Count*11 + " tests.");
        System.out.println("Total Tests Used: " + testCount);


    }//main
}//SemesterProjectVissicchio