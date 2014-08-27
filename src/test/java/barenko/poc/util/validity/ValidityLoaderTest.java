package barenko.poc.util.validity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import barenko.poc.TestUtils;
import barenko.poc.tax.inss.INSS_20000601;
import barenko.poc.tax.inss.INSS_20080101;
import barenko.poc.tax.inss.INSS_20140101;

public class ValidityLoaderTest extends TestUtils {

	@Test
	public void getINSS() {
		final TaxLoader tax = new TaxLoader();
		try {
			assertNull(tax.getINSS(date("01/01/1990")));
			fail("deveria estourar sem período vigente");
		} catch (final Throwable t) {
			assertTrue(t.getMessage().startsWith("Não há período vigente para a data"));
		}
		
		try {
			tax.getINSS(date("01/01/2200"));
			fail("deveria estourar data futura");
		} catch (final Throwable t) {
			assertTrue(t.getMessage().startsWith("Data futura"));
		}
		
		assertEquals(INSS_20000601.class, tax.getINSS(date("01/06/2000")));
		assertEquals(INSS_20000601.class, tax.getINSS(date("11/08/2007")));
		assertEquals(INSS_20080101.class, tax.getINSS(date("01/01/2008")));
		assertEquals(INSS_20080101.class, tax.getINSS(date("01/01/2010")));
		assertEquals(INSS_20140101.class, tax.getINSS(date("01/01/2014")));
		assertEquals(INSS_20140101.class, tax.getINSS(new Date()));
	}
	
}
