import calculator.NaiveCalculator;
import calculator.SplitInFutures;

public class NaiveFutures {

	public static void main(String[] args) {
		BaseProgram.run(new SplitInFutures(new NaiveCalculator()), args);
	}
}
