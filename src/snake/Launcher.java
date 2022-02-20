package snake;

import display.Display;
import input.KeyDetector;

public class Launcher
{
    public static final int WIDTH = 800, HEIGHT = 600;
    public static void main(String[] args)
    {
		KeyDetector kd = new KeyDetector();
		Display d = new Display(kd);
		SnakeGame game = new SnakeGame(40, 30, kd, false);
		
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
}
