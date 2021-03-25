package task11_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//reference
//https://matrixcalc.org/ja/#determinant%28%7B%7B2,4,-2,-4%7D,%7B-6,-12,12,24%7D,%7B4,2,2,-4%7D,%7B2,-4,-2,4%7D%7D%29


public class Matrix{
	private static final double EPSILON = 1e-10;
	private final double[][] all_matrix; // combined matrix
	private double[][] a;
	private int[] p;
	
	public Matrix(String args){
		all_matrix = readFromFile(args);
	}
	
	// read txt file
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
	
	
	//recursive determinant
	//reference = http://professorjava.weebly.com/matrix-determinant.html public
	/*
	 * 
	 * double recurvie_det(double[][] matrix) { double sum = 0.0; int s; int length
	 * = matrix.length; // rows = column. because it is determinant
	 * 
	 * if(length==1){ return(matrix[0][0]); } for(int i =0; i < length; i++ ) {
	 * double[][] smaller = new double[length-1][length-1]; for(int j =1; j <
	 * length; j++ ) { for(int k =0; k < length; k++ ) { if(k<i) { smaller[j-1][k] =
	 * matrix[j][k]; } else if(k>i) { smaller[j-1][k-1] = matrix[j][k]; }
	 * 
	 * } } if(i%2==0) { s = 1; } else { s = -1; } sum +=
	 * s*matrix[0][i]*(Math.round(recurvie_det(smaller)/(100*EPSILON))*100*EPSILON);
	 * 
	 * for(i = 0; i <length; i++) { for(int j =0; j < length; j++) {
	 * System.out.printf(matrix[i][j] + " "); } System.out.printf("\n"); } }
	 * return(sum); }
	 */
	
	
	//Determinant method
	//reference = https://introcs.cs.princeton.edu/java/95linear/GaussianElimination.java.html
	public double det() {

		/* 
		 * doesn`t work in huge data.
		 * using recursive method 
		 * double determinant = recurvie_det(all_matrix);
		 * return determinant;
		 */		

		
		int n = all_matrix[0].length;
		
		// A[N][N] = N x N
		double[][] A = new double[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				A[i][j] = all_matrix[i][j];
			}
		}
		
		double det = 0.0;
		double rightdown = 1;
		double leftdown = 1;
        double flag = 1;
		
        for (int p = 0; p < n; p++) {
            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            

            if ( max != p ) { 
            	flag = flag * -1.0;
            	for( int i = p; i < n; i++ ) {
            		double tmp = A[max][i];
            		A[max][i] = A[p][i];
            		A[p][i] = tmp;
            	}
            }

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
            	System.out.println("no");
                //throw new ArithmeticException("Matrix is singular or nearly singular");
            }
            
            
            // pivot within A
            for (int i = p + 1; i < n; i++) {
                double alpha =  A[i][p] / A[p][p];
                for (int j = p; j < n; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }
        
        
		// multiple sarasu ;
		for(int i = 0; i < n; i++) {
			for(int j =0; j < n; j++) {
				//System.out.printf(A[i][j]+" ");
		        rightdown *= A[(i+j)%n][j%n] ;
		        leftdown  *= A[(i+n-j)%n][j%n];
			}
			det += (rightdown - leftdown) * flag ; 
			//System.out.println();
		}
		
	    return  det; 
	}

	public void show() {
		int n = all_matrix[0].length;
		for(int i = 0; i <n; i++) {
			for(int j =0; j < n; j++) {
				System.out.printf(all_matrix[i][j] + " " );
			}
			System.out.printf("\n");
		}
	}
	
	public static void main(String[] args) {
		// read txtfile
		Matrix m = new Matrix(args[0]);
		double det = m.det();
		m.show();
		System.out.println("Det: " + det);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
			writer.write(String.valueOf(det));
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
