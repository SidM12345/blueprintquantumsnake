package snake;

import display.Display;
import display.Stopwatch;
import input.KeyDetector;
import menu.Start;

public class Launcher
{
    public static final int WIDTH = 800, HEIGHT = 600;
    public static void main(String[] args)
    {
		startGame();
		// new Start();
    }	
	

	public static void startGame()	
	{
		Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();
		KeyDetector kd = new KeyDetector();
		Display d = new Display(kd, stopwatch);
		SnakeGame game = new SnakeGame(40, 30, kd, false, stopwatch);
		
		int SPEED = 10;
		// try {Thread.sleep(5000);} catch (Exception ex) {}
		long lastFrame = System.currentTimeMillis();
		// System.out.println("Entering the while loop");
		while (true)
		{
			game.controlSnake(false);
			if (System.currentTimeMillis() - lastFrame > 1000 / SPEED)
			{
				game.update();
				d.bufferFrame(game.getFrame());
				d.repaint();
				lastFrame = System.currentTimeMillis();
			}
		}
    }
}
