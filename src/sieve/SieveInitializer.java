package sieve;

import java.util.ArrayList;
import java.util.List;

import calculator.Range;

public class SieveInitializer {

	public static void sweepInit(final ModifiableSieve sieve) {
		sieve.setNotPrime(0);
		sieve.setNotPrime(1);

		PartialInitializer.sweepInit()
				.clearPrimeMultiples(sieve, sieve.range());
	}

	public static void incrementalInitialzier(final ModifiableSieve sieve,
			PartialInitializer delegate) {

		sieve.setNotPrime(0);
		sieve.setNotPrime(1);
		sieve.setNotPrime(4);

		List<Range> ranges = incrementalSafeRanges(5, sieve.max());

		for (Range range : ranges)
			delegate.clearPrimeMultiples(sieve, range);
	}

	public static List<Range> incrementalSafeRanges(int startMax, int endMax) {
		List<Range> ranges = new ArrayList<>();

		Range range = Range.r(0, startMax);

		while (range.minExclusive() < endMax) {
			ranges.add(range);

			int min = range.max();
			long max = ((long) min) * min;
			max = max > Integer.MAX_VALUE ? Integer.MAX_VALUE : max;
			max = max > endMax ? endMax : max;
			range = Range.r(min, (int) max);
		}

		return ranges;
	}
}
