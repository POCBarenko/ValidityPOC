package barenko.poc.model;

import java.util.Date;

public interface Income {
	Long getRawValue();
	
	Date getReferenceDate();
	
	void addTax(Tax tax, Long value);
}
