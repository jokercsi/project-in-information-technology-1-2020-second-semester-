//15817028 Kim Jibin
package task9_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;


//int[][] data;
public class Matrix2D {
	private int [][] data;
	private String inputfileName;
	private String outputfileName;
	
	public Matrix2D(String inputDirPath, String outputDirPath) {
		inputfileName = inputDirPath;
		outputfileName = outputDirPath;
	}

	public void readFromFile(){
		try { 
			FileReader fileReader = new FileReader(new File(inputfileName));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			int j = 0;
			data = new int[6][5]; // array is fixed size

			
			while ((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				String[] values = line.trim().split("[\\s]+", 0);
				for(int i = 0; i < values.length; i++) {
					//System.out.println(Integer.parseInt(values[i]));
					data[j][i] = Integer.parseInt(values[i]); //string to int
				}
				j++;
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public void writeToFile(){
		try{
			FileWriter fw = new FileWriter(outputfileName);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for(int i = 0; i < data.length; i++) {
				for(int j =0; j < data[i].length; j++) {						
					pw.printf(data[i][j]+" ");
					System.out.printf(data[i][j] + " ");
				}	
				pw.printf("\n"); 
				System.out.printf("\n");
			}
			pw.close();
			
	
		bw.close();
		fw.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
		

	}
	
	public static void main(String[] args) {
		
		String inputDirPath = args[0]; // first argument
		String outputDirPath = args[1]; // second argument
		
		Matrix2D a = new Matrix2D(inputDirPath, outputDirPath);
		a.readFromFile();
		a.writeToFile();

		//System.out.println("program started"); // for debug
		
	}
}