//15817028 Kim Jibin
package task9_3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

//int[][] data;
public class Matrix2D1 {

	public static double[][] readFromFile(String filename){
		int rows = 0;
		int columns = 0;
		try { 
			Scanner input = new Scanner (new File(filename));
			rows = 0;
	
			while (input.hasNextLine()) {
				//System.out.println(line);
				Scanner colReader = new Scanner(input.nextLine());
				if(colReader.hasNextDouble()) {
					for (columns = 0; colReader.hasNextDouble(); columns++) {
						colReader.nextDouble();
					}
					++rows;
				}
				colReader.close();
			}
			input.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println("rows: " + rows + ", columns : " + columns );
		double a[][] = new double[rows][columns];
		
		try {
			Scanner input = new Scanner(new File(filename));
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
		matrixShow(a, filename);
		return(a);
	}
	
	public static void writeToFile( double[][] a, String filename, boolean append){
		int rows = a.length;
		int columns = a[0].length;
		try{
			StringBuilder builder = new StringBuilder();
			
			for(int i = 0; i < rows; i++) {
				for(int j =0; j < columns; j++) {						
					builder.append(a[i][j]+"");
					if(j < columns - 1)
						builder.append(" ");
					else
						builder.append("\r\n");
				}	
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append));
			writer.write(builder.toString());
			writer.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
		
	}
	
	public static void matrixShow(double[][] a, String s) {
		int rows = a.length;
		int columns = a[0].length;
		System.out.println(s);
		for(int i = 0; i < rows; i++) {
			for(int j =0; j < columns; j++) {				
				System.out.printf(a[i][j] + " ");
			}
			System.out.printf("\n");
		}
	}
	public static double[][] matrixAdd (double[][] a, double[][] b) {
		int rows = a.length;
		int columns = a[0].length;
		if (rows != b.length || columns != b[0].length) {
			System.out.println("matrixAdd: size mismatch");
			System.exit(1);
		}
		double[][] c = new double[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j =0; j < columns; j++)
				c[i][j] = a[i][j] + b[i][j];
		return(c);
	}
	public static double[][] matrixSub (double[][] a, double[][] b) {
		int rows = a.length;
		int columns = a[0].length;
		if (rows != b.length || columns != b[0].length) {
			System.out.println("matrixSub: size mismatch");
			System.exit(1);
		}
		double[][] c = new double[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j =0; j < columns; j++)
				c[i][j] = a[i][j] - b[i][j];
		return(c);
	}
	public static double[][] matrixMul (double[][] a, double[][] b) {
		int rows = a.length;
		int columns = a[0].length;
		if (rows != b.length || columns != b[0].length) {
			System.out.println("matrixMul: size mismatch");
			System.exit(1);
		}
		double[][] c = new double[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j =0; j < columns; j++)
				c[i][j] = a[i][j] * b[i][j];
		return(c);
	}
	
	
	public static double[][] matrixDot (double[][] a, double[][] b) {
		int rows = a.length;
		int ksize =a[0].length;
		int columns = b[0].length;
		if (ksize != b.length) {
			System.out.println("matrixDot: size mismatch");
			System.exit(1);
		}
		double[][] c = new double[rows][columns];
		for(int i = 0; i < rows; i++)
			for(int j =0; j < columns; j++) {
				double s = 0;
				for(int k = 0; k < ksize; k++)
					s += a[i][k] * b[k][j];
				c[i][j] = s;
			}
		return(c);
	}


	public static void main(String[] args) {
		double[][] a1, a2;
		if (args.length != 2) {
			System.out.println("argument error: two input filenames should be specified in command line");
			System.exit(1);
		}
//		String inputDirPath1 = args[0]; // first argument
//		String inputDirPath2 = args[1]; // second argument
//		String outputDirPath = args[2]; // thrid argument
//		
//		Matrix2D1 a = new Matrix2D1(inputDirPath1, inputDirPath2, outputDirPath);
//		a.readFromFile();
//		a.writeToFile();
		
		a1 = readFromFile(args[0]);
		a2 = readFromFile(args[1]);
		String output = "output1.txt";
		double[][] sum = matrixAdd(a1,a2);
		matrixShow(sum, "a1 + a2");
		writeToFile(sum, output, false);
		double [][] sub = matrixSub(a1,a2);
		matrixShow(sub, "a1 - a2");
		writeToFile(sub,output, true);
		double[][] prod = matrixMul(a1,a2);
		writeToFile(prod, output, true);
		matrixShow(prod, "a1 * a2");
		double[][] dot = matrixDot(a1,a2);
		writeToFile(dot, output, true);
		matrixShow(dot, "a1 . a2");
		

		System.out.println("program started"); // for debug
		
	}
}