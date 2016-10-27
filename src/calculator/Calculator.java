package calculator;


public interface Calculator {

	/**
	 * Count primes in range
	 * 
	 * @param low
	 *            Low boundary exclusive
	 * @param high
	 *            High boundary inclusive
	 * @return Number of primes in range.
	 */
	int countPrimesInRange(Range range);
}
