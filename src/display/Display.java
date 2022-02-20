package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import input.KeyDetector;
import snake.Launcher;

public class Display extends JPanel
{
    private BufferedImage frame;
    public Display(KeyDetector keyDetector)
    {
	JFrame jframe = new JFrame();
	jframe.setTitle("Game");
	jframe.setSize(Launcher.WIDTH, Launcher.HEIGHT);
	jframe.setResizable(false);	
	jframe.setVisible(true);	
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.add(this);
	jframe.addKeyListener(keyDetector);
    }
    
    public void bufferFrame(BufferedImage frame)
    {
	this.frame = frame;
    }
    
    public void paintComponent(Graphics g)
    {
	if (frame != null)
	{
	    g.drawImage(frame, 0, 0, Launcher.WIDTH, Launcher.HEIGHT, null);
	}
    }
}
