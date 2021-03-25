package task13_2;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Vector {

	Point2D.Double points;
	Point2D.Double save;
	
	double[][] augMatrix;
	
	// task 12-2 augMatrix(Vector)
	double[] vector;
	
	Vector(Double matrix){
		points = matrix;
	}

	Vector(Matrix augMatrix){
		this.augMatrix = augMatrix.saveMatrix;
		int row = this.augMatrix.length;
		int col = this.augMatrix[0].length;

		
		vector = new double[col];
		for(int i = 0; i < row; i++) {
			vector[i] = this.augMatrix[i][col-1];
			//System.out.println(this.augMatrix[i][col-1]);
		}
	}
	
	public double at(int row) {
		return vector[row];
	}
	
	public void at(int row, double value) {
		vector[row] = value;
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

