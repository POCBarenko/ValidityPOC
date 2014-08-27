package barenko.poc.tax;

import java.util.Date;

import barenko.poc.model.Income;
import barenko.poc.model.Tax;
import barenko.poc.util.validity.TaxLoader;

public class INSS implements Tax {

	@Override
	public Long calculate(Income income) {
		return getTax(income).calculate(income);
	}

	private INSS getTax(Income income) {
		final Date referenceDate = income.getReferenceDate();
		try {
			return (INSS) new TaxLoader().getINSS(referenceDate).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
