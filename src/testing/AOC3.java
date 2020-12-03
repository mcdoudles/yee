package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AOC3 {

	public static int task1(List<String> str) {
		int result = 0;
		int spalte = 3;
		for (int i = 1; i < str.size(); i = i + 2) {
			if (str.get(i).charAt(spalte) == "#".charAt(0)) {
				result++;
			}
			if (spalte + 3 > 30) {
				spalte = spalte - 27;
			} else {
				spalte = spalte + 3;
			}
		}
		return result;
	}

	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input"));
		System.out.print(task1(str));
	}
}