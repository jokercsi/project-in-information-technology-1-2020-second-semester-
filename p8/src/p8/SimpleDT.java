//15817028 Kim Jibin

package p8;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class TreeNode {
	private String label; 
	private String elabel;
	private ArrayList<TreeNode> children;

	TreeNode(String s) {
		label = s;
		children = new ArrayList<TreeNode>();
	}

	void setLabel(String s) {
		label = s;
	}

	void setElabel(String s) {
		elabel = s;
	}

	String getLabel() {
		return label;
	}

	String getElabel() {
		return elabel;
	}

	int getChildrenNum() {
		return children.size();
	}

	TreeNode getChild(int i) {
		if (0 <= i && i < children.size())
			return children.get(i);
		else
			return null;
	}

	void addChild(TreeNode n) {
		children.add(n);
	}
}

class DataList {
	private LinkedList<ArrayList<String>> dataList;
	public int length;

	DataList() {
		dataList = new LinkedList<ArrayList<String>>();
	}

	void add(String line) {
		String[] token = line.split("\\s+");
		ArrayList<String> lineData = new ArrayList<String>(Arrays.asList(token));
		add(lineData);
	}

	void add(ArrayList<String> d) {
		dataList.add(d);
	}

	ArrayList<String> get(int i) {
		return dataList.get(i);
	}

	int size() {
		return dataList.size();
	}
}

class AttrList {
	private ArrayList<String> attrList;

	AttrList() {
		attrList = new ArrayList<String>();
	}

	void setAttributes(String s) {
		String[] token = s.split("\\s");
		for (int i = 0; i < token.length; i++)
			add(token[i]);
	}

	void add(String s) {
		attrList.add(s);
	}

	String get(int i) {
		return attrList.get(i);
	}

	int size() {
		return attrList.size();
	}

	int indexOf(String s) {
		return attrList.indexOf(s);
	}
}

public class SimpleDT {
	private String fileName;
	private AttrList attrList;
	private DataList dataList;
	private TreeNode rootNode;

	SimpleDT(String s) {
		fileName = s;
		attrList = new AttrList();
		dataList = new DataList();
	}

	//read method.
	void readFile() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line = in.readLine();
			attrList.setAttributes(line);
			while ((line = in.readLine()) != null) {
				dataList.add(line);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		System.out.printf("read %d records\n", dataList.size());
	}

	DataList[] divideData(DataList dlist, int col, int numOfDivisions) {
		DataList[] dividedDataLists = new DataList[numOfDivisions];
		for (int i = 0; i < numOfDivisions; i++)
			dividedDataLists[i] = new DataList();
		HashMap<String, Integer> attrValueIndexMap = new HashMap<String, Integer>();
		int index;

		for (int i = 0; i < dlist.size(); i++) {
			ArrayList<String> data = dlist.get(i);
			String value = data.get(col);
			if (attrValueIndexMap.containsKey(value))
				index = attrValueIndexMap.get(value);
			else {
				index = attrValueIndexMap.size();
				attrValueIndexMap.put(value, index);
			}
			dividedDataLists[index].add(data);
		}
		return dividedDataLists;
	}

	AttrList deleteAttribute(AttrList orgAttrList, int col) {
		AttrList newAttrList = new AttrList();
		for (int i = 0; i < orgAttrList.size(); i++) {
			if (i == col)
				continue;
			newAttrList.add(orgAttrList.get(i));
		}
		return newAttrList;
	}

