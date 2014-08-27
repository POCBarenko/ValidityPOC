package barenko.poc.util;

import java.text.NumberFormat;
import java.text.ParseException;

public class MoneyFormat {
	public static String format(long cents) {
		final NumberFormat format = NumberFormat.getCurrencyInstance();
		return format.format(centsToMoney(cents, format));
	}

	public static long parse(String moneyFormat) {
		try {
			final NumberFormat format = NumberFormat.getCurrencyInstance();
			return moneyToCents(format.parse(moneyFormat).doubleValue(), format);
		} catch (final ParseException | NullPointerException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static long moneyToCents(double value) {
		return moneyToCents(value, NumberFormat.getCurrencyInstance());
	}

	private static long moneyToCents(double value, NumberFormat format) {
		return (long) (value * Math.pow(10D, format.getMaximumFractionDigits()));
	}

	public static double centsToMoney(long value) {
		return centsToMoney(value, NumberFormat.getCurrencyInstance());
	}
	
	private static double centsToMoney(long value, NumberFormat format) {
		return value / Math.pow(10D, format.getMaximumFractionDigits());
	}
}
