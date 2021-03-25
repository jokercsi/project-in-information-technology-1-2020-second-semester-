//15817028 KIM JIBIN
package task10_3;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Plot2D extends JFrame{
	public static void main(String [] args) {
		//frame = outside of window, panel = inside of window
		// Matrix instance
		Matrix points = new Matrix(args[0]);
		double[][] a;
		a = points.readFromFile();
		
//		for(int i = 0; i < 13; i++) {
//			System.out.println(a[i][1]);
//		}
		
		///////////////////////////////////////////////////////////////////////
		JFrame frame = new JFrame(); //JFrame instance
		frame.setVisible(true); // show the window
		frame.setSize(800, 400); //width, height
		frame.setTitle("JIBIN KIM"); //window title = my name
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //window is closed
		frame.setVisible(true);
		//////////////////////////////////////////////////////////////////////
		
		
		
		////////////////////////////////////////////////////////////////////////////
		// GraphPanel instance
		GraphPanel panel = new GraphPanel(a);
		////////////////////////////////////////////////////////////////////////////

		
		
		
		/////////////////////////////
		//padding 30
		JPanel up = new JPanel();
		up.setSize(800, 15);
		JPanel left = new JPanel();
		left.setSize(15, 400);
		JPanel down = new JPanel();
		left.setSize(15, 400);
		JPanel right = new JPanel();
		right.setSize(15, 400);
		/////////////////////////////
		
		
		
		
		///////////////////////////////////////////////////////////////////////
		//call GraphPanel class.
		Container contentPane = frame.getContentPane(); //obtain content pain
			contentPane.add(panel, BorderLayout.CENTER);
			
			// padding NORTH,EAST,WEST,SOUTH
			contentPane.add(up, BorderLayout.NORTH); 
			contentPane.add(right, BorderLayout.EAST);
			contentPane.add(left, BorderLayout.WEST);
			contentPane.add(down, BorderLayout.SOUTH);
		///////////////////////////////////////////////////////////////////////


		// if two commnad line argument are given, save the graph as image. not displaying a window
		if(args.length == 2) {
			panel.saveImage();
			frame.setVisible(false);
		}
		
	}
}
