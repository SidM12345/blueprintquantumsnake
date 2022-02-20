package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import display.Stopwatch;
import input.KeyDetector;
public class SnakeGame
{
    private Snake snake;
    private Tile[][] tiles;
    private int xLen, yLen;
    private KeyDetector kd;
    private boolean alreadyTurning;
	private Qubites qubites;
	private Stopwatch stopwatch;

    public SnakeGame(int x, int y, KeyDetector kd, boolean wrap, Stopwatch stopwatch)
    {
		xLen = x;
		yLen = y;
		this.kd = kd;
		this.stopwatch = stopwatch;
		
		tiles = new Tile[y][x];
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				tiles[i][j] = new Tile(j, i);
			}
		}
		alreadyTurning = false;
		System.out.println(y);
		snake = new Snake(0, y / 2);
		qubites = new Qubites();
		placeQubites();
    }
    
    private void placeQubites()
    {
		ArrayList<Tile> empty = new ArrayList<Tile>();
		for (int y = 0; y < yLen; y++)
		{
			for (int x = 0; x < xLen; x++)
			{
				//checks if the tile is empty, will need to change once more types of consumables are added
				//also check for wormholes
				if (!snake.checkForSnake(x, y))
				{
					empty.add(tiles[y][x]);
				}
			}
		}
		if (empty.size() == 0) return;
		int randomIndex = (int)(Math.random() * empty.size());
		Tile positive = empty.get(randomIndex);
		empty.remove(randomIndex);

		randomIndex = (int)(Math.random() * empty.size());
		Tile negative = empty.get(randomIndex);

		qubites.bindQubitesToTiles(positive, negative);
    }
    
    public void controlSnake(boolean move)
    {
		//allows the user to change the direction of the snake
		if (!alreadyTurning) alreadyTurning = snake.changeDirection(kd.getKeys());
    }
    
    public void update()
    {
		//update the position of the snake
		//check if the snake died

		Position headPos = snake.move();
		alreadyTurning = false;
		if (headPos.x < 0 || headPos.x >= xLen || headPos.y < 0 || headPos.y >= yLen)
		{
			dead();
		}
		ArrayList<Position> body = snake.getBody();
		if (body.size() == 0) dead();
		for (int i = 1; i < body.size(); i++)
		{
			if (headPos.x == body.get(i).x && headPos.y == body.get(i).y)
			{
				dead();
			}
		}

		int charge = qubites.hasQubite(tiles[headPos.y][headPos.x]);
		if (charge != 0)
		{
			snake.eat(charge);
		}

		if (qubites.getQubitesRemaining() == 0)
		{
			placeQubites();
		}

		qubites.update();
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

				if (tiles[y][x] == qubites.getPositive() || tiles[y][x] == qubites.getNegative())
				{
					g.setColor(qubites.getQubiteColor());
				}

				g.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
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
		int score = stopwatch.getSeconds() + stopwatch.getMinutes()*60 + stopwatch.getHours()*3600;
		System.out.println("You died, your score was " + score);
		stopwatch.end();
		try {Thread.sleep(5000);} catch (Exception ex) {}
		System.exit(0);
	}
}
