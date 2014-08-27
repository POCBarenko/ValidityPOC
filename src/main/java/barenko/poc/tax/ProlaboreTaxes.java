package barenko.poc.tax;

import barenko.poc.model.Prolabore;

public class ProlaboreTaxes {
	public void calculate(Prolabore prolabore) {
		new INSS().calculate(prolabore);
	}
}
