package swing_example_1;

import javax.swing.*;
import java.awt.*;

public class swing1 {
	
	swing1(){ //constructor
		// No operation
	}
	
	public static void main(String [] args) {
		JFrame frame = new JFrame("Title"); //JFrame instance
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what to do when the window is closed
		frame.setSize(400, 400); //width, height
		Container contentPane = frame.getContentPane(); //obtain content pain
		frame.setVisible(true); // show the window
	}
}
