package testing;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static java.util.Arrays.stream;

public class AOC13 {
	public static void main(String args[]) throws IOException {
		List<String> str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 13.txt"));
		System.out.print("task1 = " + task1(str) + "\n");
		str = Files.readAllLines(Paths.get("C:\\Git\\testing\\input\\input day 13.txt"));
		System.out.print("task2 = " + task2(str) + "\n");
	}

	private static List<Integer> getBusIDs(List<String> str) {
		List<Integer> busIDs = new ArrayList<Integer>();
		String[] splittedString = str.get(1).split(",");
		for (int i = 0; i < splittedString.length; i++) {
			if (splittedString[i].matches("[1-9]*")) {
				busIDs.add(Integer.parseInt(splittedString[i]));
			}
		}
		return busIDs;
	}

	private static int task1(List<String> str) {
		int result = 1000000000;
		int busID = 0;
		int earliestTimestamp = Integer.parseInt(str.get(0));
		List<Integer> busIDs = getBusIDs(str);
		HashMap<Integer, Integer> busWaitingTime = new HashMap<Integer, Integer>();
		for (int i = 0; i < busIDs.size(); i++) {
			int lastDeparture = (earliestTimestamp / busIDs.get(i)) * busIDs.get(i);
			while (lastDeparture < earliestTimestamp) {
				lastDeparture += busIDs.get(i);
			}
			busWaitingTime.put(busIDs.get(i), lastDeparture - earliestTimestamp);
			if (lastDeparture - earliestTimestamp < result) {
				result = lastDeparture - earliestTimestamp;
				busID = busIDs.get(i);
			}
		}
		return result * busID;
	}

	private static HashMap<Integer, Integer> getBusMap(List<String> str) {
		HashMap<Integer, Integer> busIDs = new HashMap<Integer, Integer>();
		String[] splittedString = str.get(1).split(",");
		for (int i = 0; i < splittedString.length; i++) {
			if (splittedString[i].matches("[1-9]*")) {
				busIDs.put(i + 1, Integer.parseInt(splittedString[i]));
			}
		}
		return busIDs;
	}

	private static BigInteger task2(List<String> str) {
		HashMap<Integer, Integer> busIDs = getBusMap(str);
		int[] n = new int[busIDs.size()];
		int[] a = new int[busIDs.size()];
		int counter = 0;
		for (int i : busIDs.keySet()) {
			n[counter] = i;
			a[counter] = busIDs.get(i);
			counter++;
		}
		return chineseRemainder(n, a);
	}

	public static BigInteger chineseRemainder(int[] n, int[] a) {
		BigInteger prod = BigInteger.valueOf(stream(n).reduce(1, (i, j) -> i * j));
		BigInteger p, sm = new BigInteger("0");
		for (int i = 0; i < n.length; i++) {
			p = prod.divide(BigInteger.valueOf(n[i]));
			sm = sm.add(mulInv(p, BigInteger.valueOf(n[i])).multiply(BigInteger.valueOf(a[i])).multiply(p));
		}
		return sm.remainder(prod);
	}

	private static BigInteger mulInv(BigInteger a, BigInteger b) {
		BigInteger b0 = b;
		BigInteger x0 = new BigInteger("0");
		BigInteger x1 = new BigInteger("1");
		if (b == new BigInteger("1"))
			return new BigInteger("1");
		while (a.intValue() > 1) {
			BigInteger q = a.divide(b);
			BigInteger amb = a.mod(b);
			a = b;
			b = amb;
			BigInteger xqx = x1.subtract(q.multiply(x0));
			x1 = x0;
			x0 = xqx;
		}
		if (x1.compareTo(new BigInteger("0"))<0)
			x1 = x1.add(b0);
		return x1;
	}

}
