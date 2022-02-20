package snake;

public class Tile
{
    //eventually add the teleporters here as a field
    private int x, y;
    private boolean food;
    public Tile(int x, int y)
    {
	this.x = x;
	this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public boolean hasFood()
    {
        return food;
    }
    public void setFood(boolean food)
    {
        this.food = food;
    }
}
