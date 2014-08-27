package barenko.poc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import barenko.poc.model.Prolabore;
import barenko.poc.model.Tax;
import barenko.poc.tax.ProlaboreTaxes;
import barenko.poc.util.MoneyFormat;

public class Main {
	public static void main(String... args) {
		final List<Prolabore> prolabores = new ArrayList<>();
		
		prolabores.add(new Prolabore(new Date(), 123456));
		prolabores.add(new Prolabore(new Date(), 100000));
		prolabores.add(new Prolabore(new Date(), 173401));
		
		final ProlaboreTaxes prolaboreTaxes = new ProlaboreTaxes();
		
		for (final Prolabore p : prolabores) {
			prolaboreTaxes.calculate(p);
			printProlabore(p);
		}
	}

	private static void printProlabore(final Prolabore p) {
		System.out.println("\nProlabore --------- ");
		System.out.println("\tSalário bruto:\t\t\t" + MoneyFormat.format(p.getRawValue()));
		System.out.println("\tTaxas: ");
		final Map<Class<? extends Tax>, Long> taxes = p.getTaxes();
		for (final Class<?> c : taxes.keySet()) {
			System.out.format("\t\t%s:\t\t\t%s\n", c.getSuperclass().getSimpleName(), MoneyFormat.format(taxes.get(c)));
		}
		System.out.println("\tSalário líquido:\t\t" + MoneyFormat.format(p.getRealNetValue()));
		System.out.println("\tArredondamento:\t\t\t" + MoneyFormat.format(p.getRounding()));
		System.out.println("\t-----------------------------------------------");
		System.out.println("\tSalário líquido arredondado:\t" + MoneyFormat.format(p.getNetValue()));
	}
}
