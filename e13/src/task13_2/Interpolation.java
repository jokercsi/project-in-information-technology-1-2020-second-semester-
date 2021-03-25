// 15817028 Kim Jibin
package task13_2;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Interpolation {

	public static double lagrange(Vector[] p, double x) {
    	double pn=0, li;
    	
    	for(int i=0; i<p.length; i++) {
    		li = 1.0;
    		for(int k=0; k < p.length; k++) {
    			if(k != i) 
    				li *= (x - p[k].x()) / (p[i].points.x - p[k].x());
    		}
    		pn += li * p[i].y(); 
    	}
    	return pn;
	}
	
	public static void main(String [] args) {
		
		// read Matrix(Point2D)
		Matrix instance = new Matrix();
		Point2D.Double[] matrix;
		matrix = instance.readFromFile(args[0]);
		
		
		//System.out.println(matrix[0].getX());
		
		
		Vector[] a = new Vector[matrix.length];
		for(int i = 0; i < matrix.length; i++) {
			a[i] = new Vector(matrix[i]);
		}
		
		//System.out.println(lagrange(a, 15.6));
		
	}


}
