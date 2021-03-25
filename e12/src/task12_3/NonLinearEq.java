// 15817028 Kim Jibin
package task12_3;

public class NonLinearEq {
	private static final double EPSILON = 1e-10;
	private int maxlter = 30;
	private double x;
	
//	NonLinearEq(int maxlter) {
//		this.maxlter = maxlter;
//	}
	
	NonLinearEq(double x) {
		this.x = x;
	}
	
	public double solveNewton(double init_x) {
		int n = 0;
		double d;
		
		do {
			d = -f(x)/df(x);
			x = x + d;
			n++;
		}while(Math.abs(d) > EPSILON && n < maxlter);
		

		if(n == maxlter || Double.isNaN(x)) {
		//	System.out.printf("searching x from " + init_x + ": \n");
			return 0;
		}
		else {
		//	System.out.printf("searching x from " + init_x + ", solution is " + x + "\n");
			return x;
		}
	}
	
	
	public double f(double x) {
		return(Math.exp(x) - Math.sin(3.14*x/3.0));
	}
	
	public double df(double x) {
		return (Math.exp(x) - 3.14/3.0*Math.cos(3.14*x/3.0));
	}
}