	// self information average
	double log2(double d) {
		/* complete this method */
		return Math.log10(d)/Math.log10(2.0);
	}


	
	double subInfo(DataList dlist, int col, String attrValue1) {
		double info = 0.0;
		/* complete this method */		
		// col = 0;
		// dlist = 15 x 5 (examples)
		// attrValue1 = rain, overcast, sunny
		
		// total = denominator
		int total = 0;
		// space for "play","not play"'
		HashMap<String,Integer> a = new HashMap<>();	
		
		//number of "play"
		int b = 1;
		for(int i = 0; i < dlist.size(); i++) {
			if(attrValue1.equals(dlist.get(i).get(col))) {
				
				//total = sunny(5) ,overcast(4), rain(5) 
				total++;
				
				// 4 = Class(play, not play)
				if(dlist.get(i).get(4).equals("play")){	
					a.put("play", b);
					b++;
				}				
			}
		}

		
		//number of "not_play"
		int c = 0;
		if(a.containsKey("play")) {			
			for(int i = 0; i < dlist.size(); i++) {
					c = total - a.get("play");
					a.put("not_play", c);
			}
		}

		
//		else {
//			//attrValue = sunny, col = 1 (temperature)
//			for(int i = 0; i < dlist.size(); i++) {
//				//System.out.println(attrValue1);
//				if(attrValue1.equals(dlist.get(i).get(1))) {
//					total++;
//					// 4 = Class(play, not play)
//					if(dlist.get(i).get(2).equals("normal")){	
//						a.put("play", b);
//						b++;
//					}	
//				}
//			}
//			if(a.containsKey("play")) {			
//				for(int i = 0; i < dlist.size(); i++) {
//						c = total - a.get("play");
//						a.put("not_play", c);
//				}
//			}
//		}
		
		
		
		
		
		
		
		
		
		
		
		for(String key: a.keySet()) {
			// if denominator = 0, it make NaN
			//System.out.println(a.get(key));		
			if(a.get(key) != 0)
				info -= ((double)a.get(key)/total) * log2((double)a.get(key)/total);
		}
		
		return info;
	}

	double postInfo(DataList dlist, int col, HashMap<String, Integer> attrValueFreqMap) {
		double info = 0.0;
		/* complete this method */
		
		for(String key: attrValueFreqMap.keySet()) {
			//System.out.println(attrValueFreqMap.get(key));
			//System.out.println(subInfo(dlist,col,key));
			info += (double)attrValueFreqMap.get(key)/dlist.size()*subInfo(dlist,col,key);
		}
		return info;
	}

	double preInfo(int numOfData, HashMap<String, Integer> cDist) {
		double info = 0.0;
		/* complete this method */
		//numOfData = 14
		// save key and value in Set entries
		Set<Map.Entry<String, Integer>> entries = cDist.entrySet();
		

		for (Map.Entry<String, Integer> entry : entries) {
			// int to double(casting)
			// have to change Type 
		  info -= ((double)entry.getValue() / numOfData) * (Math.log((double)entry.getValue() / numOfData)) / Math.log(2.0);
		}

		
		return info;
	}

	String findMajority(HashMap<String, Integer> attrValueFreqMap) {
		int maxFreq = 0;
		String maxKey = null;
		for (String key : attrValueFreqMap.keySet()) {
			if (maxFreq < attrValueFreqMap.get(key)) {
				maxFreq = attrValueFreqMap.get(key);
				maxKey = key;
			}
		}
		return maxKey;
	}
	
