package program;

import calculator.Calculator;
import calculator.Range;

public class BaseProgram {

	public static void run(PrimeProgram prog, String[] args) {
		Calculator calc = prog.createCalculator();
		int topValue = getTopValueSafely(args);
		int result = calc.countPrimesInRange(Range.r(0, topValue));
		System.out.println(result);
	}

	private static int getTopValueSafely(String[] args) {
		assertArgumentLengths(args);
		int n = safeParse(args[0]);
		assertValueRange(n);
		return n;
	}

	private static void assertArgumentLengths(String[] args) {
		if (args.length != 1)
			endOnBadArgument();
	}

	private static void assertValueRange(int n) {
		if (n < 0 || n > 2e9)
			endOnBadArgument();
	}

	private static int safeParse(String arg) {
		try {
			return Integer.decode(arg);
		} catch (NumberFormatException e) {
			endOnBadArgument();
			return -1;
		}
	}

	private static void endOnBadArgument() {
		String msg = "Expecting an integer in range [0, 2*10^9] as argument";
		System.err.println(msg);
		System.exit(-1);
	}
}
