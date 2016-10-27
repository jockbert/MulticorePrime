package calculator;

import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import divider.Divider;

public class InParallelStreamsCalculator implements Calculator {

	private Calculator delegate;

	public InParallelStreamsCalculator(Calculator calculator) {
		this.delegate = calculator;
	}

	@Override
	public int countPrimesInRange(Range range) {
		ToIntFunction<Range> calc = rng -> delegate.countPrimesInRange(rng);

		List<Range> rangeList = Divider.defaultSplit(range);
		Stream<Range> rangeStream = rangeList.stream().parallel();
		return rangeStream.mapToInt(calc).sum();
	}
}
