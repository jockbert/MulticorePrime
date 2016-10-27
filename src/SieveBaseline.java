import sieve.BasicSieve;
import sieve.ModifiableSieve;
import sieve.SieveInitializer;
import calculator.ReusableSieveCalculator;

public class SieveBaseline {

	public static void main(String[] args) {

		int maxValue = BaseProgram.getTopValueSafely(args);
		ModifiableSieve sieve = new BasicSieve(maxValue);

		SieveInitializer.sweepInit(sieve);

		BaseProgram.run(new ReusableSieveCalculator(sieve), args);
	}
}
