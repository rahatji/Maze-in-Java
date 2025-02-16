import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class demonstrates a maze and all
 * of its necessary functionalities in the program
 *
 */
public class Maze {
	
	//Maze to store position references
	private Position[][] maze;
	
	//Constructor to get file name from user
	//and populate the maze with required objects
	public Maze()
	{	
		//Get file name from user using getFileName() method
		String filename = getFileName();
		System.out.println("Processing " + filename + "...");
		
		try {
			//Create a new file object
		      File objFile = new File(filename);
		      
		      //Create scanner object
		      Scanner sc = new Scanner(objFile);
		      
		      //read first line to extract size of maze
		      String firstLine = sc.nextLine();
		      int row = Integer.parseInt(firstLine.trim().split(" ")[0]);
		      int col = Integer.parseInt(firstLine.trim().split(" ")[1]);
		      //Create maze
		      maze = new Position[row][col];
		      
		      int currentRow = 0;
		      
		      System.out.println("The initial maze is:");
		      
		      //Start reading the file line by line and populate the maze
		      while (sc.hasNextLine()) {
		        String data = sc.nextLine();
		        
		        System.out.println(data);
		        
		        //Process every line character by character
		        for(int cnt=0; cnt < col; cnt++)
		        {
		        	//Determine the type of position and create required object
		        	Position pos = null;
		        	if(data.charAt(cnt) == '.')
		        	{
		        		pos = new Position(currentRow, cnt, SquareType.PATH);
		        	}
		        	else if(data.charAt(cnt) == '#')
		        	{
		        		pos = new Position(currentRow, cnt, SquareType.WALL);
		        	}
		        	else if(data.charAt(cnt) == 'S')
		        	{
		        		pos = new Position(currentRow, cnt, SquareType.START);
		        	}
		        	else if(data.charAt(cnt) == 'F')
		        	{
		        		pos = new Position(currentRow, cnt, SquareType.FINISH);
		        	}
		        	
		        	//Assign reference to the object at required location in the maze
		        	maze[currentRow][cnt] = pos;
		        	
		        }
		        
		        currentRow += 1;
		        
		      }
		      //Close scanner
		      sc.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	//This method retrieves the input file name from the user
	//and returns the same to the calling method
	private String getFileName()
	{
		String filename = "";
		
		//Prompt for user
		System.out.print("Please enter the input file name (.txt only): ");
		
		//Create scanner instance
		Scanner sc = new Scanner(System.in);
		
		//Read file name
		filename = sc.nextLine();
		
		//Return file name
		return filename.trim();
	}
	
	//This method retrieves the object at start location
	//in the maze
	private Position getStartPosition()
	{
		Position pos = null;
		
		//Find the start position in the maze
		for(int rowCnt=0; rowCnt < maze.length; rowCnt++)
		{
			for(int colCnt=0; colCnt < maze[0].length; colCnt++)
			{
				//If the position is found, get the object reference
				//and exit the loop
				if(maze[rowCnt][colCnt].getType() == SquareType.START)
				{
					pos = maze[rowCnt][colCnt];
					break;
				}
			}
		}
		
		return pos;
	}
	
	//This method retrieves the object at finish position in the
	//maze
	private Position getFinishPosition()
	{
		Position pos = null;
		
		//Find the finish position in the maze
		for(int rowCnt=0; rowCnt < maze.length; rowCnt++)
		{
			for(int colCnt=0; colCnt < maze[0].length; colCnt++)
			{
				//If the finish position is found, get the object
				//reference and exit the loop
				if(maze[rowCnt][colCnt].getType() == SquareType.FINISH)
				{
					pos = maze[rowCnt][colCnt];
					break;
				}
			}
		}
		
		return pos;
	}
	
	//This method performs the path search using the stack
	//Once the search is complete, either the path is found if it exist,
	//or the relevant message is printed
	//Note: In order to process neighbors for a given position, this method
	//processes only top, bottom, left and right neighbors and ignores the top-left,
	//bottom-left, top-right, and bottom-right neighbors.
	public void searchByStack()
	{
		//Initialize a stack
		Stack stack = new Stack();
		Position currentPos = null;
		
		//Retrieve start location
		Position startPos = getStartPosition();
		
		//Start the start position as visited
		//Set prev to null and push the obj to stack
		startPos.setVisited(true);
		startPos.setPrevious(null);
		stack.push(startPos);
		
		//Process the stack until its empty
		while(!stack.isEmpty())
		{
			//Retrieve the object at the top of the stack
			currentPos = stack.pop();
			
			//if the retrieved object is a finish position
			//stop the processing
			if(currentPos.getType() == SquareType.FINISH)
			{
				break;
			}
			
			//Retrieve the top neighbor of the existing position
			if(currentPos.getRow() > 0)
			{
				//Get the neighbor
				Position pos = maze[currentPos.getRow() - 1][currentPos.getCol()];
				
				//if the same is not visited and its a path
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					//set it as visited, and push to stack
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					stack.push(pos);
				}
				
			}
			
			//Retrieve the bottom neighbor of the existing position
			if(currentPos.getRow() < (maze.length -1))
			{
				Position pos = 	maze[currentPos.getRow() + 1][currentPos.getCol()];
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					stack.push(pos);
				}
				
			}
			
			//retrieve the left neighbor of the existing position
			if(currentPos.getCol() > 0)
			{
				Position pos = 	maze[currentPos.getRow()][currentPos.getCol() - 1];
				
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					stack.push(pos);
				}
			}
			
			//retrieve the right neighbor of the existing position
			if(currentPos.getCol() < (maze[0].length - 1))
			{
				Position pos = 	maze[currentPos.getRow()][currentPos.getCol() + 1];
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					stack.push(pos);
				}
				
			}
		}
		
		//Print the final path
		System.out.println("The path found using a stack is:");
		printPath(currentPos);
	}
	
	//This method performs path search using a queue
	public void searchByQueue()
	{
		//Create the queue
		Queue queue = new Queue(maze.length * maze[0].length);
		Position currentPos = null;
		
		//Get the start position
		Position startPos = getStartPosition();
		
		//Add start position to the queue
		startPos.setVisited(true);
		startPos.setPrevious(null);
		queue.enqueue(startPos);
		
		//Keep processing the queue until it is empty
		while(!queue.isEmpty())
		{
			//Get the object from the front of the queue
			currentPos = queue.dequeue();
			
			//If the retrieved position is a finish position,
			//stop all processing
			if(currentPos.getType() == SquareType.FINISH)
			{
				break;
			}
			
			//Get the top neighbor of the existing position and process the same
			if(currentPos.getRow() > 0)
			{
				Position pos = maze[currentPos.getRow() - 1][currentPos.getCol()];
				
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					queue.enqueue(pos);
				}
				
			}
			
			//Get the bottom neighbor of the existing position
			if(currentPos.getRow() < (maze.length -1))
			{
				Position pos = 	maze[currentPos.getRow() + 1][currentPos.getCol()];
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					queue.enqueue(pos);
				}
				
			}
			
			//Get the left neighbor of the existing position
			if(currentPos.getCol() > 0)
			{
				Position pos = 	maze[currentPos.getRow()][currentPos.getCol() - 1];
				
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					queue.enqueue(pos);
				}
			}
			
			//Get the right neighbor of the existing position
			if(currentPos.getCol() < (maze[0].length - 1))
			{
				Position pos = 	maze[currentPos.getRow()][currentPos.getCol() + 1];
				if(!pos.getVisited() && pos.getType() != SquareType.WALL)
				{
					pos.setVisited(true);
					pos.setPrevious(currentPos);
					queue.enqueue(pos);
				}
				
			}
		}
		
		//Print the final maze path
		System.out.println("The path found using a queue is:");
		printPath(currentPos);
	}
	
	//This method prints the final path from start to the end
	//if the same exists, else prints the appropriate message
	private void printPath(Position finishLocation)
	{
		//If the final position (ending position) is not a finish square,
		//the path from start to finish does not exist
		if(finishLocation.getType() != SquareType.FINISH)
		{
			System.out.println("Path from start to finish does not exist");
		}
		else
		{
			//Print the maze with path from start to finish
			printFinalMaze(finishLocation);
			
			String res = "";
			//Generate list of location coordinates from start to finish
			while(finishLocation.getPrevious() != null)
			{
				res= finishLocation.toLocationString() + " " + res;
				finishLocation = finishLocation.getPrevious();
			}
			
			res= finishLocation.toLocationString() + " " + res;
			
			//Print the location coordinates
			System.out.println("Path from start to finish: " + res);
		}
	}
	
	//This method prints the solved maze with path from start to
	//finish marked with *
	private void printFinalMaze(Position finishLocation)
	{
		//Create a string representation of maze
		String finalMaze[][] = new String[maze.length][maze[0].length];
		
		//Populate all locations with wall
		for(int rowCnt=0; rowCnt<maze.length; rowCnt++)
		{
			for(int colCnt=0; colCnt<maze[0].length; colCnt++)
			{
				finalMaze[rowCnt][colCnt] = "#";
			}
		}
		
		//Populate start location
		Position pos = getStartPosition();
		finalMaze[pos.getRow()][pos.getCol()] = "S";
		
		//Populate finish location
		pos = getFinishPosition();
		finalMaze[pos.getRow()][pos.getCol()] = "F";
		
		//Start processing the path
		pos = finishLocation.getPrevious();
		
		while(pos.getPrevious() != null)
		{
			finalMaze[pos.getRow()][pos.getCol()] = "*";
			pos = pos.getPrevious();
		}
		
		//Print the maze on console
		for(int rowCnt=0; rowCnt<maze.length; rowCnt++)
		{
			for(int colCnt=0; colCnt<maze[0].length; colCnt++)
			{
				System.out.print(finalMaze[rowCnt][colCnt]);
			}
			System.out.println("");
		}
		
	}
	
	//This method resets the maze by setting all positions
	//as unvisited
	public void resetMaze()
	{
		for(int rowCnt=0; rowCnt < maze.length; rowCnt++)
		{
			for(int colCnt=0; colCnt < maze[0].length; colCnt++)
			{
				maze[rowCnt][colCnt].setVisited(false);
			}
		}
	}
	

}
