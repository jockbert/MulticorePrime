package calculator;

import java.util.Iterator;
import java.util.stream.IntStream;

public final class Range implements Iterable<Integer> {

	private final int min;
	private final int max;

	public static Range r(int min, int max) {
		return new Range(min, max);
	}

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int minExclusive() {
		return min;
	}

	public int max() {
		return max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + max;
		result = prime * result + min;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Range other = (Range) obj;
		return max == other.max && min == other.min;
	}

	@Override
	public String toString() {
		return "(" + min + "," + max + ")";
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			int isAt = min;

			@Override
			public boolean hasNext() {
				return isAt < max;
			}

			@Override
			public Integer next() {
				return ++isAt;
			}
		};
	}

	public IntStream stream() {
		return IntStream.iterate(min + 1, n -> n + 1).limit(max - min);
	}
}
