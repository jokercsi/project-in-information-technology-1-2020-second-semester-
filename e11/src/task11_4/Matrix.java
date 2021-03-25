package task11_4;

import java.io.*;
import java.util.*;

public class Matrix {
	private static final double EPSILON = 1e-10;
	
	// read matrix
	double[][] matrix;
	// matrix for calculation
	double[][] A;
	// inverse matrix
	double[][] inv;

	
	Matrix(String txtfile){
		matrix = readFromFile(txtfile);
	}
	
	//Determinant method
//	public double det() {
//		////////////////////////////////////////////////
//		//using recursive method 
//		double det = recurvie_det(matrix);
//		///////////////////////////////////////////////
//	    return det; 
//	}
	
	// recursive determinant
//	public double recurvie_det(double[][] matrix) {
//		double sum = 0.0;
//		int s;
//		int length = matrix.length; // rows = column.  because it is determinant
//	    if(length==1){
//	        return(matrix[0][0]);
//	    }
//		for(int i =0; i < length; i++ ) {
//			double[][] smaller = new double[length-1][length-1];
//			for(int j =1; j < length; j++ ) {
//				for(int k =0; k < length; k++ ) {
//					if(k<i) {
//						smaller[j-1][k] = matrix[j][k];
//					}
//					else if(k>i) {
//						smaller[j-1][k-1] = matrix[j][k];
//					}
//				}
//			}
//			if(i%2==0) {
//				s = 1;
//			}
//			else {
//				s = -1;
//			}
//			sum += s*matrix[0][i]*(recurvie_det(smaller));
//		}
//		return(sum);
//	}
	
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
			System.out.println(e);
		}
		
		//2 double[][]
		double a[][] = new double[rows][columns];
		try {
			Scanner input = new Scanner(new File(file));
			for(int i = 0; i < rows; ++i) {
				for(int j = 0; j < columns; ++j) {
					if(input.hasNextDouble()) {
						a[i][j] = input.nextDouble();
					}
				}
			}
			input.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		return(a);
	}
	
	
	// reference = https://thira.plavox.info/blog/2008/06/_c.html
	public double[][] inv(int power) {
		
		int n = matrix.length;
		double buf;
		int i, j, k; 

		// A Matrix
		double[][] A = new double[n][n];
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				A[i][j] = matrix[i][j];
			}
		}
		
		// inv Matrix (단위 행렬 initialize it as unit matrix)
		double[][] inv = new double[n][n];
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				inv[i][j] = (i == j) ? 1.0: 0.0; // true or false
			}
		}
		
		for(i=0;i<n;i++){
			buf = 1/A[i][i];
			for(j=0;j<n;j++){
				A[i][j] *= buf;
				inv[i][j] *= buf;
			}
			for(j=0;j<n;j++){
				if(i!=j){
					buf=A[j][i];
					for(k=0;k<n;k++){
						A[j][k]-=A[i][k]*buf;
						inv[j][k] -= inv[i][k]*buf;
					}
				}
			}
		}
		
		// power = 제곱
		
		if (power == 1) {
			return matrix;
		}
		else if (power == -1) {			
			return inv;
		}
		else {
			return null;
		}
	}

	
	public void show(double[][] a, int power) {
		int n = a.length;
		if(power == -1) {
			System.out.println("A^(-1)");
		}
		else if(power == 0) {
			System.out.println("A^(-1)A");
		}
		else if(power == 1) {
			System.out.println("AA^(-1)");
		}
		for(int i = 0; i <n; i++) {
		 	for(int j =0; j < n; j++) {
				System.out.printf(a[i][j] + " " );
			}
			System.out.printf("\n");
		}
	}
	
	public double[][] mul(double[][] a, double[][] b) {
		// N x N matrix
		int n = a.length;
		int i, j, k;
		
		double[][] answer = new double[n][n];
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				for(k = 0; k < n; k++) {
					answer[i][j] += (a[i][k] * b[k][j]);
				}
			}
		}
		return answer;
	}
	
	public void writeToFile(String filename, double[][] a){
		try{
			StringBuilder builder = new StringBuilder();
			int n = a.length;
			int i, j;
			for(i = 0; i < n; i++) {
				for(j = 0; j < n; j++) {
					builder.append(a[i][j]+"");
					if(j < n - 1)
						builder.append(" ");
					else
						builder.append("\r\n");	
				}
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(builder.toString());
			writer.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Matrix matrix = new Matrix(args[0]); // initailize a Matrix A with the first commnad line

		
		// 1. calculate inverse matrix
		matrix.show(matrix.inv(-1), -1);
		
		//A inverse * A
		matrix.show(matrix.mul(matrix.inv(-1), matrix.inv(1)), 0);
		
		//A * A inverse
		matrix.show(matrix.mul(matrix.inv(1), matrix.inv(-1)), 1);
		
		// inverse matrix
		matrix.writeToFile(args[1], matrix.inv(-1));
		//
		//matrix.writeToFile(args[1], matrix.mul(matrix.inv(-1), matrix.inv(1)));
		
		
	}
}
