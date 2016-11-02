import java.io.IOException;

import program.BaseProgram;
import program.PrimeProgram;
import sieve.BasicSieve;
import sieve.ModifiableSieve;
import sieve.PartialInitializer;
import sieve.SieveInitializer;
import calculator.Calculator;
import calculator.Range;
import calculator.ReusableSieveCalculator;

public class SieveIncrInitFutures implements PrimeProgram {

	public static void main(String[] args) {
		System.out.println("push enter to start");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BaseProgram.run(new SieveIncrInitFutures(), args);
	}

	@Override
	public Calculator createCalculator() {
		return new Calculator() {
			@Override
			public int countPrimesInRange(Range range) {
				ModifiableSieve sieve = createSieve(range.max());
				Calculator calc = new ReusableSieveCalculator(sieve);
				return calc.countPrimesInRange(range);
			}
		};
	}

	private ModifiableSieve createSieve(int maxValue) {
		maxValue = maxValue < 5 ? 5 : maxValue;
		ModifiableSieve sieve = new BasicSieve(maxValue);

		PartialInitializer partialInit;
		partialInit = PartialInitializer.sweepInit();
		partialInit = PartialInitializer.futuresInit(partialInit);
		SieveInitializer.incrementalInitialzier(sieve, partialInit);

		return sieve;
	}

	@Override
	public String name() {
		return "Sieve incr. init. with futures";
	}
}