//15817028 KIM JIBIN
package task10_2;

import java.awt.*;
import javax.swing.*;

public class Plot2D extends JFrame{
	String windowTitle;
	
	
	Plot2D(){ //constructor
	}
	Plot2D(String name){ //constructor
		windowTitle = name;
	}
	
	
	public static void main(String [] args) {
		//frame = outside of window, panel = inside of window
		
		double[] y = {102.0, 93.9, 85.8, 77.69, 69.59, 61.5, 53.4, 45.3, 37.2, 29.1, 21.0};
		double[] x = {4.0, 15.6, 27.2, 38.79, 50.4, 62.0, 73.59, 85.19, 96.8, 108.4, 120.0};
		// Matrix instance
		Matrix points = new Matrix(args[0]);
		double[][] a;
		a = points.readFromFile();
		for(int i = 0; i < 13; i++) {
			System.out.println(a[i][1]);
		}
			
		
		
		///////////////////////////////////////////////////////////////////////
		JFrame frame = new JFrame(); //JFrame instance
		frame.setVisible(true); // show the window
		frame.setSize(800, 400); //width, height
		frame.setTitle("JIBIN KIM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //window is closed
		//////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		//////////////////////////////////////////////////////////////////////
		JPanel frame_y = new JPanel();
		frame_y.setLayout(new BoxLayout(frame_y ,BoxLayout.Y_AXIS));
		JLabel[] yc = new JLabel[12];
		for(int i = 0; i < 11; i++) {
			yc[i] = new JLabel("" + y[i]);
			yc[i].setBorder(BorderFactory.createEmptyBorder(0,0,12,0));
			frame_y.add(yc[i]);
		}
		frame_y.setSize(15,355);
		frame_y.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));
		//////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////
		//x-coordinate
		JPanel frame_x = new JPanel();
		frame_x.setLayout(new FlowLayout(FlowLayout.LEFT, 43, 0));
		JLabel[] xc = new JLabel[12];
		for(int i = 0; i < 11; i++) {
			xc[i] = new JLabel("" + x[i]);
			xc[i].setSize(1, 1);
			frame_x.add(xc[i]);
		}
		frame_x.setSize(755,15);
		frame_x.setBorder(BorderFactory.createEmptyBorder(0,15,15,0));
		////////////////////////////////////////////////////////////////////////////	
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////
		// GraphPanel instance
		GraphPanel panel = new GraphPanel(points.readFromFile());
		// padding 30
		//10x10 gridlayout
//		panel.setLayout(new GridLayout(10,10));
//		for (int i =0; i<(10*10); i++){
//		    final JLabel label = new JLabel("");
//		    label.setBorder(BorderFactory.createLineBorder(Color.gray));
//		    panel.add(label);
//		}
		////////////////////////////////////////////////////////////////////////////
		
		
		
		
		/////////////////////////////
		//padding
		JPanel up = new JPanel();
		up.setSize(800, 15);
		JPanel right = new JPanel();
		up.setSize(800, 15);
		/////////////////////////////
		
		
		
		
		///////////////////////////////////////////////////////////////////////
		//call GraphPanel class.
		Container contentPane = frame.getContentPane(); //obtain content pain
			contentPane.add(panel, BorderLayout.CENTER);
			contentPane.add(frame_y, BorderLayout.WEST);
			contentPane.add(frame_x, BorderLayout.SOUTH); 
			contentPane.add(up, BorderLayout.NORTH); // NORTH padding 15
			contentPane.add(right, BorderLayout.EAST); //EAST padding 15
		///////////////////////////////////////////////////////////////////////
		frame.setVisible(true);
	}
}

//		int rows = a.length;
//		int columns = a[0].length;
//
//		for(int i = 0; i < rows; i++)
//			for(int j =0; j < columns; j++)
//				System.out.println(a[i][j]);


//		JLabel[] bt = new JLabel[13];
//		frame.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		for(int i = 0; i < 13; i++) {
//			bt[i] = new JLabel("" + a[i][0]);
//			frame.add(bt[i]);
//		}