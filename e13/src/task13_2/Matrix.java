// 15817028 Kim Jibin
package task13_2;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

public class Matrix {
	// task12-1
	Point2D.Double[] matrix; 
	
	// task12-2 (augMatrix)
	double[][] saveMatrix;

	
	Matrix(String txtfile){
		matrix = readFromFile(txtfile);
	}
	
	
	// task12-2
	Matrix(int rows, int cols){
		saveMatrix = new double[rows][cols];
	}
	
	
	public Point2D.Double[] readFromFile(String file){
		int rows = 0;
		int columns = 0;
		ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
		try { 
			Scanner input = new Scanner (new File(file));
			for(rows =0; input.hasNextLine(); rows++) {
				String nextLine = input.nextLine();
				if(nextLine.isEmpty()) {
					break;
				}
				else {		
					Scanner colReader = new Scanner(nextLine);
					ArrayList<Double> col = new ArrayList<Double>();
					for (columns = 0; colReader.hasNextDouble(); columns++) {
						col.add(colReader.nextDouble());
					}
					data.add(col);
					colReader.close();
				}
			}
			input.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		
		//2 double[][]
		Point2D.Double[] points = new Point2D.Double[rows];
		try {
			Scanner input = new Scanner(new File(file));
			for(int i = 0; i < rows; ++i) {
				for(int j = 0; j < columns; ++j) {
					if(input.hasNextDouble()) {
						points[i] = new Point2D.Double(data.get(i).get(0), data.get(i).get(1));
					}
				}
			}
			input.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		return(points);
	}
	
	// task 12-2 length of input
	public int rows() {
		return matrix.length;
	}
	
	public void at(int row, int col, double value) {
		saveMatrix[row][col] = value; //augMatrix.at(i, n + 1, 0.0); //a[i] = 0.0 
	}

	public double at(int row, int col) {
		
		return saveMatrix[row][col];
	}
	
	public double atX(int row) {
		return matrix[row].x;
	}
	
	public double atY(int row) {
		return matrix[row].y;
	}
}