package barenko.poc.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import barenko.poc.util.MoneyFormat;

public class MoneyFormatTest {
	
	@Test
	public void parse() {
		assertEquals(0, MoneyFormat.parse(null));
		assertEquals(0, MoneyFormat.parse(""));
		assertEquals(0, MoneyFormat.parse("0"));
		assertEquals(0, MoneyFormat.parse("X"));
		assertEquals(0, MoneyFormat.parse("123"));
		assertEquals(123, MoneyFormat.parse("R$ 1,23"));
		assertEquals(-123, MoneyFormat.parse("-R$ 1,23"));
		assertEquals(1234456, MoneyFormat.parse("R$ 12.344,56"));
		assertEquals(12344, MoneyFormat.parse("R$ 123,4456"));
	}
	
	@Test
	public void format() {
		assertEquals("R$ 0,00", MoneyFormat.format(0));
		assertEquals("R$ 0,00", MoneyFormat.format(-0));
		assertEquals("R$ 1,23", MoneyFormat.format(123));
		assertEquals("-R$ 1,23", MoneyFormat.format(-123));
		assertEquals("R$ 1.234,56", MoneyFormat.format(123456));
	}
}
