package snake;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake
{
    private int length;
    private ArrayList<Position> body;
    private Direction currentDirection;
    private Position lastPosition;
    
    public Snake(int startX, int startY)
    {
	body = new ArrayList<Position>();
	body.add(new Position(startX, startY));
	currentDirection = Direction.RIGHT;
    }
    
    public void changeDirection(boolean[] keys)
    {
	if ((keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) && currentDirection != Direction.LEFT)
	{
	    currentDirection = Direction.RIGHT;
	}
	if ((keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) && currentDirection != Direction.DOWN)
	{
	    currentDirection = Direction.UP;
	}
	if ((keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) && currentDirection != Direction.RIGHT)
	{
	    currentDirection = Direction.LEFT;
	}
	if ((keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) && currentDirection != Direction.UP)
	{
	    currentDirection = Direction.DOWN;
	}
    }
    
    public Position move()
    {
	lastPosition = new Position(body.get(body.size() - 1));
	for (int i = body.size() - 1; i > 0; i--)
	{
	    body.set(i, new Position(body.get(i - 1)));
	}
	
	if (currentDirection == Direction.RIGHT)
	{
	    body.get(0).x++;
	}
	else if (currentDirection == Direction.UP) 
	{
	    body.get(0).y--;
	}
	else if (currentDirection == Direction.LEFT)
	{
	    body.get(0).x--;
	}
	else if (currentDirection == Direction.DOWN)
	{
	    body.get(0).y++;
	}
		
	return body.get(0);
    }
    
    public boolean checkForSnake(int x, int y)
    {
	//returns true if there is snake at x, y, false if there is not
	for (int i = 0; i < body.size(); i++)
	{
	   if (body.get(i).x == x && body.get(i).y == y)
	   {
	       return true;
	   }
	}
	return false;
    }
    
    public void add()
    {
	body.add(lastPosition);
    }
    
    public Position getHead()
    {
	return body.get(0);
    }
    
    public ArrayList<Position> getBody()
    {
	return body;
    }
}
