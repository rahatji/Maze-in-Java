
/**
 * Main driver class of the program
 *
 */
public class Main {
	
	public static void main(String [] args)
	{
		//Create a maze
		Maze maze = new Maze();
		//Perform search using stack
		maze.searchByStack();
		//Reset the maze
		maze.resetMaze();
		//Perform search using queue
		maze.searchByQueue();
		System.out.println("Process terminated normally.");
	}

}
