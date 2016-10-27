package sieve;

import static calculator.Range.r;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import calculator.Range;

public class IncrementalSafeRangesTest {

	@Test
	public void test_2_2() throws Exception {
		assertRangeStream(2, 2, r(0, 2));
	}

	@Test
	public void test_2_10() throws Exception {
		assertRangeStream(2, 10, r(0, 2), r(2, 4), r(4, 10));
	}

	@Test
	public void test_2_15() throws Exception {
		assertRangeStream(2, 15, r(0, 2), r(2, 4), r(4, 15));
	}

	@Test
	public void test_2_16() throws Exception {
		assertRangeStream(2, 16, r(0, 2), r(2, 4), r(4, 16));
	}

	@Test
	public void test_2_17() throws Exception {
		assertRangeStream(2, 17, r(0, 2), r(2, 4), r(4, 16), r(16, 17));
	}

	private void assertRangeStream(int startMax, int endMax, Range... expected) {
		List<Range> actual = SieveInitializer.incrementalSafeRanges(startMax,
				endMax);

		assertArrayEquals(expected, actual.toArray());
	}
}
