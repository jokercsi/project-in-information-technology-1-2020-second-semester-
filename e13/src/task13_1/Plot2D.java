package task13_1;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;

public class Plot2D {
    public static void main(String[] args) {
    	
		// read Matrix(matrix = Point2D)
		Matrix instance = new Matrix(args[0]);
	//	Point2D.Double[] matrix;
	//	matrix = instance.readFromFile(args[0]);
		///////////////////////////////////////////////////////////
		
		
		// JFrame
		JFrame frame = new JFrame("task13_1");
		frame.setSize(600,600);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		////////////////////////////////////////////////////////////////
		
		
		
		GraphPanel panel = new GraphPanel(instance, 103);
		frame.add(panel);
		
		
		Container contentPane = frame.getContentPane(); //obtain content pain
			contentPane.add(panel, BorderLayout.CENTER);

		frame.setVisible(true);
    }
}
