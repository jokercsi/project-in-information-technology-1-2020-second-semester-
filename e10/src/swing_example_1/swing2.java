package swing_example_1;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;

public class swing2 {

	public static void main(String [] args) {
		JFrame mainFrame = new JFrame("Swing Sample");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what to do when the window is closed
		mainFrame.setSize(320, 160);
		mainFrame.setLocationRelativeTo(null);
		
		Container contentPane = mainFrame.getContentPane(); //³»¿ë¹°
		JLabel label = new JLabel("SwingLabel"); 
		JButton button = new JButton("SwingButton");
		contentPane.add(label, BorderLayout.CENTER);
		contentPane.add(button, BorderLayout.SOUTH);
		
		mainFrame.setVisible(true);
		
	}
	
}
