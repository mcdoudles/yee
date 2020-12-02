package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AOC2 {

	static char minus = "-".charAt(0);
	static char doppelp = ":".charAt(0);

	static int task1() throws IOException {
		int result = 0;
		List<String> str = Files.readAllLines(Paths.get("C:\\Java memes\\inputday2.txt"));
		// each line
		for (int i = 0; i < str.size(); i++) {
			char[] eachSymbol = str.get(i).toCharArray();
			Pattern pattern = null;
			int lowerLimit = 0;
			int upperLimit = 0;
			// each character
			for (int j = 0; j < eachSymbol.length; j++) {
				if (eachSymbol[j] == minus) {
					lowerLimit = Character.getNumericValue(eachSymbol[0]);
					if (j == 2) {
						lowerLimit = lowerLimit * 10 + Character.getNumericValue(eachSymbol[j - 1]);
					}
					upperLimit = Character.getNumericValue(eachSymbol[j + 1]);
					if (Character.getNumericValue(eachSymbol[j + 2]) >= 0) {
						upperLimit = upperLimit * 10 + Character.getNumericValue(eachSymbol[j + 2]);
					}
				}
				if (eachSymbol[j] == doppelp) {
					pattern = Pattern.compile(Character.toString(eachSymbol[j - 1]));
				}
			}
			Matcher matcher = pattern.matcher(str.get(i));
			int counter = -1;
			while (matcher.find()) {
				counter++;
			}
			if (lowerLimit <= counter && counter <= upperLimit) {
				result++;
			}
		}

		return result;
	}

	static int task2() throws IOException {
		int result = 0;
		List<String> str = Files.readAllLines(Paths.get("C:\\Java memes\\inputday2.txt"));
		// each line
		for (int i = 0; i < str.size(); i++) {
			char[] eachSymbol = str.get(i).toCharArray();
			char symbol = 0;
			int lowerDigit = 0;
			int upperDigit = 0;
			// each character
			for (int j = 0; j < eachSymbol.length; j++) {
				if (eachSymbol[j] == minus) {
					lowerDigit = Character.getNumericValue(eachSymbol[0]);
					if (j == 2) {
						lowerDigit = lowerDigit * 10 + Character.getNumericValue(eachSymbol[j - 1]);
					}
					upperDigit = Character.getNumericValue(eachSymbol[j + 1]);
					if (Character.getNumericValue(eachSymbol[j + 2]) >= 0) {
						upperDigit = upperDigit * 10 + Character.getNumericValue(eachSymbol[j + 2]);
					}
				}
				if (eachSymbol[j] == doppelp) {
					symbol = eachSymbol[j - 1];
				}
			}
			String password = str.get(i).split(": ")[1];
			if (password.charAt(lowerDigit - 1) == symbol ^ password.charAt(upperDigit - 1) == symbol) {
				result++;
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		System.out.print(task1());
		System.out.print("\n");
		System.out.print(task2());
	}
}