// 15817028 Kim Jibin
package task14_1;

public class EigenSolver {
	// lambda = Eigen Value
	private static double[] eigenValues;
	// x = Eigen Vector
	private static double[][] eigenVectors;
	
	private static int maxIter = 100;
	private static double EPSILON = 1e-10;
	
	// refernece
	//https://www.geeksforgeeks.org/program-to-check-if-a-matrix-is-symmetric/
	public static boolean isSymmetric(double[][] a) {
		int row = a.length;
		int col = a[0].length;
		if (row != col) {
			return false;
		}
		for (int i = 0; i < row; ++i) {
			for (int j = i + 1; j < col; ++j) {
				if (a[i][j] - a[j][i] >EPSILON) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public static void solve(double[][] a) {
		
		// lambda = eigen value
		// x = eigen Vector
		
		int it;
		int n = a.length;
		int i, j;
		int m = 0, k = 0;
		
		double s;
		double t, u;
		double cos_t, sin_t;
		
		double a_row_m[] = new double[n];
		double a_row_k[] = new double[n];
		double a_col_m[] = new double[n];
		double a_col_k[] = new double[n];
		
		// initialize eigen Values and Vectors
		eigenValues = new double[n];
		eigenVectors = new double[n][n];
		
		
		if (!isSymmetric(a)) {
			System.out.printf("Input matrix is not symmetric.");
			System.exit(1);
		}
		
		// 1 0 0..
		// 0 1 0..
		// 0 0 1..
		// unit matrix
		double r[][] = new double[n][n];
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				r[i][j] = 0.0;
			}
			r[i][i] = 1.0;
		}
		
		// repeat until all no diagnal element
		for(it = 0; it < maxIter; it++) {
			s = EPSILON;
			for(i = 0; i < n; i++) {
				for(j = i+1; j < n; j++ ) {
					if(Math.abs(a[i][j]) > s) {
						s = Math.abs(a[i][j]);
						k=i;
						m=j;
					}
				}
			}
			if(s == EPSILON) {
				//System.out.println("jacobi: finished after " + it + " cycles s=" + a[k][m]);
				
				// if jacobi is finished, it is saved in eigenValue
				////////////////////////////////////////////////////////
				for(i = 0; i < n; i++) {
					//System.out.printf(a_row_k[i] + " " + a_row_m[i] + " " + a_col_k[i]+ " " + a_col_m[i]);
					for(j = 0; j < n; j++) {
						eigenVectors[i][j] = r[i][j];
						eigenValues[i] = a[i][i];
					}
				}
				////////////////////////////////////////////////////////
				
				return;
			}else {
				//System.out.println("jacobi: cycle " + it + ", found non-orthogonal max a[" + k +"] [" + m + "] = " + s);
			}
			
			// calcurate rotation parameter.
			if(Math.abs(a[k][k] - a[m][m]) < EPSILON) {
				cos_t = 1 / Math.sqrt(2.0);
				sin_t = a[k][m] > 0.0 ? 1.0/Math.sqrt(2.0) : -1.0/Math.sqrt(2.0);
			}
			else {
				t = (2.0 * a[k][m]) / (a[k][k] - a[m][m]);
				u = Math.sqrt(1.0 + t * t);
				cos_t = Math.sqrt(( 1.0 + u) / (2.0 * u));
				sin_t = Math.sqrt((-1.0 + u) / (2.0 * u)) * (t > 0.0 ? 1.0 : -1.0);
			}
			//System.out.println("jacobi: cos_t = " + cos_t + ", sin_t = " + sin_t);
			
			
			// R'*A*R 
			for(j = 0; j < n; j++) {
				a_row_k[j] =		a[k][j] * cos_t + a[m][j] * sin_t;
				a_row_m[j] = -1.0 * a[k][j] * sin_t + a[m][j] * cos_t;
			}
			for(i = 0; i <n ; i++) {
				a_col_k[i] =	 	a[i][k] * cos_t + a[i][m] * sin_t;
				a_col_m[i] = -1.0 * a[i][k] * sin_t + a[i][m] * cos_t;
			}
			a_col_k[k] = a[k][k] * cos_t * cos_t
					+ a[k][m] * cos_t * sin_t
					+ a[m][k] * cos_t * sin_t
					+ a[m][m] * sin_t * sin_t;
			
			a_col_m[m] = a[k][k] * sin_t *sin_t
					- a[m][k] * cos_t * sin_t
					- a[k][m] * cos_t * sin_t
					+ a[m][m] * cos_t * cos_t;
			
			a_col_k[m] = 0.0;
			a_col_m[k] = 0.0;
			
			for(j =0; j < n; j++) {
				a[k][j] = a_row_k[j];
				a[m][j] = a_row_m[j];
			}
			for(i = 0; i < n; i++) {
				a[i][k] = a_col_k[i];
				a[i][m] = a_col_m[i];
			}
			//System.out.println("jacobi: A(" + it + ")");
			
			
			
			// apply X*R
			for(i = 0 ;i < n; i++) {
				a_col_k[i] =		 r[i][k] * cos_t + r[i][m] * sin_t;
				a_col_m[i] = - 1.0 * r[i][k] * sin_t + r[i][m] * cos_t;
			}
			for(i = 0; i < n; i++) {
				r[i][k] = a_col_k[i];
				r[i][m] = a_col_m[i];
			}
			//System.out.println("jacobi: R(" + it + ")");
		}
		if (it >= maxIter) {
			System.out.println("jacobi: didn't converge after " + it + " iteration.");
			System.exit(1);
		}
	}
	
	
	public static double det(double[][] A) {
		
		int n = A.length;
		
		double det = 0.0;
		double rightdown = 1.0;
		//double leftdown = 1.0;
		double flag = 1.0;
		
		double max;
		int ip = 0;
		
		for (int p = 0; p < n; p++) {
			// find pivot row and swap
			ip = p;
			max = Math.abs(A[p][p]);
			for (int i = p + 1; i < n; i++) {
				if (Math.abs(A[i][p]) > max) {
				    max = Math.abs(A[i][p]);
				    ip = i;
				}
			}
			

			if ( ip != p ) { 
				flag = flag * - 1.0;
				for( int i = p ; i < n; i++ ) {
					double tmp = A[p][i];
					A[p][i] = A[ip][i];
					A[ip][i] = tmp;
				}
			}
			
			
			// pivot within A
			for (int i = p + 1; i < n; i++) {
				double alpha =  A[i][p] / A[p][p];
				for (int j = p; j < n; j++) {
					A[i][j] -= A[p][j] * alpha ;
					//System.out.println(A[i][j]);
					//System.out.println(i + " "+ j  +" "+ p +" "+ A[p][j] * alpha );
				}
			}
		}
		
		
		// multiple sarasu ;
//		for(int i = 0; i < n; i++) {
//			for(int j =0; j < n; j++) {
//		        rightdown *= A[(i+j)%n][j%n] ;
//		        leftdown  *= A[(i+n-j)%n][j%n];
//			}
//			det += (rightdown - leftdown) * flag ; 
//		}
		
		
		for(int i = 0; i < n; i++) {
			rightdown *= A[i][i] ;
		}
		det = rightdown * flag  ;
		
		return  det; 
	}
	
	public static double[][] lambda_unit(double lambda,int n) {
		int i, j;
		
		double[][] unit = new double[n][n];
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				unit[i][j] = 0.0;
			}
			unit[i][i] = lambda;
		}
		
