package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import input.KeyDetector;
import snake.Launcher;

public class Display extends JPanel
{
    private BufferedImage frame;
    public Display(KeyDetector keyDetector, Stopwatch stopwatch)
    {
		JFrame jframe = new JFrame();
		JLabel time = stopwatch.getTimeLabel();
		jframe.add(time, BorderLayout.NORTH);
		jframe.setTitle("Quantum Snake Game");
		jframe.setSize(Launcher.WIDTH + 15, Launcher.HEIGHT + 90);
		jframe.setResizable(false);	
		jframe.setVisible(true);	
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(this);
		jframe.addKeyListener(keyDetector);
		setLayout(new BorderLayout());
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
