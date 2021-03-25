package task11_4;

public class Vector {
//	double[] matrix_1d;
	double[][] matrix_2d;
	
//	Vector(double[] matrix_1d){
//		this.matrix_1d = matrix_1d;
//	}
	
	Vector(double[][] matrix_2d){
		this.matrix_2d = matrix_2d;
	}
	
	public void show() {
		int n = matrix_2d.length;
			for(int i = 0; i <n; i++) {
				for(int j =0; j < n; j++) {
					System.out.printf(matrix_2d + " ");
				}
				System.out.printf("\n");
			}
		
	}
}
