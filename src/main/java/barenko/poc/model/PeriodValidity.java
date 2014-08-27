package barenko.poc.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import barenko.poc.util.validity.Validity;

public class PeriodValidity implements Comparable<PeriodValidity> {
	private Date begin;
	private Date end;
	
	public PeriodValidity(Date begin, Date end) {
		this.begin = begin;
		this.end = end;
	}
	
	public PeriodValidity(Validity validity) {
		final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date begin = null;
		try {
			begin = format.parse(validity.begin());
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		Date end = null;
		try {
			end = format.parse(validity.end());
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		this.begin = begin;
		this.end = end;
	}

	public boolean isInto(Date dt) {
		if (dt == null) return false;
		return (begin.before(dt) || begin.equals(dt)) && (end.after(dt) || end.equals(dt));
	}
	
	@Override
	public int compareTo(PeriodValidity o) {
		if (begin == null) {
			if (o.begin == null) return 0;
			return -1;
		}
		if (o.begin == null) return 1;
		return begin.before(o.begin) ? -1 : 1;
	}

	public Date getBegin() {
		return begin;
	}

	public Date getEnd() {
		return end;
	}
}
