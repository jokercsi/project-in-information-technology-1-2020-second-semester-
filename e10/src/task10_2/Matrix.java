//15817028 KIM JIBIN
package task10_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
	double [][] a;
	String points_row;
	
	Matrix(String points){
		points_row = points;
	}
	
	public double[][] readFromFile(){
		int rows = 0;
		int columns = 0;
		ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
		try { 
			Scanner input = new Scanner (new File(points_row));
			for(rows =0; input.hasNextLine(); rows++) {
				String nextLine = input.nextLine();
				if(nextLine.isEmpty()) {
					break;
				}
				else {		
					Scanner colReader = new Scanner(nextLine);
					ArrayList<Double> col = new ArrayList<Double>();
					for (columns = 0; colReader.hasNextDouble(); columns++) {
						//System.out.println(colReader.nextDouble());
						col.add(colReader.nextDouble());
					}
					data.add(col);
					//System.out.println(data);
					colReader.close();
				}
			}
			input.close();
		} catch (IOException e) {
			System.out.println(e);
			//System.exit(1);
		}
		
		//2 double[][]
		double a[][] = new double[rows][columns];
		try {
			Scanner input = new Scanner(new File(points_row));
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
//		System.out.println("rows: " + rows + ", columns : " + columns );
//		matrixShow(data, filename);
		return(a);
	}	
	public static void matrixShow(ArrayList<ArrayList<Double>> a, String s) {
		int rows = a.size();
		int columns = a.get(0).size();
		System.out.println(s);
		for(int i = 0; i < rows; i++) {
			for(int j =0; j < columns; j++) {						
				System.out.print(a.get(i).get(j));
				if(j < columns - 1)
					System.out.print(" ");
				else
					System.out.print("\r\n");
			}	
		}
	}

	public static void writeToFile( ArrayList<ArrayList<Double>> a, String filename, boolean append){
		int rows = a.size();
		int columns = a.get(0).size();
		try{
			StringBuilder builder = new StringBuilder();
			
			for(int i = 0; i < rows; i++) {
				for(int j =0; j < columns; j++) {						
					builder.append(a.get(i).get(j)+ "");
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

	public static ArrayList<ArrayList<Double>> matrixAdd(ArrayList<ArrayList<Double>> a, ArrayList<ArrayList<Double>> b){
		int rows = a.size();
		int columns = a.get(0).size();
		if( rows != b.size() || columns != b.get(0).size()) {
			System.out.println("matirxAdd : size mismatch");
			//System.exit(1);
		}
		ArrayList<ArrayList<Double>> c = new ArrayList<ArrayList<Double>>(columns);
		for (int i =0; i < rows; i++) {
			ArrayList<Double> e = new ArrayList<Double>(columns);
			for(int j = 0; j < columns; j++)
				e.add(a.get(i).get(j) + b.get(i).get(j));
			c.add(e);
		}
		return(c);
	}
	
	public static ArrayList<ArrayList<Double>> matrixSub(ArrayList<ArrayList<Double>> a, ArrayList<ArrayList<Double>> b){
		int rows = a.size();
		int columns = a.get(0).size();
		if( rows != b.size() || columns != b.get(0).size()) {
			System.out.println("matirxAdd : size mismatch");
			//System.exit(1);
		}
		ArrayList<ArrayList<Double>> c = new ArrayList<ArrayList<Double>>(columns);
		for (int i =0; i < rows; i++) {
			ArrayList<Double> e = new ArrayList<Double>(columns);
			for(int j = 0; j < columns; j++)
				e.add(a.get(i).get(j) - b.get(i).get(j));
			c.add(e);
		}
		return(c);
	}
	
	public static ArrayList<ArrayList<Double>> matrixMul(ArrayList<ArrayList<Double>> a, ArrayList<ArrayList<Double>> b){
		int rows = a.size();
		int columns = a.get(0).size();
		if( rows != b.size() || columns != b.get(0).size()) {
			System.out.println("matirxAdd : size mismatch");
			//System.exit(1);
		}
		ArrayList<ArrayList<Double>> c = new ArrayList<ArrayList<Double>>(columns);
		for (int i =0; i < rows; i++) {
			ArrayList<Double> e = new ArrayList<Double>(columns);
			for(int j = 0; j < columns; j++)
				e.add(a.get(i).get(j) * b.get(i).get(j));
			c.add(e);
		}
		return(c);
	}
	
	public static ArrayList<ArrayList<Double>> matrixDot(ArrayList<ArrayList<Double>> a, ArrayList<ArrayList<Double>> b){
		int rows = a.size();
		int ksize = a.get(0).size();
		int columns = b.get(0).size();
		if(ksize != b.size()) {
			System.out.println("matirxDot : size mismatch");
			//System.exit(1);
		}
		ArrayList<ArrayList<Double>> c = new ArrayList<ArrayList<Double>>(columns);
		for (int i =0; i < rows; i++) {
			ArrayList<Double> e = new ArrayList<Double>(columns);
			for(int j = 0; j < columns; j++) {
				double s = 0;
				for(int k = 0; k< ksize; k++)
					s += a.get(i).get(k) * b.get(k).get(j);
				e.add(s);
			}
			c.add(e);
		}
		return(c);
	}
	public static void main(String[] args) {
//		ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
//		ArrayList<ArrayList<Double>> data2 = new ArrayList<ArrayList<Double>>();
//
//		data = readFromFile(args[0]);
//		data2 = readFromFile(args[1]);
//		String output = args[2];
//		
//		
//		ArrayList<ArrayList<Double>> sum = matrixAdd(data,data2);
//		matrixShow(sum, "a1 + a2");
//		writeToFile(sum, output, false);
//		ArrayList<ArrayList<Double>> sub = matrixSub(data,data2);
//		matrixShow(sub, "a1 - a2");
//		writeToFile(sub, output, true);
//		ArrayList<ArrayList<Double>> mul = matrixMul(data,data2);
//		matrixShow(mul, "a1 * a2");
//		writeToFile(mul, output, true);
//		ArrayList<ArrayList<Double>> dot = matrixDot(data,data2);
//		matrixShow(dot, "a1 . a2");
//		writeToFile(dot, output, true);
//		System.out.println("program started"); // for debug
		
	}
}
