package barenko.poc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	protected Date date(String dt) {
		try {
			return df.parse(dt);
		} catch (final ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
}
