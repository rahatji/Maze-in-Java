
/**
 * This class defines a position in the 
 * maze. A position has a particular location
 * in terms of row and column along side link 
 * to the position from which one position is visited
 *
 */
public class Position {
	
	private int rowNum, colNum;
	private SquareType typeOfSquare;
	private boolean visited;
	private Position prevPosition;
	
	//Constructor for the class
	public Position(int Row, int Col, SquareType Type)
	{
		rowNum = Row;
		colNum = Col;
		typeOfSquare = Type;
		prevPosition = null;
		visited = false;
	}
	
	//Setter for previous position
	public void setPrevious(Position position)
	{
		prevPosition = position;
	}
	
	//Getter for previous position
	public Position getPrevious()
	{
		return prevPosition;
	}
	
	//This method returns the location 
	//coordinates of a position as a string
	public String toLocationString()
	{
		return "(" + rowNum + " , " + colNum + ")";
	}
	
	//This method returns the character
	//symbol representing the position in the maze
	public String toTypeString()
	{
		String symbol = "";
		
		if (typeOfSquare == SquareType.FINISH)
		{
			symbol = "F";
		}
		else if(typeOfSquare == SquareType.PATH)
		{
			symbol = ".";
		}
		else if(typeOfSquare == SquareType.START)
		{
			symbol = "S";
		}
		else if(typeOfSquare == SquareType.WALL)
		{
			symbol = "#";
		}
		
		return symbol;
	}
	
	//Getter for visited boolean
	public boolean getVisited()
	{
		return visited;
	}
	
	//Setter for visited state
	public void setVisited(boolean status)
	{
		visited = status;
	}
	
	//Getter for type of position
	public SquareType getType()
	{
		return typeOfSquare;
	}
	
	//Getter for row
	public int getRow()
	{
		return rowNum;
	}
	
	//Getter for column
	public int getCol()
	{
		return colNum;
	}

}
