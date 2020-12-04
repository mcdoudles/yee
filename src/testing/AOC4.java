package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AOC4 {
	public static int task1(List<String> str) {
		return clearedList(str).size();
	}

	public static int task2(List<String> str) {
		str=clearedList(str);
		int result = 0;
		for (int i = 0; i < str.size(); i++) {
			result++;
			String[] arrayOfEntries = str.get(i).split(" ");
			loopA: for (int j = 1; j < arrayOfEntries.length; j++) {
				String switcher = arrayOfEntries[j].substring(0, 3);
				switch (switcher) {
				case "byr":
					if (!isCorrectByr(arrayOfEntries[j])) {
						result--;
						break loopA;
					} else {
						break;
					}
				case "iyr":
					if (!isCorrectIyr(arrayOfEntries[j])) {
						result--;
						break loopA;
					} else {
						break;
					}
				case "eyr":
					if (!isCorrectEyr(arrayOfEntries[j])) {
						result--;
						break loopA;
					} else {
						break;
					}
				case "hgt":
					if (!isCorrectHgt(arrayOfEntries[j])) {
						result--;
						break loopA;
					} else {
						break;
					}
				case "hcl":
					if (!isCorrectHcl(arrayOfEntries[j])) {
						result--;
						break loopA;
					} else {
						break;
					}
				case "ecl":
					if (!isCorrectEcl(arrayOfEntries[j])) {
						result--;
						break loopA;
					} else {
						break;
					}
				case "pid":
					if (!isCorrectPid(arrayOfEntries[j])) {
						result--;
						break loopA;
					} else {
						break;
					}
				}
			}
		}
		return result;
	}

	public static List<String> getEntries(List<String> str) {
		List<String> newStr = new ArrayList<String>();
		for (int i = 0; i < str.size(); i++) {
			if (i + 1 == str.size() || str.get(i).isEmpty()) {
				if (str.size() == i + 1) {
					i++;
				}
				String temp = "";
				for (int j = 1; i >= j && !str.get(i - j).isEmpty(); j++) {
					temp += " " + str.get(i - j);
				}
				newStr.add(temp);
			}
		}
		return newStr;
	}

	public static boolean isCorrectByr(String s) {
		return Pattern.matches("byr:(19[2-9][0-9]|200[0-2])", s);
	}

	public static boolean isCorrectIyr(String s) {
		return Pattern.matches("iyr:(201[0-9]|2020)", s);
	}

	public static boolean isCorrectEyr(String s) {
		return Pattern.matches("eyr:(202[0-9]|2030)", s);
	}

	public static boolean isCorrectHgt(String s) {
		return Pattern.matches("hgt:(1[5-8][0-9]|19[0-3])cm", s) || Pattern.matches("hgt:(59|6[0-9]|7[0-6])in", s);
	}

	public static boolean isCorrectHcl(String s) {
		return Pattern.matches("hcl:#[0-9a-f]{6}", s);
	}

	public static boolean isCorrectEcl(String s) {
		return Pattern.matches("ecl:(amb|blu|brn|gry|grn|hzl|oth)", s);
	}

	public static boolean isCorrectPid(String s) {
		return Pattern.matches("pid:[0-9]{9}", s);
	}

	public static boolean checkerMeme(String s) {
		return s.contains("byr") && s.contains("iyr") && s.contains("eyr") && s.contains("hgt") && s.contains("hcl")
				&& s.contains("ecl") && s.contains("pid");
	}
	
	public static List<String> clearedList(List<String> str){
		List<String> newStr = new ArrayList<String>();
		for(int i = 0; i<str.size();i++) {
			if(checkerMeme(str.get(i))){
				newStr.add(str.get(i));
			}
		}
		return newStr;
	}
	
	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 4.txt"));
		str = getEntries(str);
		System.out.print("task1 = " + task1(str) + "\n");
		System.out.print("task2 = " + task2(str) + "\n");
	}
}
