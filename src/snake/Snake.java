package snake;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake
{
    private ArrayList<Position> body;
    private Direction currentDirection;
    private Position lastPosition;
    
    public Snake(int startX, int startY)
    {
		body = new ArrayList<Position>();
		body.add(new Position(startX, startY));
		currentDirection = Direction.RIGHT;
    }
    
    public boolean changeDirection(boolean[] keys)
    {
		//returns true if snake changed direction, false if not
		Direction previousDirection = currentDirection;
		if ((keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) && currentDirection != Direction.LEFT)
		{
			currentDirection = Direction.RIGHT;
		}
		else if ((keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) && currentDirection != Direction.DOWN)
		{
			currentDirection = Direction.UP;
		}
		else if ((keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) && currentDirection != Direction.RIGHT)
		{
			currentDirection = Direction.LEFT;
		}
		else if ((keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) && currentDirection != Direction.UP)
		{
			currentDirection = Direction.DOWN;
		}	
		return previousDirection != currentDirection;
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

	public void eat(int charge)
	{
		if (charge == 1)
		{
			body.add(lastPosition);
		}	
		else if (charge == -1 && body.size() > 1)
		{
			lastPosition = body.get(body.size() - 1);
			body.remove(lastPosition);
		}
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
