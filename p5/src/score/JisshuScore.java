
// 15817028 Kim Jibin
package score;
import java.io.*;

public class JisshuScore extends SubjectScore{
	
	public static void main(String[] args) {
		if (args.length == 2) {
			String inputFileName = args[0];
			String outputFileName = args[1];
			switch (inputFileName) {
			case "TaroInput.txt":
				JisshuScore taro = new JisshuScore("Taro", 9);
				taro.readScores(inputFileName);
				taro.writeScores(outputFileName);
				break;
			case "HanakoInput.txt":
				JisshuScore hanako = new JisshuScore("Hanako", 6);
				hanako.readScores(inputFileName);
				hanako.writeScores(outputFileName);
				break;
			case "JiroInput.txt":
				JisshuScore jiro = new JisshuScore("Jiro", 5);
				jiro.readScores(inputFileName);
				jiro.writeScores(outputFileName);
				break;
			case "SaburoInput.txt":
				JisshuScore saburo = new JisshuScore("Saburo", 3);
				saburo.readScores(inputFileName);
				saburo.writeScores(outputFileName);
				break;
			default:
				System.err.println("The input file is not found.");
				break;
			}
		} else {
			System.err.println("java JisshuScore [input_file] [output_file].");
		}
	}
	
	void readScores(String a) {
		try { 
			FileReader fr = new FileReader(a);
			BufferedReader br = new BufferedReader(fr);
			
				
				String s ="";
				int count = 0; 
				while((s = br.readLine()) != null){	 // while all lines are end..	
					String[] token = s.split("\\s+"); 		
						for(int i = 0; i < token.length; i++){
							try {								
								if(count == 0) {
									System.out.println(token[i]);
									setSubject(i ,token[i]);
								}
								else if(count == 1) {
									setScore(i ,Integer.parseInt(token[i])); //string to int
								}
								else if(count == 2) {
									setReportScore(i ,Integer.parseInt(token[i])); // string to int
								}
							}
							catch(Exception e) {				
								System.out.println(e.getMessage());
							}
						}
					
					count++;
				}
			
		br.close();
		fr.close();
		}catch (Exception e) {
	      e.printStackTrace();
		}
		
}

	void writeScores(String b) {
		try{
			FileWriter fw = new FileWriter(b);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			String name = getStudentName();
			double average = calAverage(0);
			double report_average =calAverage(1);

			pw.printf("The average score of %s: %.2f\n", name, average);
			pw.printf("The average report score of %s: %.2f\n", name, report_average);
			pw.printf("The maximum score of %s: %d [%s]\n", name, getMaxScore(), getMaxSubject());		
			pw.printf("The minimum score of %s: %d [%s]\n", name, getMinScore(), getMinSubject());
			pw.close();
			
		bw.close();
		fw.close();
		}catch(Exception e) {
		      System.out.println(e.getMessage());
		}
	}
	
	//private int [] report = new int[9];		//9 space for report array 
	private int [] report;

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
		report = new int[i];

	}

	int getReportScore(int i) throws Exception {
		if(i< 0 || i >= report.length) {		//if i is bigger or smaller than point[]
			throw new Exception(i + " is a wrong index");		//message for exception
		}
		return report[i];
	}
	
	void setReportScore(int i, int j) throws Exception{	
		if(i< 0 || i > report.length) {		//if i is bigger or smaller than point[]
			throw new Exception(i + " is a wrong index");		//message for exception of index range
		}
		else if (j < 0 || j > 100) {		//if j is bigger than 100 or j is smaller than 0
			report[i] = 0;
			throw new Exception(j + " is a wrong score");		//message for exception of score range
		}
		else {			
			report[i] = j;
		}

	}
	
	double calAverage(int i) {
		double total = 0.0;		//initialize space for total score
		int cnt = 0;
		if(i == 0) {
			return super.calAverage(); //call super class calAverge method, it is not child class
		}
		else {
			for(int j = 0; j < report.length; j++) {
				if(report[j] >= 0 || report[j]<= 100) {		//exception occur in wrong score. It is not counted by average.
					total += report[j];	
				}
				cnt++;
			}
			return total / cnt;		//expected out was not changed by the number of scores. it was fixed with 9 scores
		}
		
	}
	
}

class SubjectScore {

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
		if (i > 0 || i < MaxNum) { 
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
	String getSubject(int i) throws Exception{
		if(i< 0 || i >= subject.length) {		//if i is bigger or smaller than subject[]
			throw new Exception(i + " is a wrong index");		//message for exception
		}
		return subject[i];		//getter(return the value of subject)
	}
	
	void setSubject(int i, String s) throws Exception{	
		if(i< 0 || i >= subject.length) {		//if i is bigger or smaller than subject[]
			throw new Exception(i + " is a wrong index");		//message for exception
		}
		subject[i] = s;		//setter(set the value of subject in array)
	}
	
	void setSubject(int i) throws Exception{
		if(i< 0 || i >= subject.length) {		//if i is bigger or smaller than subject[]
			throw new Exception(i + " is a wrong index");		//message for exception
		}
		subject[i] ="unknown";
	}
	
	int getScore(int i) throws Exception{
		if(i< 0 || i >= point.length) {		//if i is bigger or smaller than point[]
			throw new Exception(i + " is a wrong index");		//message for exception
		}
		return point[i];	//getter(return the value of subject)
	}
	
	void setScore(int i, int j) throws Exception{
		if(i< 0 || i >= point.length) {		//if i is bigger or smaller than point[]
			throw new Exception(i + " is a wrong index");		//message for exception of index range
		}
		else if(j < 0 || j > 100) {		//if j is bigger than 100 or j is smaller than 0
			point[i] = 0;
			throw new Exception(j + " is a wrong score");		//message for exception of score range
			
		}
		else {			
			point[i] = j;	//setter(set the value of score in array)
		}

	}
	
	void setScore(int i) throws Exception {
		if(i< 0 || i >= point.length) {		//if i is bigger or smaller than point[]
			throw new Exception(i + " is a wrong index");		//message for exception
		}
		point[i] = 60;
	}
	
	double calAverage(){
		double total = 0.0;		//initialize space for total score
		int cnt = 0;			//count the number of subject
			for(int i = 0; i < point.length; i++) {
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
		for (int i=0; i<point.length; i++) {
			if(point[i] > max) {		//if next element of point[] is bigger than previous element
				max = point[i];			//maximum is next element
			}
		}
		return max;
	}
	int getMinScore(){
		int min = point[0];		//initialize minimum is first element of point[]
		for (int i=0; i<point.length; i++) {
			if(point[i] < min) {		//if next element of point[] is smaller than previous element
				min = point[i];			//minimum is next element
			}
		}
		return min;
	}
	String getMaxSubject(){
		int max = point[0];		//initialize maximum is first element of point[]
		String max_sub = subject[0];	//initialize space for the subject name for maximum score
		for (int i=0; i<point.length; i++) {
			if(point[i] > max) {	//if next element of point[] is bigger than previous element
				max = point[i];			//maximum score 
				max_sub = subject[i];	//the subject name for maximum score
			}
		}
		return max_sub;
	}
	String getMinSubject(){
		int min = point[0];		//initialize minimum is first element of point[]
		String min_sub = subject[0];	//initialize space for the subject name for minimum score
		for (int i=0; i<point.length; i++) {	
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

