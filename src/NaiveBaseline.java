import program.BaseProgram;
import program.PrimeProgram;
import calculator.Calculator;
import calculator.NaiveCalculator;

public class NaiveBaseline implements PrimeProgram {

	public static void main(String[] args) {
		BaseProgram.run(new NaiveBaseline(), args);
	}

	@Override
	public Calculator createCalculator() {
		return new NaiveCalculator();
	}

}
