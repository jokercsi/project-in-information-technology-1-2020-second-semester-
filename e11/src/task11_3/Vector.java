// 15817028 KIM JIBIN
package task11_3;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Vector {
	double[] data;
	double[][] notdata;
	
	
	Vector(double[] matrix){
		data = matrix;
	}
	
	public Vector(double[][] matrix){
		notdata = matrix;
	}
	
	
	public void show() {
		for(int i = 0; i <notdata.length; i++) {
			for(int j =0; j < notdata[0].length; j++) {
				System.out.printf(notdata[i][j] + " ");
			}
			System.out.printf("\n");
		}
	}
	
//	public void show(String word) {
//		System.out.println(word);
//		if(word.equals("b") || word.equals("solution") || word.equals("Ax-b")) {
//			for(int i = 0; i <data.length; i++) {
//				System.out.printf(data[i] + " ");
//			}
//			System.out.printf("\n");
//		}
//		else {
//			for(int i = 0; i <notdata.length; i++) {
//				for(int j =0; j < notdata[0].length; j++) {
//					System.out.printf(notdata[i][j] + " ");
//				}
//				System.out.printf("\n");
//			}
//		}
//	}
//	public void writeToFile(String filename){
//		try{
//			
//			//System.out.println(notdata.length);
//			StringBuilder builder = new StringBuilder();
//				for(int i = 0; i <notdata.length; i++) {
//					for(int j =0; j < notdata[0].length; j++) {				
//						builder.append(notdata[i][j] + "");
//						if(j < notdata[0].length - 1)
//							builder.append(" ");
//						else
//							builder.append("\r\n");
//					}	
//				}
//				
//			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
//			writer.write(builder.toString());
//			writer.close();
//		}catch(Exception e) {
//		      System.out.println(e.getMessage());
//		}
//	}
	
	public void writeToFile(String filename){
		try{
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < data.length; i++) {						
					builder.append(data[i]+"");
					if(i < data.length - 1)
						builder.append(" ");
					else
						builder.append("\r\n");	
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(builder.toString());
			writer.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
	}
	
	public Vector dot(Vector v) {
		// v = return value of solve().(= double[] x)  ===== x
		// notdata[][]                                  ===== A
		int row = notdata.length;
		int column = notdata[0].length;
		double[] sum = new double[column];
		for (int i = 0; i < row; i++) 
		{
			for(int j = 0; j < column; j++) {				
				sum[i] += notdata[i][j] * v.data[j];    
			}
		}
//		for(int j = 0; j < column; j++) {
//			System.out.println(sum[j]);
//		}
        Vector Ax = new Vector(sum);
		return Ax;
	}
	
	public static Vector sub(Vector A, Vector b) {
		//Vector A = [][]
		//Vector b =[]
		int column = b.data.length;
		double [] Ax_b = new double[column];
		for (int i = 0; i < column; i++) 
		{
			Ax_b[i] = A.data[i] - b.data[i]; 
		}
		
//		for (int i = 0; i < column; i++) 
//		{
//			System.out.println(Ax_b[i]);
//		}
        Vector zero = new Vector(Ax_b); // Ax-b is 0
		return zero;
	}
}

