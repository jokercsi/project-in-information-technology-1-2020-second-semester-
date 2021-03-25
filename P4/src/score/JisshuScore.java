// 15817028 Kim Jibin
package score;

public class JisshuScore extends SubjectScore{
	private int [] report = new int[9];		//9 space for report array 


	//JisshuScore use same constructor with its parent class
	JisshuScore(){
		super();		//default constructor
	}
	
	JisshuScore(String s){
		super(s);
	}
	
	JisshuScore(int i){
		super(i);
	}
	
	JisshuScore(String s, int i){
		super(s, i);

	}
	
	
	
	

	int getReportScore(int i) throws Exception {
		if(i< 0 || i >= report.length) {		//if i is bigger or smaller than point[]
			throw new Exception(i + " is a wrong index");		//message for exception
		}
		return report[i];
	}
	
	void setReportScore(int i, int j) throws Exception{	
		if(i< 0 || i >= report.length) {		//if i is bigger or smaller than point[]
			throw new Exception(i + " is a wrong index");		//message for exception of index range
		}
		else if (j < 0 || j > 100) {		//if j is bigger than 100 or j is smaller than 0
			throw new Exception(j + " is a wrong score");		//message for exception of score range
		}
		report[i] = j;
	}
	
	double calAverage(int i) {
		double total = 0.0;		//initialize space for total score
		if(i == 0) {
			return super.calAverage(); //call super class calAverge method, it is not child class
		}
		else {
			for(int j = 0; j < report.length; j++) {
				if(report[j] > 0 || report[j]< 100) {		//exception occur in wrong score. It is not counted by average.
					total += report[j];	
				}
			}
			return total / 9;		//expected out was not changed by the number of scores. it was fixed with 9 scores
		}
		
	}
	
}
