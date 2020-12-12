package testing;

public class KeyForAOC11 {
	private final int x;
	private final int y;

	public KeyForAOC11(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof KeyForAOC11))
			return false;
		KeyForAOC11 key = (KeyForAOC11) o;
		return x == key.x && y == key.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + 7*y;
		return result;
	}

}
