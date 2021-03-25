//15817028 KIM JIBIN
package task11_2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LinearEq {
	private static final double EPSILON = 1e-10;
	double[][] aug_matrix; // uncombined matrix(same content in txt file)
	double[][] all_matrix; // combined matrix
	double[][] coeffi;
	double[] constV;
	
	LinearEq(String file){
		all_matrix = readFromFile(file);
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
	
	// connect to Vector
	public Vector A() {
		//returns the coefficient matrix
		int rows = all_matrix.length;
		int columns =  all_matrix[0].length;
		coeffi = new double[rows-1][columns];
		for(int i = 0; i < rows-1; i++) {
			for(int j =0; j < columns; j++) {
				coeffi[i][j] = all_matrix[i][j];
			}
		}
		Vector A = new Vector(coeffi);
		return A;
	}
	
	// connect to Vector
	public Vector b() {
		// return the constant vector
		int rows = all_matrix.length;
		int columns =  all_matrix[0].length;
		constV = new double[columns];
		for(int i = 0; i < columns; i++) {
			constV[i] = all_matrix[rows-1][i];
		}
		Vector b = new Vector(constV);
		return b;
	}
	
	public Vector aug_matrix() {
		// return the constant vector
		int rows = all_matrix.length;
		int columns =  all_matrix[0].length;
		aug_matrix = new double[rows -1][columns + 1];
		for(int i = 0; i < rows-1; i++) {
			for(int j =0; j < columns + 1; j++) {
				if(j == columns) {
					aug_matrix[i][j] = all_matrix[rows-1][i];
				}
				else
					aug_matrix[i][j] = all_matrix[i][j];
			}
		}
		Vector aug = new Vector(aug_matrix);
		return aug;
	}
	
	public Vector solve() {
		int n =constV.length;
		
		for(int k = 0; k < n; k++) {
			
			// find pivot row
			int max = k;
			for(int i = k + 1; i < n; i++) {
				if(Math.abs(coeffi[i][k]) > Math.abs(coeffi[max][k]));
				max = i;
			}
			// swap row in coeffi matrix
			double[] temp = coeffi[k]; coeffi[k] = coeffi[max]; coeffi[max] = temp;
			double t = constV[k]; constV[k] = constV[max]; constV[max] = t;
			// singular or nearly singular
            if (Math.abs(coeffi[k][k]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }
            
            // pivot within coeffi and const
            for (int i = k + 1; i < n; i++) {
                double alpha = coeffi[i][k] / coeffi[k][k];
                constV[i] -= alpha * constV[k];
                for (int j = k; j < n; j++) {
                	coeffi[i][j] -= alpha * coeffi[k][j];
                }
            }
		}
        // back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += coeffi[i][j] * x[j];
            }
            x[i] = (constV[i] - sum) / coeffi[i][i];
        }
        Vector solution = new Vector(x);
		return solution;
	}
	
	
	public static void main(String [] args) {
		LinearEq solver = new LinearEq(args[0]);
		solver.A().show("A");
		solver.b().show("b");
		solver.aug_matrix().show("Augmented Matrix");
		
		//task11-1
		//solver.A().writeToFile(args[1]);
		//solver.b().writeToFile(args[1], true);
		
		//task11-2
		Vector solution = solver.solve(); 
		solution.show("solution"); 
		//solver.A().dot(solution);
		Vector.sub(solver.A().dot(solution), solver.b());
		Vector.sub(solver.A().dot(solution), solver.b()).show("Ax-b"); //solution = return value of solution.
		solution.writeToFile(args[1]);
	}
}
