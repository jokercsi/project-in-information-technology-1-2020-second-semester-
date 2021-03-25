// 15817028 Kim Jibin
package task14_1;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Vector {

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

}

