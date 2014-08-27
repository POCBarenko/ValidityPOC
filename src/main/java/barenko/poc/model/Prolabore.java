package barenko.poc.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import barenko.poc.util.RoundingCalculator;

public class Prolabore implements Income {
	private Date referenceDate;
	private Date emission;
	private long rawValue;
	private long previousMonthRouding;
	private long rounding;
	private Map<Class<? extends Tax>, Long> taxes = new HashMap<>();
	private String description;
	
	public Prolabore(Date referenceDate, long rawValue, Prolabore previousMonth) {
		if (previousMonth != null) {
			previousMonthRouding = previousMonth.getRounding();
		}
		this.referenceDate = referenceDate;
		this.rawValue = rawValue;
	}
	
	public Prolabore(Date referenceDate, long rawValue) {
		this(referenceDate, rawValue, null);
	}
	
	@Override
	public Long getRawValue() {
		return rawValue;
	}
	
	public Long getNetValue() {
		return getRealNetValue() + rounding;
	}
	
	public Long getRealNetValue() {
		long retained = 0;
		
		for (final Long tax : taxes.values()) {
			retained += tax;
		}
		retained -= previousMonthRouding;
		
		return rawValue - retained;
	}
	
	@Override
	public Date getReferenceDate() {
		return referenceDate;
	}
	
	public Date getEmission() {
		return emission;
	}
	
	public Map<Class<? extends Tax>, Long> getTaxes() {
		return Collections.unmodifiableMap(taxes);
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public void addTax(Tax tax, Long value) {
		taxes.put(tax.getClass(), value);
		calculateRounding();
	}
	
	private void calculateRounding() {
		rounding = new RoundingCalculator().calculate(getRealNetValue());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Long getRounding() {
		return rounding;
	}
}
