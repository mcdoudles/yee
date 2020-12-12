package testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AOC12_1 {

	private static int currentDirection = 0;
	private static Map<Integer, Integer> distanceTowardsDirection = new HashMap<Integer, Integer>();
	private static Map<Character, Integer> directionToDegrees = new HashMap<Character, Integer>();

	public static void main(String args[]) throws IOException {
		initializeHashMaps();
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 12.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
	}

	private static void initializeHashMaps() {
		distanceTowardsDirection.put(270, 0);
		distanceTowardsDirection.put(90, 0);
		distanceTowardsDirection.put(0, 0);
		distanceTowardsDirection.put(180, 0);
		directionToDegrees.put('N', 270);
		directionToDegrees.put('S', 90);
		directionToDegrees.put('W', 180);
		directionToDegrees.put('E', 0);
	}
	
	private static void changeDirection(char command, int value) {
		if (command == 'R') {
			currentDirection += value;
			currentDirection = currentDirection % 360;
			if (currentDirection < 0) {
				currentDirection += 360;
			}
		} else if (command == 'L') {
			currentDirection -= value;
			currentDirection = currentDirection % 360;
			if (currentDirection < 0) {
				currentDirection += 360;
			}
		} else {
			System.err.print("unknown command: " + command + "\n");
		}
	}

	private static void executeCommand(char command, int value) {
		switch (command) {
		case 'N', 'S', 'W', 'E':
			distanceTowardsDirection.put(directionToDegrees.get(command),
					distanceTowardsDirection.get(directionToDegrees.get(command)) + value);
			break;
		case 'L', 'R':
			changeDirection(command, value);
			break;
		case 'F':
			distanceTowardsDirection.put(currentDirection, distanceTowardsDirection.get(currentDirection) + value);
			break;
		default:
			System.err.print("unknown command: " + command + "\n");
			break;
		}
	}

	private static int task1(List<String> str) {
		for (int i = 0; i < str.size(); i++) {
			executeCommand(str.get(i).charAt(0), Integer.parseInt(str.get(i).substring(1)));
		}
		return Math.abs(distanceTowardsDirection.get(270) - distanceTowardsDirection.get(90))
				+ Math.abs(distanceTowardsDirection.get(0) - distanceTowardsDirection.get(180));
	}
}
