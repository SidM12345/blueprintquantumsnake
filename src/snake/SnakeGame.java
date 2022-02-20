package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import input.KeyDetector;

public class SnakeGame
{
    private Snake snake;
    private Tile[][] tiles;
    private int xLen, yLen;
    private KeyDetector kd;
    
    public SnakeGame(int x, int y, KeyDetector kd, boolean wrap)
    {
	xLen = x;
	yLen = y;
	this.kd = kd;
	
	tiles = new Tile[y][x];
	for (int i = 0; i < y; i++)
	{
	    for (int j = 0; j < x; j++)
	    {
		tiles[i][j] = new Tile(j, i);
	    }
	}
	
	snake = new Snake(x / 2, y / 2);
	placeFood();
    }
    
    private void placeFood()
    {
	ArrayList<Tile> empty = new ArrayList<Tile>();
	for (int y = 0; y < yLen; y++)
	{
	    for (int x = 0; x < xLen; x++)
	    {
		//checks if the tile is empty, will need to change once more types of consumables are added
		if (!snake.checkForSnake(x, y) && !tiles[y][x].hasFood())
		{
		    empty.add(tiles[y][x]);
		}
	    }
	}
	int randomIndex = (int)(Math.random() * empty.size());
	System.out.println(randomIndex);
	empty.get(randomIndex).setFood(true);
    }
    
    public void controlSnake()
    {
	//allows the user to change the direction of the snake
	kd.keyControl(snake);
    }
    
    public void update()
    {
	//update the position of the snake
	
	Position headPos = snake.move();
	if (headPos.x < 0 || headPos.x > xLen || headPos.y < 0 || headPos.y > yLen)
	{
	    System.out.println("You lost");
	    System.exit(0);
	}
	
	if (tiles[headPos.y][headPos.x].hasFood())
	{
	    tiles[headPos.y][headPos.x].setFood(false);
	    snake.add();
	    placeFood();
	}
    }
    
    public BufferedImage getFrame()
    {
	BufferedImage frame = new BufferedImage(Launcher.WIDTH, Launcher.HEIGHT, BufferedImage.TYPE_INT_RGB);
	Graphics g = frame.getGraphics();
	int tileWidth = Launcher.WIDTH / xLen;
	int tileHeight = Launcher.HEIGHT / yLen;
	for (int y = 0; y < yLen; y++)
	{
	    for (int x = 0; x < xLen; x++)
	    {
		if ((y + x) % 2 == 1) g.setColor(ColorList.very_light_gray);
		else g.setColor(ColorList.light_gray);
		g.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
		
		if (tiles[y][x].hasFood())
		{
		    g.setColor(Color.blue);
		    g.fillOval((int)((tileWidth) * (x)), (int)((tileHeight) * (y)), tileWidth, tileHeight);
		}
	    }
	}
	
	g.setColor(Color.green);
	ArrayList<Position> body = snake.getBody();
	for (int i = 0; i < body.size(); i++)
	{
	    g.fillRect(body.get(i).x * tileWidth, body.get(i).y * tileHeight, tileWidth, tileHeight);
	}
	return frame;
    }
}
