import program.BaseProgram;
import program.PrimeProgram;
import calculator.Calculator;
import calculator.NaiveCalculator;
import calculator.SplitInFutures;

public class NaiveFutures implements PrimeProgram {

	public static void main(String[] args) {
		BaseProgram.run(new NaiveFutures(), args);
	}

	@Override
	public Calculator createCalculator() {
		return new SplitInFutures(new NaiveCalculator());
	}
}
