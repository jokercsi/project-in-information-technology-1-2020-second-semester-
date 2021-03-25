//15817028 Kim Jibin
package task9_2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;


//int[][] data;
public class Matrix2D {
//	private int [][] data;
	private ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>(); //2-dimension ArrayList
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
			ArrayList<Integer> dataIn = new ArrayList<>();
			
			//int j = 0;
			
			while ((line = bufferedReader.readLine()) != null) {
				dataIn = new ArrayList<>();  //initialize Arraylist for dataIn (to remove all element that is stored in dataIn because of previous .add(element))
				String[] values = line.trim().split("[\\s]+", 0);
				for(int i = 0; i < values.length; i++) {
						dataIn.add(Integer.parseInt(values[i]));		
				}
				data.add(dataIn);
				//j++;
			}
			//System.out.println(data);
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
	
	public void writeToFile(){
		try{
			FileWriter fw = new FileWriter(outputfileName);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for(int i = 0; i < data.size(); i++) {
				//System.out.println(data.get(i));
				for(int j =0; j < data.get(i).size(); j++) {					
					pw.printf(data.get(i).get(j)+" "); 
				}
				pw.printf("\n"); // line break
			}
			pw.close();
			
	
		bw.close();
		fw.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		
		String inputDirPath = args[0];
		String outputDirPath = args[1];
		
		Matrix2D a = new Matrix2D(inputDirPath, outputDirPath);
		a.readFromFile();
		a.writeToFile();

		System.out.println("program started"); // for debug
		
	}
}