	// getAttrValueFreqMapList() = count the frequency of each attribute value.
	// alist = list of attributes
	// dlist = list of data 
	ArrayList<HashMap<String, Integer>> getAttrValueFreqMapList(DataList dlist, AttrList alist) {
		ArrayList<HashMap<String, Integer>> attrValueFreqMap = new ArrayList<HashMap<String, Integer>>();
		/* complete this method */
		//키랑 벨로룰 정해주면 됨.........
		// alist = attribute(Outlook, temperature, Humidity, Windy, Class)
		// dlist = attribute value(rain, cool, normal, false, play.....so on)
		
		if(alist.size() == 5) {	
			for(int i = 0; i < alist.size(); i++) {
				HashMap<String, Integer> Attr = new HashMap<>();
				for(int j = 0; j < dlist.size(); j++) {
					if(Attr.containsKey(dlist.get(j).get(i))){
						//System.out.println(dlist.get(j).get(i));
						Attr.put(dlist.get(j).get(i), Attr.get(dlist.get(j).get(i))+1); //count increment of words
					}
					else {
						//System.out.println(dlist.get(j).get(i));
						Attr.put(dlist.get(j).get(i), 1);
					}
				}
				attrValueFreqMap.add(i, Attr);
			}
		}
		
		else if(alist.size() == 4) {	
			//System.out.println(alist.get(0));
			for(int i = 0; i < alist.size()+1; i++) {
				HashMap<String, Integer> Attr = new HashMap<>();
				for(int j = 0; j < dlist.size(); j++) {
					if(Attr.containsKey(dlist.get(j).get(i))){
						//System.out.println(dlist.get(j).get(i));
						Attr.put(dlist.get(j).get(i), Attr.get(dlist.get(j).get(i))+1); //count increment of words
					}
					else {
						//System.out.println(dlist.get(j).get(i));
						Attr.put(dlist.get(j).get(i), 1);
					}
				}
				//System.out.println(dlist.get(j).get(0));
				attrValueFreqMap.add(i, Attr);
			}
		}
			
		
		//System.out.println(attrValueFreqMap.get(0));
		
		
		
		
//		HashMap<String, Integer> Outlook = new HashMap<>();
//		HashMap<String, Integer> Temperature = new HashMap<>();
//		HashMap<String, Integer> Humidity = new HashMap<>();
//		HashMap<String, Integer> Windy = new HashMap<>();
//		HashMap<String, Integer> Class = new HashMap<>();
//		
//		for(int i = 0; i < dlist.size(); i++) {
//			int j = 0;
//			// 0 ~ 5
//			for(String attrValue: dlist.get(i)) {	
//				if(j == 0) {					
//					if(Outlook.containsKey(attrValue)){					
//						Outlook.put(attrValue, Outlook.get(attrValue)+1); //count increment of words
//					}
//					else
//						Outlook.put(attrValue, 1);
//					j++;
//				}
//				else if(j == 1) {	
//					if(Temperature.containsKey(attrValue)){					
//						Temperature.put(attrValue, Temperature.get(attrValue)+1); //count increment of words
//					}
//					else
//						Temperature.put(attrValue, 1);
//					j++;
//				}
//				else if(j == 2) {
//					if(Humidity.containsKey(attrValue)){					
//						Humidity.put(attrValue, Humidity.get(attrValue)+1); //count increment of words
//					}
//					else
//						Humidity.put(attrValue, 1);
//					j++;
//				}
//				else if(j == 3) {
//					if(Windy.containsKey(attrValue)){					
//						Windy.put(attrValue, Windy.get(attrValue)+1); //count increment of words
//					}
//					else
//						Windy.put(attrValue, 1);
//					j++;
//				}
//				else if(j == 4) {
//					if(Class.containsKey(attrValue)){					
//						Class.put(attrValue, Class.get(attrValue)+1); //count increment of words
//					}
//					else
//						Class.put(attrValue, 1);
//					j++;	
//				}
//			}
//		}
//		//System.out.println(dlistmap);
//		//attrValueFreqMap.add(dlistmap);
//		attrValueFreqMap.add(0, Outlook);
//		attrValueFreqMap.add(1, Temperature);
//		attrValueFreqMap.add(2, Humidity);
//		attrValueFreqMap.add(3, Windy);
//		attrValueFreqMap.add(4, Class);
//
//		System.out.println(attrValueFreqMap.get(1));
		
		
		
		
		
		
		
		
		
		return attrValueFreqMap;
	}
	
	// HashMap in ArrayList
	// key = attribute value.
	// attrValueFreqMapList = name of Tree
	TreeNode makeTree(DataList dlist, AttrList alist) {
		ArrayList<HashMap<String, Integer>> attrValueFreqMapList = getAttrValueFreqMapList(dlist, alist);
		DataList[] dividedDataLists;
		AttrList newAttrList;
		String clsName = null;
		double maxGain = 0.0;
		int maxId = -1;
		int col;
		// alist = attrvalue, dlist = examples
		// attrValueFreqMapList = examples in {outlook, Temperature, Humidity, Windy, Class}
		
		if (alist.size() == 1) {
			clsName = findMajority(attrValueFreqMapList.get(0));
			return new TreeNode(clsName);
		}

		if (attrValueFreqMapList.get(alist.size() - 1).size() == 1) {
			for (String key : attrValueFreqMapList.get(alist.size() - 1).keySet())
				clsName = key;
			return new TreeNode(clsName);
		}

		double preInfo = preInfo(dlist.size(), attrValueFreqMapList.get(alist.size() - 1));
		System.out.printf("  preInfo: %.3f\n", preInfo);

		for (int i = 0; i < alist.size() - 1; i++) {
			//System.out.println(attrValueFreqMapList.get(i));
			double postInfo = postInfo(dlist, attrList.indexOf(alist.get(i)), attrValueFreqMapList.get(i));
			double gain = preInfo - postInfo;
			System.out.printf("    gain for %s: %.3f\n", alist.get(i), gain);
			if (maxGain < gain) {
				maxGain = gain;
				maxId = i;
			}
		}

		if (maxGain == 0.0) {
			clsName = findMajority(attrValueFreqMapList.get(alist.size() - 1));
			return new TreeNode(clsName);
		}

		col = attrList.indexOf(alist.get(maxId));
		dividedDataLists = divideData(dlist, col, attrValueFreqMapList.get(maxId).size());
		newAttrList = deleteAttribute(alist, maxId);
		TreeNode newNode = new TreeNode(alist.get(maxId));

		for (int i = 0; i < dividedDataLists.length; i++) {
			if (dividedDataLists[i].size() != 0) {
				TreeNode tmp = makeTree(dividedDataLists[i], newAttrList);
				tmp.setElabel(dividedDataLists[i].get(0).get(col));
				newNode.addChild(tmp);
			}
		}
		return newNode;
	}

