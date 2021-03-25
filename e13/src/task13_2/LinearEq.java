// 15817028 Kim Jibin
package task13_2;

import java.awt.geom.Point2D;

public class LinearEq {
	private static final double EPSILON = 1e-10;
	// task12-2
	Matrix augMatrix;
	
	LinearEq(Matrix matrix){
		this.augMatrix = matrix;

	}

	public Vector solve() {
		
		int n = augMatrix.saveMatrix.length;
		Vector x = new Vector(augMatrix);
		
		int ip;
		for(int k = 0; k < n-1; k++) {
			double max = Math.abs(augMatrix.at(k, k));
			ip = k;
			for(int i = k + 1; i < n; i++) {
				if(Math.abs(augMatrix.at(i, k)) > max) {
					max = Math.abs(augMatrix.at(i, k));
					ip = i;
				}
			}
			
			// singular or nearly singular
			if (max < EPSILON) {
				throw new ArithmeticException("Matrix is singular or nearly singular");
			}
			
			if (ip != k) {
				for(int j = k; j <= n; j++) {
					max = augMatrix.at(k, j);
					augMatrix.at(k, j, augMatrix.at(ip, j));
					augMatrix.at(ip, j, max);
				}
			}
			
			
			
			max = augMatrix.at(k, k);
			// forward
			for(int i = k; i <= n; i++)
				augMatrix.at(k, i, augMatrix.at(k, i)/max);
			for(int j = k+1; j < n; j++) {
				max = augMatrix.at(j, k);
				for(int i = k; i <= n; i++)
					augMatrix.at(j, i, augMatrix.at(j, i) - augMatrix.at(k, i) * max);
				}
			}
		
		// back substitution
		for(int i = n-1; i >= 0; i--) {
			double s = 0.0;
			for(int j = i+1; j < n; j++) {
				s += augMatrix.at(i, j) * x.at(j);
			}
			x.at(i, (augMatrix.at(i, n) - s) /  augMatrix.at(i, i));
		}
		
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j < n; j++) {
				System.out.print(augMatrix.at(i, j+1) + " ");
			}
			System.out.println();
		}

		return x;
	}
}
