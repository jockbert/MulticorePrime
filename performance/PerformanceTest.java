import program.PrimeProgram;
import calculator.Range;

public class PerformanceTest {

	public static void main(String[] args) {

		printHeader();

		printTest((int) 2e6, new NaiveBaseline(), new NaiveFutures(),
				new NaiveParallellStreams());

		printTest((int) 5e7, new SieveBaseline(), new SieveIncrInitFutures());

	}

	private static void printTest(int maxValue, PrimeProgram baselineProg,
			PrimeProgram... improvedProgs) {

		int baselineDuration = runDuration(maxValue, baselineProg);

		printProg(maxValue, baselineDuration, 100, baselineProg.name());

		for (PrimeProgram prog : improvedProgs) {

			int duration = runDuration(maxValue, prog);

			int performance = 100 * baselineDuration / duration;
			printProg(maxValue, duration, performance, prog.name());

		}

		System.out.println("-------------------------------------------------");
	}

	private static int runDuration(int maxValue, PrimeProgram prog) {
		Range range = Range.r(0, maxValue);
		long start = System.currentTimeMillis();
		prog.createCalculator().countPrimesInRange(range);
		return (int) (System.currentTimeMillis() - start);
	}

	private static void printProg(int maxValue, int milliSecs,
			int percentChange, String name) {
		System.out.print(right(12, maxValue));
		System.out.print(right(8, milliSecs));
		System.out.print(right(8, percentChange + "%"));
		System.out.println("    " + name);
	}

	private static String right(int length, Object o) {
		String result = o.toString();
		while (result.length() < length) {
			result = " " + result;
		}
		return result;
	}

	private static void printHeader() {
		System.out.println("Performance test");
		System.out.println();
		System.out.println("  MaxVal | Max value of prime count range. ");
		System.out.println("     Dur | Duration in milliseconds. ");
		System.out.println("    Impr | Duration improvement over baseline. ");
		System.out.println();
		System.out.println();
		System.out.println("      MaxVal     Dur    Impr    Name");
		System.out.println("-------------------------------------------------");
	}
}
