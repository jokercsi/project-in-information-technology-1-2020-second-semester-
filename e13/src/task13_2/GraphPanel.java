// 15817028 Kim Jibin
package task13_2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.Arrays;

import javax.swing.JPanel;


public class GraphPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private int[] padding = {75, 30, 30, 30};  // left, right, top, and bottom
    private Color gridColor = new Color(200, 200, 200, 200);
    private Color lineColor = new Color(44, 102, 230, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int margin = 8;
    private int numberXDivisions = 10;
    private int numberYDivisions = 10;
    private Vector[] points;
    private Vector[] interpolatedPoints;
    private Vector leastapproximationPoints;

    public GraphPanel() {
        this(600, 600);
    }

    public GraphPanel(int width, int height) {
        super();
        setPreferredSize(new Dimension(width, height));
    }

    public GraphPanel(Matrix points, int n) {
        this();
        
        // points = Matrix
        // this.points = Vector
        
        
        // 1. copy the matrix to the list of vectors.
        // write code here
        this.points = new Vector[points.matrix.length];
        
		for (int i = 0; i < points.matrix.length; ++i) {
			this.points[i] = new Vector(points.matrix[i]);
			//System.out.println(points.matrix[i]);
		}
        // 2. calculate minmum and maximum x values of the points
		// write code here
        double minX = getOrder_X(this.points, "min"), maxX = getOrder_X(this.points, "max");
        
        
        interpolatedPoints = new Vector[n];
        for (int i = 0; i < n; ++i) {
            double x = minX + (double) i * (maxX - minX) / (double) (n - 1);
            interpolatedPoints[i] = new Vector(x, Interpolation.lagrange(this.points, x));
        }
        
        
        //task 12-2
        int n1 = 2;
        for (int i = 0; i < n1; ++i) {
        	leastapproximationPoints = LeastSquares.minimize(points, n1); //2차로 근접
            
        	double X,Y;
            for(X= minX; X<maxX; X+=1) 
            {
            	//System.out.println(X);
				Y= 0.0;
				for(i=0;i<n1;i++) 
				{
					Y += leastapproximationPoints.vector[i] * Math.pow(X,i);
				}
				//System.out.println(X+" " + Y);
            }
        }
    }
    
    
    //x-axis is points(X)
	public double getOrder_X(Vector[] p, String order) {
		int length = p.length;
		double[] array = new double[length];
		for (int i = 0; i < length; i++) {
			array[i] = p[i].x();
		}
		Arrays.parallelSort(array);
		double min, max;
		min = array[0];
		max = array[array.length-1];
		if(order.equals("min")) {
			return min;
		}
		else if(order.equals("max")) {
			return max;
		}
		else 
			return 0;
	}
    
	
	
	// y-axis is interpolatedPoints
	public double getOrder_Y(Vector[] p, String order) {
		int length = p.length;
		double[] array = new double[length];
		for (int i = 0; i < length; i++) {
			array[i] = p[i].saveY();
		}
		Arrays.parallelSort(array);
		double min, max;
		min = array[0];
		max = array[array.length-1];
		if(order.equals("min")) {
			return min;
		}
		else if(order.equals("max")) {
			return max;
		}
		else 
			return 0;
	}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        // 3. calculate minimum and maximum values for all points on each axis
        // write code here
        double minX = getOrder_X(this.points, "min"), maxX = getOrder_X(this.points, "max");
        double minY = getOrder_Y(interpolatedPoints, "min"), maxY = getOrder_Y(interpolatedPoints, "max");

        
        
        double xScale = ((double) getWidth() - (padding[0] + padding[1])) / (maxX - minX);
        double yScale = ((double) getHeight() - (padding[2] + padding[3])) / (maxY - minY);
        
        
        Vector[] graphPoints = new Vector[points.length];
        for (int i = 0; i < points.length; i++) {
            double x = Math.round(getWidth() - padding[1] - (maxX - points[i].x()) * xScale);
            double y = Math.round((maxY - points[i].y()) * yScale) + padding[2];
            graphPoints[i] = new Vector(x, y);
        }

        Vector[] linePoints = new Vector[interpolatedPoints.length];
        for (int i = 0; i < interpolatedPoints.length; i++) {
            double x = Math.round(getWidth() - padding[1] - (maxX - interpolatedPoints[i].saveX()) * xScale) ;
            double y = Math.round((maxY - interpolatedPoints[i].saveY()) * yScale) + padding[2];
            linePoints[i] = new Vector(x, y);
        }
        


        
        
        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding[0], padding[2], getWidth() - (padding[0] + padding[1]), getHeight() - (padding[2] + padding[3]));

        
        
        
        // draw grid and label
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x = padding[0];
            int y = (int)Math.round(getHeight() - (double)i * (getHeight() - (padding[2] + padding[3])) / numberYDivisions) - padding[2];

            g2.setColor(gridColor);
            g2.drawLine(x, y, getWidth() - padding[1], y);

            g2.setColor(Color.BLACK);
            String yLabel = String.format("%.1f", minY + (maxY - minY) * (double) i / numberYDivisions);
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x - labelWidth - margin, y + metrics.getHeight() / 2);
        }

        for (int i = 0; i < numberXDivisions + 1; i++) {
            int x = padding[0] + i * (getWidth() - (padding[0] + padding[1])) / numberXDivisions;
            int y = getHeight() - padding[3];

            g2.setColor(gridColor);
            g2.drawLine(x, getHeight() - padding[3], x, padding[3]);

            g2.setColor(Color.BLACK);
            String xLabel = String.format("%.1f", minX + (maxX - minX) * (double) i / numberXDivisions);
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(xLabel);
            g2.drawString(xLabel, x - labelWidth / 2, y + metrics.getHeight() + margin);
        }

        // draw axes
        g2.setColor(Color.BLACK);
        g2.drawLine(padding[0], getHeight() - padding[3], padding[0], padding[3]);
        g2.drawLine(padding[0], getHeight() - padding[3], getWidth() - padding[1], getHeight() - padding[3]);

        // plot point
        // 4. write code here
        Stroke oldStroke = g2.getStroke();
		g2.setStroke(oldStroke);
		g2.setColor(Color.BLACK);
			for (int i = 0; i < graphPoints.length; i++) {
				int x = (int)graphPoints[i].saveX() - pointWidth/2;
				int y = (int)graphPoints[i].saveY() - pointWidth/2;
				int point_width = pointWidth;
				int point_height = pointWidth;
				g2.fillOval(x, y, point_width, point_height);
			}

        // draw lines
		// 5. write code here
		g2.setColor(lineColor);
		g2.setStroke(GRAPH_STROKE);
		for (int i = 0; i < linePoints.length - 1; i++) {
			//System.out.println(linePoints[i].saveY());
			int x1 = (int)linePoints[i].saveX();
			int y1 = (int)linePoints[i].saveY();
			int x2 = (int)linePoints[i+1].saveX();
			int y2 = (int)linePoints[i+1].saveY();
			g2.drawLine(x1, y1, x2, y2);
		}
		
    }
}
