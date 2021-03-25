// 15817028 Kim Jibin
package task14_1;

import java.io.*;
import java.util.*;

public class Matrix {
	private static final double EPSILON = 1e-10;
	
	double[][] matrix; 

	Matrix(){
	}
	
	Matrix(String txtfile){
		matrix = readFromFile(txtfile);
	}
	
	public double[][] readFromFile(String file){
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
			System.out.println("Input matrix is not symmetric.");
			
		}
		
		//2 double[][]
		double a[][] = new double[rows][columns];
		try {
			Scanner input = new Scanner(new File(file));
			for(int i = 0; i < rows; ++i) {
				if (columns != data.get(0).size()) {
					throw new IOException();
				}
				
				for(int j = 0; j < columns; ++j) {
					if(input.hasNextDouble()) {
						a[i][j] = input.nextDouble();
					}
				}
			}
			input.close();
		} catch (IOException e) {
			System.out.println("Input matrix is not symmetric.");
			System.exit(1);
		}
		return(a);
	}
	
	public void show(double[][] a) {
		int rows = a.length;
		int columns = a[0].length;
		
		for( int i = 0; i < rows; i++) {
			for( int j = 0; j < columns; j++)
				System.out.printf( " %9.5f", a[i][j] );
			System.out.println();
		}
	}
}
