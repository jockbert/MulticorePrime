import static org.junit.Assert.assertEquals;

import org.junit.Test;

import program.PrimeProgram;
import calculator.Range;

public abstract class ProgramTest {

	abstract PrimeProgram cut();

	public static class NaiveBaselineTest extends ProgramTest {
		@Override
		PrimeProgram cut() {
			return new NaiveBaseline();
		}
	}

	public static class NaiveFuturesTest extends ProgramTest {
		@Override
		PrimeProgram cut() {
			return new NaiveFutures();
		}
	}

	public static class NaiveParallellStreamsTest extends ProgramTest {
		@Override
		PrimeProgram cut() {
			return new NaiveParallellStreams();
		}
	}

	public static class SieveBaselineTest extends ProgramTest {
		@Override
		PrimeProgram cut() {
			return new SieveBaseline();
		}
	}

	public static class SieveIncrInitFuturesTest extends ProgramTest {
		@Override
		PrimeProgram cut() {
			return new SieveIncrInitFutures();
		}
	}

	@Test
	public void testFrom0() throws Exception {
		assertCorrectCount(0, 0, 0);
		assertCorrectCount(0, 1, 0);
		assertCorrectCount(0, 2, 1);
		assertCorrectCount(0, 3, 2);
		assertCorrectCount(0, 4, 2);
		assertCorrectCount(0, 5, 3);
		assertCorrectCount(0, 6, 3);
		assertCorrectCount(0, 7, 4);
		assertCorrectCount(0, 8, 4);
		assertCorrectCount(0, 9, 4);
		assertCorrectCount(0, 10, 4);
		assertCorrectCount(0, 11, 5);
	}

	@Test
	public void testTo11() throws Exception {
		assertCorrectCount(0, 11, 5);
		assertCorrectCount(1, 11, 5);
		assertCorrectCount(2, 11, 4);
		assertCorrectCount(3, 11, 3);
		assertCorrectCount(4, 11, 3);
		assertCorrectCount(5, 11, 2);
		assertCorrectCount(6, 11, 2);
		assertCorrectCount(7, 11, 1);
		assertCorrectCount(8, 11, 1);
		assertCorrectCount(9, 11, 1);
		assertCorrectCount(10, 11, 1);
		assertCorrectCount(11, 11, 0);
	}

	@Test
	public void testFirstThousandPrimes() throws Exception {
		assertCorrectCount(0, 7918, 999);
		assertCorrectCount(0, 7919, 1000);
		assertCorrectCount(0, 7920, 1000);
	}

	private void assertCorrectCount(int minExcl, int max, int expectedCount) {
		Range range = Range.r(minExcl, max);
		int actualCount = cut().createCalculator().countPrimesInRange(range);
		String message = "Calculator should return prime count "
				+ expectedCount + " for range " + range;
		assertEquals(message, actualCount, expectedCount);
	}
}