	// make a new Tree
	void buildTree() {
		rootNode = makeTree(dataList, attrList);
	}

	void printNode(TreeNode node, int level) {
		/*  complete this method */
		//for loop
		System.out.printf("PreInfo: \n");
		System.out.printf("gain for ");
		
		return;
	}

	void printTree() {
		printNode(rootNode, 0);
		
	}
	
	//getAttrValueFreqMapList = ArrayList<HashMap>
	//AttrList = (class)
	//DataList = (class)
	//Exercise 8-2(1)
	public void testGetAttrValueFreqMapList() {
		//attrValuefreqMapList
		ArrayList<HashMap<String, Integer>> attrValueFreqMapList = getAttrValueFreqMapList(dataList, attrList);
		
		
		
		for (int i = 0; i < attrValueFreqMapList.size(); i++) { //until all elements of attrValueFreqMapList(ArrayList) 
			// attrName = Attribute
			String attrName = attrList.get(i); 		// attrList(AttrList class) = ArrayList
			HashMap<String, Integer> attrValueFreqMap = attrValueFreqMapList.get(i); 
			for (Entry<String, Integer> attrValueFreqEntry : attrValueFreqMap.entrySet()) {
				// attrValue = key of HashMap = HashMap for ArrayList's element(Attribute Value)
				String attrValue = attrValueFreqEntry.getKey();		
				// freq = value of Hash Map
				int freq = attrValueFreqEntry.getValue();		
				System.out.printf("%s %s %d\n", attrName, attrValue, freq); 
			}
		}
	}
	
	public void testPreInfo() {
		ArrayList<HashMap<String, Integer>> attrValueFreqMapList = getAttrValueFreqMapList(dataList, attrList);
		double preInfo = preInfo(dataList.size(), attrValueFreqMapList.get(attrList.size() - 1));
		System.out.printf("Root preInfo %.3f\n", preInfo);
	}

	public void testSubInfo() {
		ArrayList<HashMap<String, Integer>> attrValueFreqMapList = getAttrValueFreqMapList(dataList, attrList);
		HashMap<String, Integer> attrValueFreqMap = attrValueFreqMapList.get(0);
		for (String attrValue : attrValueFreqMap.keySet()) {
			double subInfo = subInfo(dataList, 0, attrValue);
			System.out.printf("%s %s subInfo %.3f\n", attrList.get(0), attrValue, subInfo);
		}
	}

	public void testPostInfo() {
		ArrayList<HashMap<String, Integer>> attrValueFreqMapList = getAttrValueFreqMapList(dataList, attrList);
		for (int i = 0; i < attrValueFreqMapList.size() - 1; i++) { //
			double postInfo = postInfo(dataList, i, attrValueFreqMapList.get(i));
			System.out.printf("%s postInfo %.3f\n", attrList.get(i), postInfo);
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("java p8.SimpleDT [testGetAttrValueFreqMapList|testPreInfo|testSubInfo|testPostInfo|testPrintTree] [input file path]");
			System.exit(-1);
		}
		String testType = args[0]; 
		String inputFilePath = args[1];
		SimpleDT dt = new SimpleDT(inputFilePath);
		dt.readFile();

		switch (testType) {
		//Exercise 8-2(1)
		case "testGetAttrValueFreqMapList":
			dt.testGetAttrValueFreqMapList();
			break;
		// success	
			
		//Exercise 8-2(2)
		case "testPreInfo":
			dt.testPreInfo();
			break;

			
		case "testSubInfo":
			dt.testSubInfo();
			break;
			
		
		case "testPostInfo":
			dt.testPostInfo();
			break;
			
		case "testPrintTree": 
			dt.buildTree();  //exercise
			
			dt.printTree();  //exercise
			break;
		}
	}
}
