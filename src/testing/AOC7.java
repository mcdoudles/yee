package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AOC7 {
	
	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 7.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
		str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 7.txt"));
		System.out.print("task2 = " + task2(str) + "\n");
	}

	static int task2Result = 0;

	private static void recursionMeme(List<String> str, String s, int numberOfBags) {
		for (int i = 0; i < str.size(); i++) {
			if (str.get(i).contains(s) && !str.get(i).contains("no other bags")) {
				String[] line = str.get(i).split(" contain ");
				String[] values = line[1].split("[,.]");
				for (int j = 0; j < values.length; j++) {
					if (values[j].charAt(0) == ' ') {
						values[j] = values[j].replaceFirst(" ", "");
					}
					values[j] = values[j].replaceAll(" bags*", "");
					task2Result = task2Result + numberOfBags * Character.getNumericValue(values[j].charAt(0));
					recursionMeme(str, values[j].substring(2) + " bags contain ", numberOfBags * Character.getNumericValue(values[j].charAt(0)));
				}
			}
		}
	}

	private static int task2(List<String> str) {
		int numberOfBags = 1;
		recursionMeme(str, "shiny gold bags contain ", numberOfBags);
		return task2Result;
	}

	private static int task1(List<String> str) {
		List<String> addedBags = new ArrayList<String>();
		addedBags.add("shiny gold");
		for (int i = 0; i < str.size(); i++) {
			for (int j = 0; j < addedBags.size(); j++) {
				if (str.get(i).contains(addedBags.get(j))) {
					addedBags.add(str.get(i).substring(0, str.get(i).indexOf(" bags contain")));
					str.remove(i);
					i = 0;
				}
			}
		}
		addedBags.remove("shiny gold");
		addedBags.remove("shiny gold");
		return addedBags.size();
	}
}
