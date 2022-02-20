package snake;

import display.Display;
import display.Stopwatch;
import input.KeyDetector;

public class Launcher
{
    public static final int WIDTH = 800, HEIGHT = 600;
	public static Stopwatch stopwatch = new Stopwatch();
    public static void main(String[] args)
    {
		stopwatch.start();
		KeyDetector kd = new KeyDetector();
		Display d = new Display(kd, stopwatch);
		SnakeGame game = new SnakeGame(40, 30, kd, false, stopwatch);
		
		int SPEED = 10;
		
		long lastFrame = System.currentTimeMillis();
		while (true)
		{
			game.controlSnake();
			if (System.currentTimeMillis() - lastFrame > 1000 / SPEED)
			{
				game.update();
				d.bufferFrame(game.getFrame());
				d.repaint();
				lastFrame = System.currentTimeMillis();
			}
		
	//	    try {Thread.sleep(1000 / FPS);}
	//	    catch (Exception ex) {}
		}
    }
	public static int endgame () {
		stopwatch.end();
		return stopwatch.getSeconds() + stopwatch.getMinutes()*60 + stopwatch.getHours()*3600;

	}
}
