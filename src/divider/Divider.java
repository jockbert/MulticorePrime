package divider;

import java.util.List;

import calculator.Range;

public interface Divider {
	static final int DEFAULT_MAX_COUNT = 100;
	static final int DEFAULT_MIN_SIZE = 1000;
	static final MinSizeDivider DEFAULT_DIV = new MinSizeDivider(
			DEFAULT_MIN_SIZE, DEFAULT_MAX_COUNT);

	List<Range> split(Range range);

	static List<Range> defaultSplit(Range range) {
		return DEFAULT_DIV.split(range);
	}
}
