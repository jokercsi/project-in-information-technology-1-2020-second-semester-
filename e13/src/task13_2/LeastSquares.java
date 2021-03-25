// 15817028 Kim Jibin
package task13_2;

public class LeastSquares {
	static Vector minimize(Matrix points, int n) {
		// make augmatrix
		Matrix augMatrix = new Matrix(n+1, n+2);
		
		
		
		
		for(int i = 0; i <= n; i ++) {
			augMatrix.at(i, n + 1, 0.0); //a[i] = 0.0 
			for(int j = 0; j < points.rows(); j++) {
				// a[i] +=  y[j]*pow(x[j],(double)(i-1)) ;
				// at(x, y) 이걸 어떠케 하냐, augmatrxi랑 points는 다른 메트릭스인데 둘다 같이 return해주어야 함
				// points.at(j,1) = y[j]
				augMatrix.at(i, n+1, augMatrix.at(i, n + 1) + points.atY(j) * Math.pow(points.atX(j), (double) i));		
			}
		}
		
		
		
		for(int i = 0; i <= n; i ++) {
			for(int j = 0; j <= i; j ++) {
				augMatrix.at(i, j, 0.0);
				for(int k = 0; k < points.rows(); k++) {
					//System.out.printf(" "+ augMatrix.at(i, j));
					augMatrix.at(i, j, augMatrix.at(i, j) + Math.pow(points.atX(k), (double) (i + j)));
				}
				if (i != j)
					augMatrix.at(j, i, augMatrix.at(i, j));
			}
		}
		

//		for(int i = 0; i < n+1; i ++) {
//			for(int j = 0; j < n+2; j++) {
//				System.out.print(augMatrix.at(i, j) + " ");
//			}
//			System.out.println();
//		}
		
		LinearEq solver = new LinearEq(augMatrix);
		Vector x = solver.solve();
		return x;
	}
}
