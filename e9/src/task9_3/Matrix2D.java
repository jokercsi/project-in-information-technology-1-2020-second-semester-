//15817028 KIM JIBIN
package task9_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;


//int[][] data;
public class Matrix2D {
	private static double [][] data;
	private static double [][] data2;
	private String inputfileName1;
	private String inputfileName2;
	private String outputfileName;
	
	public Matrix2D(String inputDirPath1, String inputDirPath2, String outputDirPath) {
		inputfileName1 = inputDirPath1;
		inputfileName2 = inputDirPath2;
		outputfileName = outputDirPath;
	}

	
	// a1.txt
	public void readFromFile1(){
		try { 
			FileReader fileReader = new FileReader(new File(inputfileName1));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			int j = 0;
			data = new double[6][6]; // array is fixed size

			
			while ((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				String[] values = line.trim().split("[\\s]+", 0);
				for(int i = 0; i < values.length; i++) {
					//System.out.println(Double.parseDouble(values[i]));
					data[j][i] = Double.parseDouble(values[i]); //string to int
				}
				j++;
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
	
	
	// a2.txt
	public void readFromFile2(){
		try { 
			FileReader fileReader = new FileReader(new File(inputfileName2));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			int j = 0;
			data2 = new double[6][6]; // array is fixed size

			
			while ((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				String[] values = line.trim().split("[\\s]+", 0);
				for(int i = 0; i < values.length; i++) {
					//System.out.println(Double.parseDouble(values[i]));
					data2[j][i] = Double.parseDouble(values[i]); //string to int
				}
				j++;
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
	
	public static double add(double a, double b) {
		return a + b;
	}
	public static double sub(double a, double b) {
		return a - b;
	}
	public static double mul(double a, double b) {
		return a * b;
	}
	public static double dot(double a, double b) {
		return a *  b;
	}
	
	public void writeToFile(String outputfileName){
		try{
			FileWriter fw = new FileWriter(outputfileName);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
				int rows = 6, columns = 6;
			    double[][] sum = new double[rows][columns];
			    
			    //a+b
			    pw.printf("a+b\n");			    
			    for(int i = 0; i < rows; i++) {
			        for (int j = 0; j < columns; j++) {
			            sum[i][j] = add(data[i][j], data2[i][j]);
			            pw.printf(sum[i][j]+" ");
			        }
			        pw.printf("\n"); 
			    }
			    //a-b
			    pw.printf("a-b\n");
			    for(int i = 0; i < rows; i++) {
			        for (int j = 0; j < columns; j++) {
			            sum[i][j] = sub(data[i][j], data2[i][j]);
			            pw.printf(sum[i][j]+" ");
			        }
			        pw.printf("\n"); 
			    }
			    //a*b
			    pw.printf("a*b\n");
			    for(int i = 0; i < rows; i++) {
			        for (int j = 0; j < columns; j++) {
			            sum[i][j] = mul(data[i][j], data2[i][j]);
			            pw.printf(sum[i][j]+" ");
			        }
			        pw.printf("\n"); 
			    }
			    //a.b (dot product)
			    pw.printf("a.b\n");
			    for(int i = 0; i < rows; i++) {
			        for (int j = 0; j < columns; j++) {
			            sum[i][j] += dot(data[i][j], data2[i][j]);
			            pw.printf(sum[i][j]+" ");
			        }
			        pw.printf("\n"); 
			    }
			    
			    pw.close();
			
	
		bw.close();
		fw.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		String inputDirPath1 = args[0]; // first argument
		String inputDirPath2 = args[1]; // second argument
		String outputDirPath = args[2];
		
		Matrix2D a = new Matrix2D(inputDirPath1,inputDirPath2, outputDirPath);
		a.readFromFile1();
		a.readFromFile2();
		a.writeToFile(outputDirPath);


		System.out.println("program started"); 
		
	}
}