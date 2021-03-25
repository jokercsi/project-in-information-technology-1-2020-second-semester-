//15817028 KIM JIBIN
package task11_1;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Vector {
	double[] data;
	double[][] notdata;
	
	Vector(double[] matrix){
		data = matrix;
	}
	
	Vector(double[][] matrix){
		notdata = matrix;
	}
	
	public void show(String word) {
		System.out.println(word);
		if(word.equals("b") || word.equals("solution")) {
			for(int i = 0; i <data.length; i++) {
				System.out.printf(data[i] + " ");
			}
			System.out.printf("\n");
		}
		else {
			for(int i = 0; i <notdata.length; i++) {
				for(int j =0; j < notdata[0].length; j++) {
					System.out.printf(notdata[i][j] + " ");
				}
				System.out.printf("\n");
			}
		}
	}
	
	public void writeToFile(String filename){
		try{
			
			//System.out.println(notdata.length);
			StringBuilder builder = new StringBuilder();
				for(int i = 0; i <notdata.length; i++) {
					for(int j =0; j < notdata[0].length; j++) {				
						builder.append(notdata[i][j] + "");
						if(j < notdata[0].length - 1)
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
	
	public void writeToFile(String filename, boolean append){

		try{
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < data.length; i++) {						
					builder.append(data[i]+"");
					if(i < data.length - 1)
						builder.append(" ");
					else
						builder.append("\r\n");	
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append));
			writer.write(builder.toString());
			writer.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
		
	}
	
}
