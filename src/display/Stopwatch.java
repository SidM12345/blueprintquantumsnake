package display;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener{
	
	JFrame frame = new JFrame();
	JLabel timeLabel = new JLabel();
	int elapsedTime;
	int seconds;
	int minutes;
	int hours;
	String sec = String.format("%02d", seconds);
	String min = String.format("%02d", minutes);
	String hour = String.format("%02d", hours);
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		public void actionPerformed (ActionEvent e) {
			elapsedTime = elapsedTime + 1000;
			hours = (elapsedTime/3600000);
			minutes = (elapsedTime/60000)%60;
			seconds = (elapsedTime/1000)%60;
			String sec = String.format("%02d", seconds);
			String min = String.format("%02d", minutes);
			String hour = String.format("%02d", hours);
			timeLabel.setText(hour + ":" + min + ":" + sec);
		}
	});
	
	public Stopwatch(){
		
		timeLabel.setText(hour + ":" + min + ":" + sec);
		//timeLabel.setBounds(100,100,200,100);
		timeLabel.setFont(new Font ("Constantia", Font.PLAIN, 35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
	
	}

	public void start() {
		timer.start();
	}

	public void end() {
		timer.stop();
		elapsedTime = 0;
		
	}
	public int getSeconds () {
		return seconds;
	}
	public int getMinutes() {
		return minutes;
		
	}
	public int getHours() {
		return hours;
	}
    public JLabel getTimeLabel() {
        return timeLabel;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
