package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import snake.Snake;

public class KeyDetector implements KeyListener
{
    private boolean[] keys;
    
    public KeyDetector()
    {
	keys = new boolean[256];
    }
    
    public void keyControl(Snake snake)
    {
	snake.changeDirection(keys);
    }
    
    public void keyTyped(KeyEvent e)
    {
	
    }
    
    public void keyPressed(KeyEvent e)
    {
	keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e)
    {
	keys[e.getKeyCode()] = false;
    }
}
