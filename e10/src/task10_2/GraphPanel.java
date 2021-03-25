//15817028 KIM JIBIN
package task10_2;

import java.awt.*;
import javax.swing.*;

public class GraphPanel extends JPanel {
	
	private double[][] points;		
	private Color gridColor = new Color(200, 200, 200, 200); // grid color
    private Color lineColor = new Color(44, 102, 230, 180); // line color
    private Color pointColor = new Color(100, 100, 100, 180); // point color
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private int numberYDivisions = 10;
	private int point = 4; //point width
	
	public GraphPanel(double[][] a) {
		/////////////////////////////////////////////////////////
		// receive points from points.txt
		points = a;
		// background = white
		setBackground(Color.white);
		/////////////////////////////////////////////////////////
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//make graph graphics.
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		double xScale = (double) getWidth() / (points.length -1);
		double yScale = (double) getHeight() / (81);
		
		
		int rows = points.length;
		int columns = points[0].length;
		double[][] graphPoints = new double[rows][columns];
		for (int i = 0; i < points.length; i++) {
			int x1 = (int) (i * xScale);
			int y1 = (int) ((102.00 - points[i][1]) * yScale);
			graphPoints[i][0] = x1;
			graphPoints[i][1] = y1;
		}
		
		
		
		
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		
		
		
		
		
		
		
		for (int i = 0; i < numberYDivisions + 1; i++) {
			int x0 = 0;
			int x1 = point;
			int y0 = getHeight() - ((i * getHeight() ) / numberYDivisions);
			int y1 = y0;
			if (points.length > 0) {
				g2.setColor(gridColor);
				g2.drawLine(1 + point, y0, getWidth(), y1);
				g2.setColor(Color.BLACK);
            }
            g2.drawLine(x0, y0, x1, y1);
        }
		
		
		
		
		
		for (int i = 0; i < numberYDivisions; i++) {
			if (points.length > 1) {
				int x0 = i * getWidth() / (numberYDivisions - 1);
				int x1 = x0;
				int y0 = getHeight();
				int y1 = y0 - point;
				if ((i % ((int) ((points.length / 20.0)) + 1)) == 0) {
					g2.setColor(gridColor);
					g2.drawLine(x0, getHeight() - point, x1, 0);
					g2.setColor(Color.BLACK);
		        }
		        g2.drawLine(x0, y0, x1, y1);
		    }
		}
		
		
		// y-axis
		g2.drawLine(0, getHeight(), 0, 0);
		// x-axis
		g2.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
		
		// line of graph
		Stroke oldStroke = g2.getStroke();
		g2.setColor(lineColor);
		g2.setStroke(GRAPH_STROKE);
		for (int i = 0; i < graphPoints.length - 1; i++) {
			int x1 = (int)graphPoints[i][0];
			int y1 = (int)graphPoints[i][1];
			int x2 = (int)graphPoints[i+1][0];
			int y2 = (int)graphPoints[i+1][1];
			g2.drawLine(x1, y1, x2, y2);
		}
		
		// point of graph
		 g2.setStroke(oldStroke);
		 g2.setColor(pointColor);
			for (int i = 0; i < graphPoints.length - 1; i++) {
				int x = (int)graphPoints[i][0] - point/2;
				int y = (int)graphPoints[i][1] - point/2;
				int point_width = point;
				int point_height = point;
				g2.fillOval(x, y, point_width, point_height);
			}

		System.out.println(getWidth());
		System.out.println(getHeight());
	}
}
