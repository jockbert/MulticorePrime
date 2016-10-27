package divider;

import static calculator.Range.r;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import calculator.Range;

public class MinRangeSizeDividerTest {

	private MinSizeDivider createCUT(int minRangeSize, int maxRangeCount) {
		return new MinSizeDivider(minRangeSize, maxRangeCount);
	}

	@Test
	public void testWithMinRange10AndMax5Ranges() throws Exception {

		assertDivider(10, 5, r(0, 0), r(0, 0));
		assertDivider(10, 5, r(0, 1), r(0, 1));
		assertDivider(10, 5, r(0, 3), r(0, 3));

		// crossed legs
		assertDivider(10, 5, r(0, -1), r(0, 0));

		// near minRange ranges
		assertDivider(10, 5, r(1, 10), r(1, 10));
		assertDivider(10, 5, r(1, 11), r(1, 11));
		assertDivider(10, 5, r(1, 12), r(1, 11), r(11, 12));

		assertDivider(10, 5, r(1, 20), r(1, 11), r(11, 20));
		assertDivider(10, 5, r(1, 21), r(1, 11), r(11, 21));
		assertDivider(10, 5, r(1, 22), r(1, 11), r(11, 21), r(21, 22));

		// near max range count
		assertDivider(10, 5, r(0, 49), r(0, 10), r(10, 20), r(20, 30),
				r(30, 40), r(40, 49));
		assertDivider(10, 5, r(0, 50), r(0, 10), r(10, 20), r(20, 30),
				r(30, 40), r(40, 50));
		assertDivider(10, 5, r(0, 51), r(0, 11), r(11, 22), r(22, 33),
				r(33, 44), r(44, 51));
		assertDivider(1, 5, r(0, 51), r(0, 11), r(11, 22), r(22, 33),
				r(33, 44), r(44, 51));
	}

	private void assertDivider(int minRangeSize, int maxRangeCount,
			Range originalRange, Range... expectedRanges) {

		Divider divider = createCUT(minRangeSize, maxRangeCount);
		List<Range> actualRanges = divider.split(originalRange);
		assertArrayEquals(expectedRanges, actualRanges.toArray());
	}
}
