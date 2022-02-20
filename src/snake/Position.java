package snake;

public class Position
{
    public int x, y;
    public Position(int x, int y)
    {
	this.x = x;
	this.x = y;
    }
    
    public Position(Position pos)
    {
	x = pos.x;
	y = pos.y;
    }
}
