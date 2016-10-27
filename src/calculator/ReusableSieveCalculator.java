package calculator;

import sieve.Sieve;

public final class ReusableSieveCalculator implements Calculator {

	private Sieve sieve;

	public ReusableSieveCalculator(Sieve sieve) {
		this.sieve = sieve;
	}

	@Override
	public int countPrimesInRange(Range range) {
		int count = 0;
		for (Integer n : range)
			count += sieve.isPrime(n) ? 1 : 0;
		return count;
	}
}
