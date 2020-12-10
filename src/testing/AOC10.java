package testing;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AOC10 {
	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 10.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
		str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 10.txt"));
		System.out.print("task2 = " + task2(str) + " result: 2314037239808" + "\n");
	}

	private static List<Integer> stringListToSortedIntList(List<String> str) {
		List<Integer> intList = new ArrayList<Integer>();
		for (int i = 0; i < str.size(); i++) {
			intList.add(Integer.parseInt(str.get(i)));
		}
		intList.sort(Comparator.naturalOrder());
		return intList;
	}

	private static long getPermutations(int numberOfOnes) {
		if (numberOfOnes == 2) {
			return 2;
		} else if (numberOfOnes == 3) {
			return 4;
		} else if (numberOfOnes == 4) {
			return 7;
		} else {
			System.err.print("\nerror: " + numberOfOnes);
			return 0;
		}
	}

	private static BigInteger multiplyPermutations(List<Integer> intList) {
		BigInteger output = new BigInteger("1");
		for (int i = 0; i < intList.size(); i++) {
			output = output.multiply(BigInteger.valueOf(getPermutations(intList.get(i))));
		}
		return output;
	}

	private static BigInteger task2(List<String> str) {
		List<Integer> intList = stringListToSortedIntList(str);
		List<Integer> numberOfConsecutiveOnesList = new ArrayList<Integer>();
		int numberOfConsecutiveOnes = 0;
		for (int i = 0; i + 1 < intList.size(); i++) {
			System.out.print(intList.get(i) + "\n");
			if (intList.get(i + 1) - intList.get(i) == 1) {
				if (i == 0) {
					numberOfConsecutiveOnes++; // bad solution to count 0
				}
				numberOfConsecutiveOnes++;
			} else {
				if (numberOfConsecutiveOnes > 1) {
					numberOfConsecutiveOnesList.add(numberOfConsecutiveOnes);
				}
				numberOfConsecutiveOnes = 0;
			}
		}
		if (numberOfConsecutiveOnes > 1) {
			System.out.print(numberOfConsecutiveOnes + "\n");
			numberOfConsecutiveOnesList.add(numberOfConsecutiveOnes);
		} // bad solution to count last couple of 1s
		BigInteger a = new BigInteger("2314037239808");
		BigInteger b = new BigInteger("1157018619904");
		System.out.print(a.divide(b) + "\n");
		return multiplyPermutations(numberOfConsecutiveOnesList);
	}

	private static int task1(List<String> str) {
		List<Integer> intList = stringListToSortedIntList(str);
		int numberOfOneJoltDifferences = 0;
		int numberOfThreeJoltDifferences = 1; // has to be one by default.
		for (int i = 0; i < intList.size(); i++) {
			int switcher = 0;
			if (i == 0) {
				switcher = intList.get(i);
			} else {
				switcher = intList.get(i) - intList.get(i - 1);
			}
			switch (switcher) {
			case 1:
				numberOfOneJoltDifferences++;
				break;
			case 2:
				break;
			case 3:
				numberOfThreeJoltDifferences++;
				break;
			default:
				System.err.print("error");
				break;
			}
		}
		return numberOfOneJoltDifferences * numberOfThreeJoltDifferences;
	}
}
