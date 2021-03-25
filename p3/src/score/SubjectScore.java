//15817028 kim jibin

package score;

public class SubjectScore {

	private String studentName; 
	private int num; 
	private String[] subject; 
	private int[] point; 
	final static int MaxNum = 10; //the maximum number of subjects, static constant variable
	
	SubjectScore(){
		this("unknown", 5); //default constructor, defined with "this" keyword
		}
	
	SubjectScore(String s){ 
		this(s, 5);        //defined with "this" keyword
	}
	
	SubjectScore(int i){
		this("unknown", i);//defined with "this" keyword
	}
	
	SubjectScore(String s, int i){
		studentName = s;
		subject = new String [i]; //allocate memory space to subject[].
		point = new int [i];      //allocate memory space to point[].
		if (i > 0 && i < MaxNum) { 
			num = i; //If 0 < i <= MaxNum does hold, num should be i;
		}
		else {
			num = MaxNum; //If 0 < i <= MaxNum does not hold, num should be MaxNum;
		}
	}
	int getNum(){
		return num;		//getter(return the value of num)
	}
	String getStudentName(){
		return studentName;		//getter(return the value of studentName)
	}
	void setStudentName(String s){
		studentName = s;	//setter(set the value of studentName)
	}
	String getSubject(int i){
		return subject[i];		//getter(return the value of subject)
	}
	
	void setSubject(int i, String s){	
		subject[i] = s;		//setter(set the value of subject in array)
	}
	
	void setSubject(int i) {
		subject[i] ="unknown";
	}
	
	int getScore(int i){
		return point[i];	//getter(return the value of subject)
	}
	
	void setScore(int i, int j){
		point[i] = j;	//setter(set the value of score in array)
	
	}
	
	void setScore(int i) {
		point[i] = 60;
	}
	
	double calAverage(){
		double total = 0.0;		//initialize space for total score
		int cnt = 0;			//count the number of subject
			for(int i = 0; i < point.length-1; i++) {
				total += point[i];		//making total score from all subjects
				cnt++;			//count +1 by processing for loop
			}
			return total / cnt;		//calculate average score
	}
	
	double calAverage(int m) {
		double total = 0.0;		//initialize space for total score
		int cnt = 0;			//count the number of subject
		
		if(m<=9) {		//if m is smaller than value of num
			for(int i = 0; i < m; i++) {		// for loop until value of m
				total += point[i];		//making total score from all subjects
				cnt++;			//count +1 by processing for loop
			}
			
		}
		else if(m>9){		//if m is greater than value of num
			for(int i = 0; i < 9; i++) {		// for loop until value of num
				total += point[i];		//making total score from all subjects
				cnt++;			//count +1 by processing for loop
			}
		}
		
		return total / cnt;		//calculate average score
	}
	
	void setScores(int [] p) {
		if(p.length < point.length) { // if p[] is smaller than point[] 
			for(int i =0; i < p.length; i++) {
				point[i] = p[i];		//push p[]'s scores to point[]'s scores
			}
		}
		else if(p.length > point.length) { // if p[] is greater than point[] 
			for(int i =0; i < point.length; i++) {
				point[i] = point[i];		//only original point[]'s scores are used
			}
		}
	}
	
	void setSubjects(String [] s) {
		if(s.length < subject.length) {	// if s[] is smaller than subject[] 
			for(int i =0; i < s.length; i++) {
				subject[i] = s[i];		//push p[]'s names to point[]'s names
			}
		}
		else if(s.length > subject.length) {	// if s[] is greater than subject[] 
			for(int i =0; i < subject.length; i++) {
				subject[i] = subject[i];		//only original subject[]'s names are used
			}
		}
	}
	
	void copyScores(int [] p) {
		for(int i =0; i < p.length; i++) {
			p[i] = point[i];	// copy point[] to p[] 
		}
	}
	
	void copySubjects(String [] s) {
		for(int i =0; i < s.length; i++) {
			s[i] = subject[i];		// copy subject[] to s[]
		}		
	}
	
	int getMaxScore(){
		int max = point[0];		//initialize maximum is first element of point[]
		for (int i=0; i<point.length-1; i++) {
			if(point[i] > max) {		//if next element of point[] is bigger than previous element
				max = point[i];			//maximum is next element
			}
		}
		return max;
	}
	int getMinScore(){
		int min = point[0];		//initialize minimum is first element of point[]
		for (int i=0; i<point.length-1; i++) {
			if(point[i] < min) {		//if next element of point[] is smaller than previous element
				min = point[i];			//minimum is next element
			}
		}
		return min;
	}
	String getMaxSubject(){
		int max = point[0];		//initialize maximum is first element of point[]
		String max_sub = null;	//initialize space for the subject name for maximum score
		for (int i=0; i<point.length-1; i++) {
			if(point[i] > max) {	//if next element of point[] is bigger than previous element
				max = point[i];			//maximum score 
				max_sub = subject[i];	//the subject name for maximum score
			}
		}
		return max_sub;
	}
	String getMinSubject(){
		int min = point[0];		//initialize minimum is first element of point[]
		String min_sub = null;	//initialize space for the subject name for minimum score
		for (int i=0; i<point.length-1; i++) {	
			if(point[i] < min) {	//if next element of point[] is smaller than previous element
				min = point[i];		//minimum score
				min_sub = subject[i];	//the subject name for minimum score
			}
		}
		return min_sub;
	}
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		

	}
}

