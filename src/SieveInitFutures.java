import sieve.BasicSieve;
import sieve.ModifiableSieve;
import sieve.PartialInitializer;
import sieve.SieveInitializer;
import calculator.ReusableSieveCalculator;

public class SieveInitFutures {

	public static void main(String[] args) {

		int maxValue = BaseProgram.getTopValueSafely(args);
		ModifiableSieve sieve = new BasicSieve(maxValue);

		PartialInitializer partialInit;
		partialInit = PartialInitializer.sweepInit();
		partialInit = PartialInitializer.futuresInit(partialInit);
		SieveInitializer.incrementalInitialzier(sieve, partialInit);

		BaseProgram.run(new ReusableSieveCalculator(sieve), args);
	}
}