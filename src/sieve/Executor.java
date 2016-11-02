package sieve;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

	public static ExecutorService service() {
		return Executors.newWorkStealingPool();
	}
}
