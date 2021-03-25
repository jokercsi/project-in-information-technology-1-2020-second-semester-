package task13_1;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

public class Matrix {
	private static final double EPSILON = 1e-10;
	
	Point2D.Double[] matrix; 

	Matrix(){
	}
	
	Matrix(String txtfile){
		matrix = readFromFile(txtfile);
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
//	public double[][] readFromFile(String file){
//		int rows = 0;
//		int columns = 0;
//		ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
//		try { 
//			Scanner input = new Scanner (new File(file));
//			for(rows =0; input.hasNextLine(); rows++) {
//				String nextLine = input.nextLine();
//				if(nextLine.isEmpty()) {
//					break;
//				}
//				else {		
//					Scanner colReader = new Scanner(nextLine);
//					ArrayList<Double> col = new ArrayList<Double>();
//					for (columns = 0; colReader.hasNextDouble(); columns++) {
//						col.add(colReader.nextDouble());
//					}
//					data.add(col);
//					colReader.close();
//				}
//			}
//			input.close();
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		
//		//2 double[][]
//		double a[][] = new double[rows][columns];
//		try {
//			Scanner input = new Scanner(new File(file));
//			for(int i = 0; i < rows; ++i) {
//				for(int j = 0; j < columns; ++j) {
//					if(input.hasNextDouble()) {
//						a[i][j] = input.nextDouble();
//					}
//				}
//			}
//			input.close();
//		} catch (IOException e) {
//			System.out.println(e);
//			System.exit(1);
//		}
//		return(a);
//	}
}
