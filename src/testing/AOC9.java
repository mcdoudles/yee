package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AOC9 {
	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 9.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
		str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 9.txt"));
		System.out.print("task2 = " + task2(str) + "\n");
	}

	private static List<Long> findSum(List<Long> longList, long targetSum, int lineOfTargetSum) {
		List<Long> resultList = new ArrayList<Long>();
		for (int i = 0; i < lineOfTargetSum; i++) {
			for (int j = i + 1; j < lineOfTargetSum; j++) {
				long sum = 0;
				for (int k = i; k < j; k++) {
					sum += longList.get(k);
					resultList.add(longList.get(k));
				}
				if (sum == targetSum) {
					return resultList;
				} else {
					resultList.clear();
				}
			}
		}
		return null;
	}
	
	private static List<Long> stringListToLongList(List<String> str) {
		List<Long> intList = new ArrayList<Long>();
		for (int i = 0; i < str.size(); i++) {
			intList.add(Long.parseLong(str.get(i)));
		}
		return intList;
	}

	private static long task2(List<String> str) {
		List<Long> longList = stringListToLongList(str);
		long task1Result = Long.parseLong(task1(str));
		int lineOfTask1Result = longList.indexOf(task1Result);
		List<Long> resultList = findSum(longList, task1Result, lineOfTask1Result);
		resultList.sort(Comparator.naturalOrder());
		return resultList.get(0) + resultList.get(resultList.size()-1);
	}

	private static String task1(List<String> str) {
		List<Long> longList = stringListToLongList(str);
		boolean success = false;
		for (int i = 24; i < str.size(); i++) {
			for (int j = 0; j < str.size() && j <= i; j++) {
				for (int k = 0; k < str.size() && k <= i; k++) {
					if (longList.get(j) + longList.get(k) == longList.get(i) && longList.get(j) != longList.get(k)) {
						success = true;
					}
				}
			}
			if (!success) {
				return str.get(i);
			}
			success = false;
		}
		return "fail";
	}
}
