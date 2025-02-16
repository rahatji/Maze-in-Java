
/**
 * This class demonstrates the declaration
 * of a stack in the maze using linked list
 *
 */
public class Stack {
	
	//The top link in the stack
	private Position lastPosition;
	
	//Constructor for class
	public Stack()
	{
		lastPosition = null;
	}
	
	//This method pushes an object on the top
	//of the stack
	public void push(Position newPosition)
	{
		lastPosition = newPosition;
	}
	
	//This method pops/removes the object
	//from the top of the stack
	public Position pop()
	{
		Position position = lastPosition;
		lastPosition = lastPosition.getPrevious();
		
		return position;
	}
	
	//This method returns the object currently
	//at the top of the stack
	public Position top()
	{
		return lastPosition;
	}
	
	//This method returns if the stack is empty
	//or not. Returns True if empty else False
	public boolean isEmpty()
	{
		return (lastPosition == null);
	}
	
	//This method returns location coordinates of all
	//objects in the stack at one point in time
	public String toString()
	{
		String res = "";
		
		
		Position tmp = lastPosition;
		
		while(tmp.getPrevious() != null)
		{
			res += tmp.toLocationString() + " ";
			tmp = tmp.getPrevious();
		}
		
		res+= tmp.toLocationString();
		
		return res;
		
	}

}
