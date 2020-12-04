package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AOC3 {

	public static long task2(List<String> str, int right, int down) {
		int result = 0;
		int spalte = right;
		for (int i = down; i < str.size(); i = i + down) {
			if (str.get(i).charAt(spalte) == "#".charAt(0)) {
				result++;
			}
			if (spalte + right > 30) {
				spalte = spalte - (30 - (right - 1));
			} else {
				spalte = spalte + right;
			}
		}
		return result;
	}

	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 3.txt"));
		System.out.print("task1 = " + task2(str, 3, 1));
		System.out.print("\ntask2 = "
				+ task2(str, 1, 1) * task2(str, 3, 1) * task2(str, 5, 1) * task2(str, 7, 1) * task2(str, 1, 2));
		System.out.print("\nindividual = " + task2(str, 1, 1) + " " + task2(str, 3, 1) + " " + task2(str, 5, 1) + " "
				+ task2(str, 7, 1) + " " + task2(str, 1, 2));

	}
}