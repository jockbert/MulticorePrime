package sieve;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import calculator.Range;
import divider.Divider;

public interface PartialInitializer {

	void clearPrimeMultiples(ModifiableSieve sieve, Range primeRange);

	public static PartialInitializer sweepInit() {
		return new PartialInitializer() {

			@Override
			public void clearPrimeMultiples(final ModifiableSieve sieve,
					Range primeRange) {

				for (Integer n : primeRange) {
					if (sieve.isPrime(n))
						setMultiplesAsNonPrime(sieve, n);
				}
			}

			void setMultiplesAsNonPrime(final ModifiableSieve sieve, int n) {
				int start = ((long) n * n) > Integer.MAX_VALUE ? Integer.MAX_VALUE
						: n * n;

				for (int m = start; m <= sieve.max(); m += n)
					sieve.setNotPrime(m);
			}
		};
	}

	public static PartialInitializer parallellStreamInit(
			final PartialInitializer delegate) {
		return new PartialInitializer() {

			@Override
			public void clearPrimeMultiples(final ModifiableSieve sieve,
					Range primeRange) {

				Stream<Range> subRanges = Divider.defaultSplit(primeRange)
						.stream().parallel();

				subRanges.forEach(r -> delegate.clearPrimeMultiples(sieve, r));
			}
		};
	}

	public static PartialInitializer futuresInit(
			final PartialInitializer delegate) {
		return new PartialInitializer() {

			@Override
			public void clearPrimeMultiples(final ModifiableSieve sieve,
					Range primeRange) {

				List<Range> subRanges = Divider.defaultSplit(primeRange);

				Function<Range, Callable<Integer>> asRunnable = r -> () -> {
					delegate.clearPrimeMultiples(sieve, r);
					return 0;
				};

				List<Callable<Integer>> callabels = subRanges.stream()
						.map(asRunnable).collect(Collectors.toList());

				try {
					Executor.service().invokeAll(callabels).forEach(f -> {
						try {
							f.get();
						} catch (ExecutionException | InterruptedException e) {
							throw new RuntimeException(e);
						}
					});

				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
}
