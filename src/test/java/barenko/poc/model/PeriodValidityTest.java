package barenko.poc.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import barenko.poc.TestUtils;

public class PeriodValidityTest extends TestUtils {
	
	@Test
	public void isInto() {
		final PeriodValidity period2005 = new PeriodValidity(date("01/01/2005"), date("31/12/2005"));
		assertFalse(period2005.isInto(null));
		assertFalse(period2005.isInto(date("01/01/2000")));
		assertFalse(period2005.isInto(date("31/12/2003")));
		assertFalse(period2005.isInto(date("01/01/2006")));
		assertTrue(period2005.isInto(date("01/01/2005")));
		assertTrue(period2005.isInto(date("31/12/2005")));
	}
}
