package barenko.poc.tax.inss;

import barenko.poc.model.Income;
import barenko.poc.tax.INSS;
import barenko.poc.util.validity.Validity;

@Validity(begin = "01/01/2014")
public class INSS_20140101 extends INSS {
	@Override
	public Long calculate(Income income) {
		final Long value = (long) (income.getRawValue() * 0.11);
		income.addTax(this, value);
		return value;
	}
}
