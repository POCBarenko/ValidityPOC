package barenko.poc.tax.inss;

import barenko.poc.model.Income;
import barenko.poc.tax.INSS;
import barenko.poc.util.validity.Validity;

@Validity(begin = "01/06/2000", end = "31/12/2007")
public class INSS_20000601 extends INSS {
	@Override
	public Long calculate(Income income) {
		final Long value = (long) (income.getRawValue() * 0.20);
		income.addTax(this, value);
		return value;
	}
}
