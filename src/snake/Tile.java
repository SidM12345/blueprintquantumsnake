package snake;

public class Tile
{
    //eventually add the teleporters here as a field
    private int x, y;
    public Tile(int x, int y)
    {
	    this.x = x;
	    this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}
