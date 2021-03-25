package task12_4;



public class NonLinearEq {
	private static final double EPSILON = 1e-10;
	private int maxlter = 30;
	private double x;
	private double[][] X;
	
//	NonLinearEq(int maxlter) {
//		this.maxlter = maxlter;
//	}
	
	NonLinearEq() {
	}
	// task 12-3
	NonLinearEq(double x) {
		this.x = x;
	}
	
	// task 12-4
	NonLinearEq(double[][] x) {
		this.X = x;
	}
	
	public double solveSecant(double x0, double x1) {
		int n = 1;
		
		double d, y0, y1;
		
		d = x1 - x0;
		y0 = f(x0);
		
		
		do {
			y1 = f(x1);
			d = - d * y1 / (y1 - y0);
			x1 = x1 + d;
			y0 = y1;;
			n++;
		}while(Math.abs(d) > EPSILON && n < maxlter);
		
		if(n == maxlter || Double.isNaN(x)) {
			//System.out.println("no answer");
			return 404;
		}
		else {
			//System.out.println("searching x from " +  x1 + "\n");
			return x1;
		}
	}
	
	public double f(double x) {
		return(Math.exp(x) - Math.sin(Math.PI*x/3.0));

	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////
//	public double solveNewton(double init_x) {
//		int n = 0;
//		double d;
//		
//		do {
//			d = -f(x)/df(x);
//			x = x + d;
//			n++;
//		}while(Math.abs(d) > EPSILON && n < maxlter);
//		
//
//		if(n == maxlter || Double.isNaN(x)) {
//		//	System.out.printf("searching x from " + init_x + ": \n");
//			return 0;
//		}
//		else {
//		//	System.out.printf("searching x from " + init_x + ", solution is " + x + "\n");
//			return x;
//		}
//	}
//	
//	public double f(double x) {
//		return(Math.exp(x) - Math.sin(3.14*x/3.0));
//	}
//	
//	public double df(double x) {
//		return (Math.exp(x) - 3.14/3.0*Math.cos(3.14*x/3.0));
//	}
////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	public static void main(String [] args) {

	}
}


