package divider;

import static calculator.Range.r;

import java.util.ArrayList;
import java.util.List;

import calculator.Range;

public class MinSizeDivider implements Divider {

	private final int minRangeSize;
	private final int maxRangeCount;

	public MinSizeDivider(int minRangeSize, int maxRangeCount) {
		this.minRangeSize = minRangeSize;
		this.maxRangeCount = maxRangeCount;
	}

	@Override
	public List<Range> split(Range range) {

		int max = range.max();
		int min = range.minExclusive();
		max = noCrossedLegs(max, min);

		int size = max - min;
		int rangeSize = capRangeCount(size);

		List<Range> result = new ArrayList<>();

		int start = min;
		for (int stop = start + rangeSize; stop < max;) {
			result.add(r(start, stop));
			start = stop;
			stop += rangeSize;
		}
		result.add(r(start, max));

		return result;
	}

	private int capRangeCount(int size) {
		int rangeSize = minRangeSize;
		int rangeCount = size / rangeSize;

		boolean hasToManyRanges = rangeCount > maxRangeCount;
		boolean hasMaxRangeCount = rangeCount == maxRangeCount;
		boolean hasExtraRangeOnEnd = size % rangeSize != 0;

		if (hasToManyRanges || (hasMaxRangeCount && hasExtraRangeOnEnd)) {
			rangeSize = (size / maxRangeCount) + 1;
		}
		return rangeSize;
	}

	private int noCrossedLegs(int max, int min) {
		return min > max ? min : max;
	}
}
