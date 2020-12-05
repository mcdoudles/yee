package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class AOC5 {

	static List<Integer> occupiedSeats = new ArrayList<Integer>();

	public static int task2() {
		int target = 953 / 2; // 953 should be replaced
		List<Integer> seats = new ArrayList<Integer>();
		for (int i = 0; i < 953; i++) { // 953 should be replaced
			seats.add(i);
		}
		for (int i = 0; i < occupiedSeats.size(); i++) {
			seats.remove(occupiedSeats.get(i));
		}
		return seats.stream().min(Comparator.comparingInt(i -> Math.abs(i - target)))
				.orElseThrow(() -> new NoSuchElementException("No value present"));
	}

	public static int task1(List<String> str) {
		int result = 0;
		for (int i = 0; i < str.size(); i++) {
			int upperRow = 127;
			int lowerRow = 0;
			int upperColumn = 7;
			int lowerColumn = 0;
			int tempResult = 0; // hardcoded shiet
			char[] charArray = str.get(i).toCharArray();
			for (int j = 0; j < 7; j++) {
				if (charArray[j] == "F".charAt(0)) {
					upperRow = ((upperRow - lowerRow) / 2) + lowerRow;
				} else {
					lowerRow = ((upperRow - lowerRow) / 2 + 1) + lowerRow; // +1 rounding
				}
			}
			for (int j = 7; j < 10; j++) {
				if (charArray[j] == "L".charAt(0)) {
					upperColumn = ((upperColumn - lowerColumn) / 2) + lowerColumn;
				} else {
					lowerColumn = ((upperColumn - lowerColumn) / 2 + 1) + lowerColumn; // +1 rounding
				}
			}
			tempResult = upperRow * 8 + upperColumn;
			occupiedSeats.add(tempResult);
			if (tempResult > result) {
				result = tempResult;
			}
		}
		return result;
	}

	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 5.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
		System.out.print("task2 = " + task2() + "\n");
	}
}
