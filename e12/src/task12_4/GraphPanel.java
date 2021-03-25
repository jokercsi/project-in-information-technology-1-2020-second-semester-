package task12_4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraphPanel extends JPanel {
	private double[][] points;	
	private double[] zeroPoints;	
	private Color gridColor = new Color(200, 200, 200, 200); // grid color
    private Color lineColor = new Color(30, 30, 30, 180); // line color
    private Color pointColor = new Color(100, 100, 100, 180); // point color
    private Color zeroPointColor = new Color(50, 200, 51, 200); // root point color
    private Color minusPointColor = new Color(15, 15, 200, 180); // minus point color
    private Color plusPointColor = new Color(200, 0, 0, 200); // plus point color
    private Color x_axisColor = new Color(35, 75, 35, 255); 
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private int numberYDivisions = 10;
	private int point = 4; //point width
	private int padding = 15;
	private int labelpadding = 30;
	
	public GraphPanel(double[][] a, double[] zero) {
		/////////////////////////////////////////////////////////
		points = a; // receive points from points.txt
		zeroPoints = zero; // answer root. root = 근(해)
		setBackground(Color.white); // background = white
		/////////////////////////////////////////////////////////
	}
	
	// arrange points in a row (x-axis)
	public double getOrder_X(String a) {
		int rows = points.length;
		double[] array = new double[rows];
		for (int i = 0; i < points.length; i++) {
			array[i] = points[i][0];
		}
		Arrays.parallelSort(array);
		double min, max;
		min = array[0];
		max = array[array.length-1];
		if(a.equals("min")) {
			return min;
		}
		else if(a.equals("max")) {
			return max;
		}
		else 
			return 0;
	}
	// arrange points in a row (y-axis)
	public double getOrder_Y(String a) {
		int rows = points.length;
		double[] array = new double[rows];
		for (int i = 0; i < points.length; i++) {
			array[i] = points[i][1];
		}
		Arrays.parallelSort(array);
		double min, max;
		min = array[0];
		max = array[array.length-1];
		if(a.equals("min")) {
			return min;
		}
		else if(a.equals("max")) {
			return max;
		}
		else 
			return 0;
	}
	
	
	
	
	// save image function
	public void saveImage() {
		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();
		paint(g2);
		try {
		    System.out.println("panel saved as image");
		    ImageIO.write(img, "png", new File("Screen.png"));
		} catch (Exception e) {
		    System.out.println("panel not saved" + e.getMessage());
		}
	}
	
	
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//make graph graphics.
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		double xScale = ((double) getWidth() - (2 * padding) - labelpadding) / (points.length -1);
		double yScale = ((double) getHeight() - 2 * padding - labelpadding) / (getOrder_Y("max") - getOrder_Y("min"));
		
		int y_0 = (int)  (getHeight() - (((getHeight() - padding * 2 - labelpadding)) / (Math.round((getOrder_Y("max") - getOrder_Y("min")) *10)/10.0) +  padding + labelpadding));
		
		// POINTS
		// adjusting value of points array to graphPoints. 
		int rows = points.length;
		int columns = points[0].length;
		double[][] graphPoints = new double[rows][columns];
		for (int i = 0; i < points.length ; i++) {
			int x1 = (int) (i * xScale + padding + labelpadding);
			int y1 = (int) ((getOrder_Y("max") - points[i][1]) * yScale + padding);
			graphPoints[i][0] = x1;
			graphPoints[i][1] = y1;
		}
		
		
		System.out.println(y_0);
		
		
		// 근의 points를 넣는 장소.. 
		// 1.일단 근이기 때문에 y 축은 고정
		// 2 x좌표만 구하면 된다. -> 0.5 더해서 해결
		
		// (Math.round((getOrder_Y("max") - getOrder_Y("min")) *10)/10.0) 
		//     = full amount of y axis value  =  6.5 - (-1.0) = 7.5
		int row = zeroPoints.length;
		int column = 2;
		double[][] zeroGraphPoints = new double[row][column];
		for (int i = 0; i < zeroPoints.length; i++) {
			// 0.5 뭔가 이상하다.
			int x1 = (int) (getWidth() - ((zeroPoints[i] + padding + 0.5) * (getWidth() - padding * 2 - labelpadding)) / (Math.round((getOrder_X("max") - getOrder_X("min")) *10)/10.0))  + padding + labelpadding;
			int y1 = (int) (getHeight() - (( (getHeight() - padding * 2 - labelpadding)) / (Math.round((getOrder_Y("max") - getOrder_Y("min")) *10)/10.0)  +  padding + labelpadding));
			zeroGraphPoints[i][0] = x1;
			zeroGraphPoints[1][1] = y1;
		}
		
		
		
		// set color for graph axis lines
		g2.setColor(Color.WHITE);
		g2.fillRect(padding + labelpadding, padding, getWidth() - (2*padding) - labelpadding, getHeight() - 2*padding - labelpadding);
		g2.setColor(Color.BLACK);
		
		// y-axis lines(vertical element)
		for (int i = 0; i < numberYDivisions + 1; i++) {
			int x0 = padding + labelpadding;
			int x1 = point + padding + labelpadding;
			int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelpadding)) / numberYDivisions +  padding + labelpadding);
			int y1 = y0;
			if (points.length > 0) {
				g2.setColor(gridColor);
				g2.drawLine( padding + labelpadding + 1 + point, y0, getWidth() - padding, y1);
				g2.setColor(Color.BLACK);
                String yLabel = Math.round(((int) ((getOrder_Y("min") + (getOrder_Y("max") - getOrder_Y("min")) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 *10)/10.0 + ""; //each axis label is rounded to the first decimal place
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

		// x-axis lines (holizontal element)
		for (int i = 0; i < numberYDivisions + 1; i++) {
			if (points.length > 1) {
				int x0 = i * (getWidth() - padding * 2 - labelpadding) / (numberYDivisions) + padding + labelpadding;
				int x1 = x0;
				int y0 = getHeight() - padding -labelpadding;
				int y1 = y0 - point;
				if ((i % ((int) ((points.length / 20.0)) )) == 0) {
					g2.setColor(gridColor);
					g2.drawLine(x0, getHeight() -padding -labelpadding - point, x1, padding);
					g2.setColor(Color.BLACK);
					String xLabel = Math.round(((int) ((getOrder_X("min") + (getOrder_X("max") - getOrder_X("min")) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 * 10)/10.0 + ""; //each axis label is rounded to the first decimal place
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
		        
				}
		        g2.drawLine(x0, y0, x1, y1);
		    }
		}
		
		
		// y-axis
		g2.drawLine(padding + labelpadding, getHeight() -padding - labelpadding, padding + labelpadding, padding);
		// x-axis
		g2.drawLine(padding + labelpadding, getHeight() -1 - padding - labelpadding, getWidth() - padding, getHeight() - 1 - padding - labelpadding);
		
		
		
		
		
		// y = 0 "line 선" 
		int y_axis_0 = (int)  (getHeight() - (((getHeight() - padding * 2 - labelpadding)) / (Math.round((getOrder_Y("max") - getOrder_Y("min")) *10)/10.0) +  padding + labelpadding));
		g2.setColor(x_axisColor);
		g2.drawLine(padding + labelpadding ,y_axis_0, getWidth() - padding, y_axis_0);
		
		
		
		
		
		// line of graph
		Stroke oldStroke = g2.getStroke();
		g2.setColor(lineColor);
		g2.setStroke(GRAPH_STROKE);
		for (int i = 0; i < graphPoints.length - 1; i++) {
			int x1 = (int)graphPoints[i][0];
			int x2 = (int)graphPoints[i+1][0];
			int y1 = (int)graphPoints[i][1];
			int y2 = (int)graphPoints[i+1][1];
			g2.drawLine(x1, y1, x2, y2);
		}
		
		// point of graph
		 g2.setStroke(oldStroke);
		 g2.setColor(pointColor);
		for (int i = 0; i < graphPoints.length; i++) {
			int x = (int)graphPoints[i][0] - point/2;
			int y = (int)graphPoints[i][1] - point/2;
			int point_width = point;
			int point_height = point;
			
			if (y < y_0 ) {
				 g2.setColor(plusPointColor);
				 g2.fillOval(x, y, point_width, point_height);
			}
			else if(y > y_0) {
				 g2.setColor(minusPointColor);
				 g2.fillOval(x, y, point_width, point_height);
			}
			// else same color
			g2.fillOval(x, y, point_width, point_height);
		}
		
		
		// y = 0, points
		g2.setStroke(oldStroke);
		g2.setColor(zeroPointColor);
		for (int i = 0; i < graphPoints.length; i++) {
			int x = (int)zeroGraphPoints[i][0] - point/2;
			int y = (int)zeroGraphPoints[1][1] - point/2;
			int point_width = point;
			int point_height = point;
			g2.fillOval(x, y, point_width, point_height);
		}
		
		// y < 0, points
		
		// y > 0 points
	}
}
