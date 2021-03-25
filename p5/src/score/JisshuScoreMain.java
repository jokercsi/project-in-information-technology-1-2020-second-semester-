package score;

public class JisshuScoreMain {

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
}
