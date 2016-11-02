package divider;

import java.util.List;

import calculator.Range;

public interface Divider {
	static final int DEFAULT_MAX_COUNT = 500;
	static final int DEFAULT_MIN_SIZE = 10;
	static final MinSizeDivider DEFAULT_DIV = new MinSizeDivider(
			DEFAULT_MIN_SIZE, DEFAULT_MAX_COUNT);

	List<Range> split(Range range);

	static List<Range> defaultSplit(Range range) {
		List<Range> split = DEFAULT_DIV.split(range);
		// System.out.println("Range " + range + " split into " + split.size()
		// + " chunks");
		return split;
	}
}
