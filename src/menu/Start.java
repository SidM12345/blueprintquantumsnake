package menu;
import java.awt.event.*;
import input.KeyDetector;
import snake.Launcher;
import java.awt.*;
import javax.swing.*;

public class Start
{
	JButton startButton, helpButton, quitButton;
	public Start() 
	{
		JFrame frame = new JFrame();
		JLabel title = new JLabel("Quantum Snake Game", SwingConstants.CENTER);
		JButton startButton = new JButton("Start");
		startButton.setMargin(new Insets(10,10,10,10));
		JButton quitButton = new JButton("Quit");
		quitButton.setMargin(new Insets(10,10,10,10));

		// String rules = "In Quantum Snake Game, 2 'qubites' will spawn for you to eat." +
		// 			   "\nOne of them will add to your length, and the other will subtract from it. " + 
		// 			   "\nEating one of the qubites them will reveal which one is 'positive' and which is 'negative'." + 
		// 			   "\nUntil then, the qubites are entangled and superposed" +
		// 			   "\n But the qubites are on a clock; if you don't eat either of them within a certain time," + 
		// 			   "\n they will decay into permanent worm holes; the goal is to last as long as possible."; 
		// JTextArea textArea = new JTextArea(rules);
		// textArea.setMargin(new Insets(10, 10, 10, 10));
		// textArea.setColumns(40);
		// textfield.setPreferredSize(new Dimension(300, 200));
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(title);
		panel.add(startButton);
		panel.add(quitButton);
		// panel.add(textArea);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Quantum Snake Game Menu");
		frame.pack();
		frame.setVisible(true);	

		quitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				System.exit(0);
			}
		});

		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				frame.setVisible(false);
				Launcher.startGame();
			}
		});
	}
}
