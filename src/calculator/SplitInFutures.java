package calculator;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import divider.Divider;

public class SplitInFutures implements Calculator {

	private static final ExecutorService SERVICE = Executors
			.newWorkStealingPool();

	private Calculator delegateCalc;

	public SplitInFutures(Calculator calculator) {
		this.delegateCalc = calculator;
	}

	@Override
	public int countPrimesInRange(Range range) {

		List<Range> ranges = toRanges(range);
		List<Callable<Integer>> calculations = toCalculations(ranges);
		List<Future<Integer>> futures = toFutures(calculations);
		return futures.stream().mapToInt(this::get).sum();
	}

	private Integer get(Future<Integer> f) {
		return safe(() -> f.get());
	}

	private List<Callable<Integer>> toCalculations(List<Range> ranges) {
		return ranges.stream().map(this::toCallable).collect(toList());
	}

	private Callable<Integer> toCallable(Range r) {
		return () -> delegateCalc.countPrimesInRange(r);
	}

	private List<Range> toRanges(Range wholeRange) {
		return Divider.defaultSplit(wholeRange);
	}

	private <X> List<Future<X>> toFutures(List<Callable<X>> split) {
		return safe(() -> SERVICE.invokeAll(split));
	}

	@FunctionalInterface
	static interface ProducerWithException<Y> {
		public Y apply() throws Exception;
	}

	private <X> X safe(ProducerWithException<X> func) {
		try {
			return func.apply();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
