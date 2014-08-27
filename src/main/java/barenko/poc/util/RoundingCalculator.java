package barenko.poc.util;

public class RoundingCalculator {
	
	public long calculate(long value) {
		final long rest = value % 100;
		return (rest == 0) ? 0 : 100 - rest;
	}
}
