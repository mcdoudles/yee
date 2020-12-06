package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AOC6 {
	
	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 6.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
		System.out.print("task2 = " + task2(str) + "\n");
	}

	private static int uniqueCharacters(String input) {
		return (int) input.chars().distinct().count();
	}

	private static List<Character> stringToUniqueCharacterList(String s) {
		Set<Character> charSet = new HashSet<Character>();
		List<Character> charList = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			charSet.add(s.charAt(i));
		}
		charList.addAll(charSet);
		return charList;
	}

	private static int threeUniqueCharacters(String input, int groupMemberCount) {
		int output = 0;
		List<Character> charList = stringToUniqueCharacterList(input);
		for (int i = 0; i < charList.size(); i++) {
			int counter = 0;
			for (int j = 0; j < input.length(); j++) {
				if (input.charAt(j) == charList.get(i)) {
					counter++;
				}
			}
			if (counter == groupMemberCount) {
				output++;
			}
		}
		return output;
	}

	private static int task2(List<String> str) {
		String entry = "";
		int result = 0;
		int groupMemberCount = 0;
		for (int i = 0; i < str.size(); i++) {
			if (!str.get(i).isEmpty()) {
				entry += str.get(i);
				groupMemberCount++;
			}
			if (str.get(i).isEmpty() || i + 1 == str.size()) {
				result = result + threeUniqueCharacters(entry, groupMemberCount);
				entry = "";
				groupMemberCount = 0;
			}
		}
		return result;
	}

	private static int task1(List<String> str) {
		String entry = "";
		int result = 0;
		for (int i = 0; i < str.size(); i++) {
			if (!str.get(i).isEmpty()) {
				entry += str.get(i);
			}
			if (str.get(i).isEmpty() || i + 1 == str.size()) {
				result = result + uniqueCharacters(entry);
				entry = "";
			}
		}
		return result;
	}
}
