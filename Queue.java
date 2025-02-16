
/**
 * This method demonstrates a queue in the 
 * maze using 1D array
 *
 */
public class Queue {
	
	private Position [] positionList;
	private int totalPositions;
	
	//Constructor to create the queue of size
	//equal to the size of the maze
	public Queue(int size)
	{
		positionList = new Position[size];
		totalPositions = 0;
	}
	
	//This method adds an object to the queue
	public void enqueue(Position position)
	{
		positionList[totalPositions] = position;
		totalPositions+= 1;
	}
	
	//This method removes an object from the queue
	public Position dequeue()
	{
		Position elemToReturn = positionList[0];
		
		for(int cnt=0; cnt<totalPositions-1;cnt++)
		{
			positionList[cnt] = positionList[cnt+1];
		}
		
		totalPositions -=1;
		
		return elemToReturn;
		
	}
	
	//This method returns the object at the front in 
	//the queue without removing the same
	public Position front()
	{
		return positionList[0];
	}
	
	//This method checks if the queue is empty or not
	//Returns True if empty else False
	public boolean isEmpty()
	{
		return (totalPositions == 0);
	}
	
	//This method returns the string containing location
	//coordinates of all elements present in the queue
	//from front to back
	public String toString()
	{
		String res = "";
		
		for(int cnt=0; cnt<totalPositions;cnt++)
		{
			res += positionList[cnt].toLocationString() + " ";
		}
		
		return res;
	}
}
