package task12_1;

public class NonLinearEq {
	private static final double EPSILON = 1e-10;
	private int maxlter = 30;
	private double x;
	
	
	NonLinearEq(double x) {
		this.x = x;
	}
	
	public int solveNewton(double init_x) {
		//System.out.println(x);
		
		int n = 0;
		double d;
		
		do {
			d = -f(x)/df(x);
			x = x + d;
			n++;
		}while(Math.abs(d) > EPSILON && n < maxlter);
		

		if(n == maxlter || Double.isNaN(x)) {
			System.out.println("searching x from " + init_x + " failed.");
			return 0;
		}
		else {
			System.out.printf("searching x from " + init_x + ", solution is " + x + ".\n");
			return 1;
		}
	}
	
	
	public double f(double x) {
		return(x - Math.exp(-x));
	}
	
	public double df(double x) {
		return (1.0 + Math.exp(-x));
	}
	
	public static void main(String [] args) {
		int N = args.length;
		double[] init_x = new double[N];
		for(int i = 0; i < args.length; i++) {
			init_x[i] = Double.parseDouble(args[i]);
		}
		
		try{
			int count=0;;
			for(int i = 0; i < args.length; i++) {
				NonLinearEq X = new NonLinearEq(init_x[i]);
				if(X.solveNewton(init_x[i]) == 0) {
					count++;	
				}
				if(args.length == count) {
					throw new ArithmeticException();
				}
			}
		}catch(ArithmeticException e) {
			System.exit(1);
		}
		
	}
}
