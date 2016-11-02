import program.BaseProgram;
import program.PrimeProgram;
import sieve.BasicSieve;
import sieve.ModifiableSieve;
import sieve.SieveInitializer;
import calculator.Calculator;
import calculator.Range;
import calculator.ReusableSieveCalculator;

public class SieveBaseline implements PrimeProgram {

	public static void main(String[] args) {
		BaseProgram.run(new SieveBaseline(), args);
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
		maxValue = maxValue < 1 ? 1 : maxValue;
		ModifiableSieve sieve = new BasicSieve(maxValue);
		SieveInitializer.sweepInit(sieve);
		return sieve;
	}

	@Override
	public String name() {
		return "Sieve baseline";
	}
}
