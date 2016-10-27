package sieve;

import calculator.Range;

public final class BasicSieve implements ModifiableSieve {

	private volatile boolean[] isNonPrimeSieve;

	public BasicSieve(int max) {
		isNonPrimeSieve = new boolean[max + 1];
	}

	@Override
	public boolean isPrime(int n) {
		return !isNonPrimeSieve[n];
	}

	@Override
	public int max() {
		return isNonPrimeSieve.length - 1;
	}

	@Override
	public void setNotPrime(int n) {
		isNonPrimeSieve[n] = true;
	}

	@Override
	public Range range() {
		return Range.r(0, max());
	}
}
