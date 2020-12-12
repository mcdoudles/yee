package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AOC12_2 {

	private static Map<Integer, Integer> actualPosition = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> waypoint = new HashMap<Integer, Integer>();
	private static Map<Character, Integer> directionToDegrees = new HashMap<Character, Integer>();

	public static void main(String args[]) throws IOException {
		initializeHashMaps();
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 12.txt"));
		str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 12.txt"));
		System.out.print("task2 = " + task2(str) + "\n");
	}

	private static int task2(List<String> str) {
		for (int i = 0; i < str.size(); i++) {
			executeCommand(str.get(i).charAt(0), Integer.parseInt(str.get(i).substring(1)));
		}
		return Math.abs(actualPosition.get(270) - actualPosition.get(90))
				+ Math.abs(actualPosition.get(0) - actualPosition.get(180));
	}

	private static void changeDirection(char command, int value) {
		Map<Integer, Integer> newWaypoint = new HashMap<Integer, Integer>();
		if (command == 'R') {
			for (Integer i : waypoint.keySet()) {
				newWaypoint.put((i + value) % 360, waypoint.get(i));
			}
		} else if (command == 'L') {
			for (Integer i : waypoint.keySet()) {
				if (i - value >= 0) {
					newWaypoint.put((i - value) % 360, waypoint.get(i));
				} else {
					newWaypoint.put(((i - value) % 360) + 360, waypoint.get(i));
				}
			}
		} else {
			System.err.print("unknown command: " + command + "\n");
		}
		waypoint = newWaypoint;
	}

	private static void executeCommand(char command, int value) {
		switch (command) {
		case 'N', 'S', 'W', 'E':
			waypoint.put(directionToDegrees.get(command), waypoint.get(directionToDegrees.get(command)) + value);
			break;
		case 'L', 'R':
			changeDirection(command, value);
			break;
		case 'F':
			for (Integer i : actualPosition.keySet()) {
				actualPosition.put(i, actualPosition.get(i) + value * waypoint.get(i));
			}
			break;
		default:
			System.err.print("unknown command: " + command + "\n");
			break;
		}
	}

	private static void initializeHashMaps() {
		actualPosition.put(270, 0);
		actualPosition.put(90, 0);
		actualPosition.put(0, 0);
		actualPosition.put(180, 0);
		waypoint.put(270, 1);
		waypoint.put(90, 0);
		waypoint.put(0, 10);
		waypoint.put(180, 0);
		directionToDegrees.put('N', 270);
		directionToDegrees.put('S', 90);
		directionToDegrees.put('W', 180);
		directionToDegrees.put('E', 0);
	}

}
