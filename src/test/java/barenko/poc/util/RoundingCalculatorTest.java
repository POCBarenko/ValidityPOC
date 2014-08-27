package barenko.poc.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoundingCalculatorTest {
	
	@Test
	public void calculate() {
		final RoundingCalculator calc = new RoundingCalculator();
		assertEquals(0, calc.calculate(0));
		assertEquals(0, calc.calculate(-0));
		assertEquals(90, calc.calculate(10));
		assertEquals(50, calc.calculate(50));
		assertEquals(0, calc.calculate(100));
		assertEquals(77, calc.calculate(123));
	}
	
}
