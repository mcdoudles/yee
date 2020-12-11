package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AOC11 {

	private static char[][] task1ResultArray;
	private static char[][] task2ResultArray;

	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 11.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
		str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 11.txt"));
		System.out.print("task2 = " + task2(str) + "\n");
	}

	public static void arrayPrinter(char array[][]) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length + 1; j++) {
				System.out.print(array[i][j]);
			}
			System.out.print("\n");
		}
	}

	public static char[][] listToCharArray(List<String> str) {
		char[][] charArray = new char[str.size()][str.get(0).length()];
		for (int i = 0; i < str.size(); i++) {
			for (int j = 0; j < str.get(0).length(); j++) {
				charArray[i][j] = str.get(i).charAt(j);
			}
		}
		return charArray;
	}

	public static char[][] clone2DCharArray(char[][] charArray) {
		if (charArray == null)
			return null;
		char[][] result = new char[charArray.length][];
		for (int r = 0; r < charArray.length; r++) {
			result[r] = charArray[r].clone();
		}
		return result;
	}

	private static int occupiedSeats(char[][] charArray) {
		int result = 0;
		for (int i = 0; i < charArray.length; i++) {
			for (int j = 0; j < charArray[0].length; j++) {
				if (charArray[i][j] == '#') {
					result++;
				}
			}
		}
		return result;
	}

	private static int task1(List<String> str) {
		char[][] charArray = listToCharArray(str);
		recursionForTask1(charArray);
		return occupiedSeats(task1ResultArray);
	}

	public static void recursionForTask1(char[][] charArray) {
		char[][] newArray = clone2DCharArray(charArray);
		boolean didChange = false;
		for (int i = 0; i < charArray.length; i++) {
			for (int j = 0; j < charArray[0].length; j++) {
				char switcher = charArray[i][j];
				switch (switcher) {
				case 'L':
					if (adjacentIsOccupiedTask1(charArray, i, j) == 0) {
						newArray[i][j] = '#';
						didChange = true;
					}
					break;
				case '#':
					if (adjacentIsOccupiedTask1(charArray, i, j) >= 4) {
						newArray[i][j] = 'L';
						didChange = true;
					}
					break;
				case '.':
					break;
				default:
					System.err.print("error, character not found: " + switcher + "\n");
					break;
				}
			}
		}
		if (didChange) {
			recursionForTask1(newArray);
		} else {
			task1ResultArray = newArray.clone();
		}
	}

	private static int adjacentIsOccupiedTask1(char[][] charArray, int posX, int posY) {
		int result = 0;
		int i = -1;
		if (posX == 0) {
			i++;
		}
		while (i <= 1 && (posX + i) < charArray.length) {
			int j = -1;
			if (posY == 0) {
				j++;
			}
			while (j <= 1 && (posY + j) < charArray[0].length) {
				if (charArray[posX + i][posY + j] == '#')
					result++;
				j++;
			}
			i++;
		}
		if (charArray[posX][posY] == '#' && result > 0) {
			result--;
		}
		return result;
	}

	private static int task2(List<String> str) {
		char[][] charArray = listToCharArray(str);
		recursionForTask2(charArray);
		return occupiedSeats(task2ResultArray);
	}

	private static void recursionForTask2(char[][] charArray) {
		char[][] newArray = clone2DCharArray(charArray);
		boolean didChange = false;
		for (int i = 0; i < charArray.length; i++) {
			for (int j = 0; j < charArray[0].length; j++) {
				char switcher = charArray[i][j];
				switch (switcher) {
				case 'L':
					if (adjacentIsOccupiedTask2(charArray, i, j) == 0) {
						newArray[i][j] = '#';
						didChange = true;
					}
					break;
				case '#':
					if (adjacentIsOccupiedTask2(charArray, i, j) >= 5) {
						newArray[i][j] = 'L';
						didChange = true;
					}
					break;
				case '.':
					break;
				default:
					System.err.print("error, character not found: " + switcher + "\n");
					break;
				}
			}
		}
		if (didChange) {
			recursionForTask2(newArray);
		} else {
			task2ResultArray = newArray.clone();
		}
	}

	private static int adjacentIsOccupiedTask2(char[][] charArray, int posX, int posY) {
		int result = 0;
		int oldResult = 0;
		int distance = 1;
		boolean isItHashtag = false;
		if (charArray[posX][posY] == '#')
			isItHashtag = true;
		while (distance < charArray[0].length) {
			if (posX - distance >= 0 && posY - distance >= 0) {
				if (charArray[posX - distance][posY - distance] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX - distance][posY - distance] == '#') {
					result++;
				}
			}
			if (posY - distance >= 0) {
				if (charArray[posX][posY - distance] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX][posY - distance] == '#') {
					result++;
				}
			}
			if (posX - distance >= 0) {
				if (charArray[posX - distance][posY] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX - distance][posY] == '#') {
					result++;
				}
			}
			if (posX + distance < charArray.length && posY + distance < charArray[0].length) {
				if (charArray[posX + distance][posY + distance] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX + distance][posY + distance] == '#') {
					result++;
				}
			}
			if (posX + distance < charArray.length && posY - distance >= 0) {
				if (charArray[posX + distance][posY - distance] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX + distance][posY - distance] == '#') {
					result++;
				}
			}
			if (posX - distance >= 0 && posY + distance < charArray[0].length) {
				if (charArray[posX - distance][posY + distance] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX - distance][posY + distance] == '#') {
					result++;
				}
			}
			if (posX + distance < charArray.length) {
				if (charArray[posX + distance][posY] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX + distance][posY] == '#') {
					result++;
				}
			}
			if (posY + distance < charArray[0].length) {
				if (charArray[posX][posY + distance] == 'L' && isItHashtag) {
					return oldResult;
				}
				if (charArray[posX][posY + distance] == '#') {
					result++;
				}
			}
			oldResult = result;
			distance++;
		}
		return result;
	}
}
