// 15817028 Kim Jibin
package task12_4;

import java.awt.*;
import javax.swing.*;


public class Plot2D extends JFrame{
	public static void main(String [] args) {
		// frame = outside of window, panel = inside of window
		// x1 = initail search start
		// x2 = end 
		// d = dvision
		// h = divided value
		double x1, x2, h;
		int d;
		x1 = Double.parseDouble(args[0]);
		x2 = Double.parseDouble(args[1]);
		d = Integer.parseInt(args[2]);
		
		h = (x2 - x1)/(d-1);
		
		// (x, y)
		double[][] a = new double[d][d];
		
		// x value of poin£ô
		for(int i = 0; i < d; i ++) {
			a[i][0] = x1 + h*i;
		}
	
		// y value of points (f(x) = x - e)
		for(int i = 0; i < d; i ++) {
			a[i][1] = Math.exp(a[i][0]) - Math.sin(3.14*a[i][0]/3.0); 
		}
		
//		for(int i = 0; i < d; i ++) {		
//			System.out.println(a[i][0]);
//		}
		

		//±Ù!!!
		double[] answer = new double[d];
		
		NonLinearEq solver = new NonLinearEq(a);
		for(int i = 0; i < d; i ++) {
			if(i+1 == d) {
				break;
			}
			else {
				answer[i] = solver.solveSecant(a[i][0], a[i+1][0]);
			}
		}
		
		///////////////////////////////////////////////////////////////////////
		JFrame frame = new JFrame(); //JFrame instance
		frame.setVisible(true); // show the window
		frame.setSize(600, 600); //width, height
		frame.setTitle("JIBIN KIM"); //window title = my name
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //window is closed
		frame.setVisible(true);
		//////////////////////////////////////////////////////////////////////
		
		
		
		////////////////////////////////////////////////////////////////////////////
		// GraphPanel instance
		GraphPanel panel = new GraphPanel(a, answer);
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
		if(args.length == 3) {
			panel.saveImage();
			frame.setVisible(false);
		}
		
	}
}
