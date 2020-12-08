package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AOC8 {

	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 8.txt"));
		task1(str);
		System.out.print("task1 = " + accumulator + "\n");
		str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 8.txt"));
		System.out.print("task2 = " + task2(str) + "\n");
	}

	static int accumulator = 0;

	private static int task2(List<String> str) {
		for (int i = 0; i < str.size(); i++) {
			String[] split = str.get(i).split(" ");
			if (split[0].contains("jmp")) {
				str.set(i, "nop "+ split[1]);
				if (task1(str)) {
					return accumulator;
				} else {
					str.set(i, "jmp "+ split[1]);
				}
			}
			if (split[0].contains("nop")) {
				str.set(i, "jmp "+ split[1]);
				if (task1(str)) {
					return accumulator;
				} else {
					str.set(i, "nop "+ split[1]);
				}
			}
		}
		return 0;
	}

	public static void acc(String value) {
		if (value.charAt(0) == '+') {
			accumulator += Integer.parseInt(value.substring(1));
		} else {
			accumulator -= Integer.parseInt(value.substring(1));
		}
	}

	private static boolean task1(List<String> str) {
		accumulator = 0;
		boolean[] flag = new boolean[str.size()];
		int i = 0;
		while (i < str.size()) {
			if (!flag[i]) {
				flag[i] = true;
				String[] split = str.get(i).split(" ");
				switch (split[0]) {
				case "acc":
					acc(split[1]);
					i++;
					break;
				case "jmp":
					if (split[1].charAt(0) == '+') {
						i += Integer.parseInt(split[1].substring(1));
					} else {
						i -= Integer.parseInt(split[1].substring(1));
					}
					break;
				case "nop":
					i++;
					break;
				}
			} else {
				return false;
			}
		}
		return true;
	}
}