		return unit;
	}
	
	
	public static double[][] sub(double[][] A, double[][] Lambda_I) {
		int i, j;
		int n = A.length;
		double[][] answer = new double[n][n];
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				answer[i][j] = A[i][j] - Lambda_I[i][j];
			}
		}
		
		return answer;
	}
	
	// j = row
	// k = col
	// i  eigenVectors row
	public static double dot(double[][] answer, int i, int j) {
		
		double sum = 0.0;
		int n = answer.length;
		
		for(int k = 0; k < n; k++) {
			//System.out.printf(answer[j][k] + " ");
			sum += answer[j][k] * eigenVectors[k][i];
		}

		return sum;
	}
	

	public static void main(String [] args) {
		
		// solve matrix = a
		double[][] a;

		// read A Matrix and adjust it to solve method.
		Matrix solve = new Matrix(args[0]);
		a = solve.matrix;
		
		solve(a); // EigenValue is created in here.

		// A matrix = A
		double[][] A;
		// lambda * I
		double[][] lambda_I;
		int n = eigenValues.length;
		
		// read A Matrix 
		Matrix instance  = new Matrix(args[0]);
		A = instance.matrix;
		
		
		// answer[][] = A * lambda * I
		double[][] answer = new double[n][n];
		
		for(int i = 0; i < n; i++) {
			lambda_I = lambda_unit(eigenValues[i], n);
			answer = sub(A,lambda_I);
			
			System.out.println( "===========     " + "lambda" + (i + 1) + "     ===========");
			System.out.println("A - lambda * I");
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					System.out.printf(answer[j][k] +" ");
				}
				System.out.println();
			}
			System.out.println("det(A - lambda * I)=" + det(answer));
			

			// eigen vector (A_-_lambda_*_I)x
			lambda_I = lambda_unit(eigenValues[i], n);
			answer = sub(A,lambda_I);
			System.out.println("(A - lambda * I)x");
			for(int j = 0; j < n; j++) {
				System.out.printf(dot(answer, i, j) + " ");
			}
			System.out.println();
		}
		
	}
}

