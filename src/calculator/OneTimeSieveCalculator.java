package calculator;

public final class OneTimeSieveCalculator implements Calculator {

	boolean[] isPrime;

	@Override
	public int countPrimesInRange(Range range) {
		int low = range.minExclusive();
		int high = range.max();

		initializeSieve(high);

		int count = 0;
		for (int n = low + 1; n <= high; ++n)
			count += isPrime[n] ? 1 : 0;

		return count;
	}

	void initializeSieve(int maxValue) {
		isPrime = new boolean[maxValue + 1];

		for (int i = 2; i < isPrime.length; ++i)
			isPrime[i] = true;

		for (int n = 2; n * n <= maxValue; ++n) {
			if (isPrime[n])
				setMultiplesAsNonPrime(n);
		}
	}

	private void setMultiplesAsNonPrime(int n) {
		for (int m = n * 2; m < isPrime.length; m += n)
			isPrime[m] = false;
	}
}
