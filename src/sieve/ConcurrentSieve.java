package sieve;

import java.util.ArrayList;
import java.util.List;

import calculator.Range;

public class ConcurrentSieve implements ModifiableSieve {

	private final int max;

	private final List<Boolean> isNotPrime;

	public ConcurrentSieve(int max) {
		this.max = max;
		this.isNotPrime = new ArrayList<>(max + 1);

		for (int n : Range.r(0, max + 1)) {
			isNotPrime.add(false);
		}
		// new Boolean[max + 1]);
	}

	@Override
	public boolean isPrime(int n) {

		return !isNotPrime.get(n);
	}

	@Override
	public int max() {
		return max;
	}

	@Override
	public Range range() {
		return Range.r(0, max);
	}

	@Override
	public void setNotPrime(int n) {
		this.isNotPrime.set(n, true);
	}
}
