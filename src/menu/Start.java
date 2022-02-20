package menu;
import java.awt.event.*;
import input.KeyDetector;
import snake.Launcher;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.math.*;
import java.util.*;
import java.lang.*;

public class Start implements ActionListener
{
	JFrame frame;
	JButton startButton, helpButton, quitButton;
	JLabel title;
	public Start() 
	{
		title = new JLabel("Quantum Snake Game");
		startButton = new JButton("Start");
		startButton.setMargin(new Insets(10,10,10,10));
		helpButton = new JButton("Help");
		helpButton.setMargin(new Insets(10,10,10,10));
		quitButton = new JButton("Quit");
		quitButton.setMargin(new Insets(10,10,10,10));
		
		startButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(title);
		panel.add(startButton);
		panel.add(helpButton);
		panel.add(quitButton);
		
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Quantum Snake Game Menu");
		frame.pack();
		frame.setVisible(true);
			
	}

	public static void main(String[] args) {
		new Start();
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
