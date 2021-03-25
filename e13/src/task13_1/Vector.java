package task13_1;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Vector {

//	double[] data;
//	double[][] notdata;
	Double points;
	Double save;
	
	Vector(Double matrix){
		points = matrix;
	}
	
	
	public Vector(double x, double lagrange) {
		save = new Point2D.Double(x, lagrange);
	}
	
	double x() {
		return points.x;
	}
	
	double y() {
		return points.y;
	}
	
	double saveX() {
		return save.x;
	}
	
	double saveY() {
		return save.y;
	}

	
//	public void show() {
//		for(int i = 0; i <notdata.length; i++) {
//			for(int j =0; j < notdata[0].length; j++) {
//				System.out.printf(notdata[i][j] + " ");
//			}
//			System.out.printf("\n");
//		}
//	}
	
	

}

