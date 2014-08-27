package barenko.poc.util.validity;

import java.util.Date;

import barenko.poc.tax.INSS;

public class TaxLoader {

	private ValidityLoader validityLoader;

	public TaxLoader() {
		validityLoader = new ValidityLoader();
	}
	
	public <T extends INSS> Class<T> getINSS(Date date) {
		return validityLoader.get(INSS.class, date);
	}
}
