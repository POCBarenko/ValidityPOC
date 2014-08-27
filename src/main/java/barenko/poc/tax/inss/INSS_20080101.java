package barenko.poc.tax.inss;

import barenko.poc.model.Income;
import barenko.poc.tax.INSS;
import barenko.poc.util.validity.Validity;

@Validity(begin = "01/01/2008", end = "31/12/2013")
public class INSS_20080101 extends INSS {
	@Override
	public Long calculate(Income income) {
		final Long value = (long) (income.getRawValue() * 0.05);
		income.addTax(this, value);
		return value;
	}
}
