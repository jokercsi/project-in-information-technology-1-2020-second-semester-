package swing_example_1;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

public class swing3 {
	
	public static void main(String [] args) {
		JFrame mainFrame = new JFrame("Title");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what to do when the window is closed
		mainFrame.setSize(320, 200);
		mainFrame.setLocationRelativeTo(null);
		Container contentPane = mainFrame.getContentPane();
		final JTextField textField = new JTextField();
		final JTextArea textArea = new JTextArea(5,0);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JButton button = new JButton("Add");
		contentPane.add(textField, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(button, BorderLayout.SOUTH);
		mainFrame.setVisible(true);
	}
}
