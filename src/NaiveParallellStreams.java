import calculator.NaiveCalculator;
import calculator.InParallelStreamsCalculator;

public class NaiveParallellStreams {

	public static void main(String[] args) {
		BaseProgram.run(new InParallelStreamsCalculator(new NaiveCalculator()),
				args);
	}
}
