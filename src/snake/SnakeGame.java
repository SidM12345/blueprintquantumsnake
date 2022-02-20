package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import display.Stopwatch;
import input.KeyDetector;
import snake.Launcher;;
public class SnakeGame
{
    private Snake snake;
    private Tile[][] tiles;
    private int xLen, yLen;
    private KeyDetector kd;
    private boolean alreadyTurning;

    public SnakeGame(int x, int y, KeyDetector kd, boolean wrap, Stopwatch stopwatch)
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
		alreadyTurning = false;
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
		if (empty.size() == 0) return;
		int randomIndex = (int)(Math.random() * empty.size());
		empty.get(randomIndex).setFood(true);
    }
    
    public void controlSnake()
    {
		//allows the user to change the direction of the snake
		if (!alreadyTurning) alreadyTurning = snake.changeDirection(kd.getKeys());
    }
    
    public void update()
    {
		//update the position of the snake
		
		Position headPos = snake.move();
		alreadyTurning = false;

		if (headPos.x < 0 || headPos.x >= xLen || headPos.y < 0 || headPos.y >= yLen)
		{
			dead();
		}
		ArrayList<Position> body = snake.getBody();
		for (int i = 1; i < body.size(); i++)
		{
			if (headPos.x == body.get(i).x && headPos.y == body.get(i).y)
			{
				System.out.println("collided with " + i);
				dead();
			}
		}
		
		if (tiles[headPos.y][headPos.x].hasFood())
		{
			tiles[headPos.y][headPos.x].setFood(false);
			snake.add();
			placeFood();
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

	private void dead()
	{
		int score = Launcher.endgame();
		System.out.println("You died, your score was" + score);
		
		//System.exit(0);
	}
}
