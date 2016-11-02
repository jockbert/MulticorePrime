import program.BaseProgram;
import program.PrimeProgram;
import calculator.Calculator;
import calculator.InParallelStreamsCalculator;
import calculator.NaiveCalculator;

public class NaiveParallellStreams implements PrimeProgram {

	public static void main(String[] args) {
		BaseProgram.run(new NaiveParallellStreams(), args);
	}

	@Override
	public Calculator createCalculator() {
		return new InParallelStreamsCalculator(new NaiveCalculator());
	}

	@Override
	public String name() {
		return "Naive parallell streams";
	}
}
