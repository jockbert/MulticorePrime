package calculator;

public final class NaiveCalculator implements Calculator {

	@Override
	public int countPrimesInRange(Range range) {
		int count = 0;
		int low = range.minExclusive() + 1;
		int high = range.max();

		for (int n = low; n <= high; n++)
			count += isPrime(n) ? 1 : 0;

		return count;
	}

	private boolean isPrime(int n) {
		for (int t = 2; t * t <= n; ++t) {
			if (n % t == 0)
				return false;
		}
		return n >= 2;
	}
}
