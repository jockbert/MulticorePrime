package calculator;

import static calculator.Range.r;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class CalculatorTest {

	abstract Calculator calc();

	public static final class NaiveCalculatorTest extends CalculatorTest {
		@Override
		Calculator calc() {
			return new NaiveCalculator();
		}
	}

	public static final class SieveCalculatorTest extends CalculatorTest {
		@Override
		Calculator calc() {
			return new OneTimeSieveCalculator();
		}
	}

	public static final class SplitInFuturesTest extends CalculatorTest {
		@Override
		Calculator calc() {
			return new SplitInFutures(new NaiveCalculator());
		}
	}

	@Test
	public void testSomeValues() throws Exception {
		assertRange(r(0, 0), 0);
		assertRange(r(0, 1), 0);
		assertRange(r(0, 2), 1);
		assertRange(r(0, 3), 2);
		assertRange(r(0, 4), 2);
		assertRange(r(0, 5), 3);
		assertRange(r(0, 6), 3);
		assertRange(r(0, 7), 4);
		assertRange(r(0, 8), 4);
		assertRange(r(0, 9), 4);
		assertRange(r(0, 10), 4);
		assertRange(r(0, 11), 5);
		assertRange(r(1, 11), 5);
		assertRange(r(2, 11), 4);
		assertRange(r(3, 11), 3);
		assertRange(r(4, 11), 3);
		assertRange(r(5, 11), 2);
		assertRange(r(6, 11), 2);
		assertRange(r(7, 11), 1);
		assertRange(r(8, 11), 1);
		assertRange(r(9, 11), 1);
		assertRange(r(10, 11), 1);
		assertRange(r(11, 11), 0);

		assertRange(r(1, 2), 1);
		assertRange(r(2, 2), 0);
		assertRange(r(3, 2), 0);
	}

	private void assertRange(Range range, int expected) {
		String message = "Range " + range + " should contain " + expected
				+ " primes.";
		assertEquals(message, expected, calc().countPrimesInRange(range));
	}
}
