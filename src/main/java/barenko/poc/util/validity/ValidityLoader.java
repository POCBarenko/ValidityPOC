package barenko.poc.util.validity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.vidageek.mirror.dsl.Mirror;

import org.reflections.Reflections;

import barenko.poc.model.PeriodValidity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ValidityLoader {
	private Mirror mirror;
	private Map<Class, Map<PeriodValidity, Class<?>>> cache = new HashMap<>();
	
	public ValidityLoader() {
		mirror = new Mirror();
	}
	
	public <T, S extends T> Class<S> get(Class<T> referenceClass, Date date) {
		if (date.after(new Date())) throw new RuntimeException("Data futura: " + date);

		final Map<PeriodValidity, Class<?>> eligibleValidities = getEligibleValidityClasses(referenceClass);
		final List<Class<S>> results = getAllIntoPeriodValidity(date, eligibleValidities);
		
		if (results.size() > 1) throw new RuntimeException("Há mais de um período vigente: " + results.toString());
		if (results.size() == 0) throw new RuntimeException("Não há período vigente para a data: " + date);
		return results.get(0);
	}
	
	private <T, S extends T> List<Class<S>> getAllIntoPeriodValidity(Date date, final Map<PeriodValidity, Class<?>> validities) {
		final List<Class<S>> results = new ArrayList<>();
		for (final PeriodValidity p : validities.keySet()) {
			if (p.isInto(date)) {
				results.add((Class<S>) validities.get(p));
			}
		}
		return results;
	}
	
	private <T, S extends T> Map<PeriodValidity, Class<?>> getEligibleValidityClasses(Class<T> referenceClass) {
		
		Map<PeriodValidity, Class<?>> validities = cache.get(referenceClass);
		if (validities != null) return validities;
		
		validities = loadEligibleValidities(referenceClass);
		cache.put(referenceClass, validities);
		return validities;
	}

	private <T> Map<PeriodValidity, Class<?>> loadEligibleValidities(Class<T> referenceClass) {
		Map<PeriodValidity, Class<?>> validities;
		final Set<Class<?>> eligibles = getEligibleClasses(referenceClass);
		
		validities = new HashMap<>();
		for (final Class<?> eligible : eligibles) {
			final Validity validity = getAnnotation(Validity.class, eligible);
			validities.put(new PeriodValidity(validity), eligible);
		}
		return validities;
	}

	private Set<Class<?>> getEligibleClasses(Class<?> referenceClass) {
		final Reflections reflections = new Reflections(resolveSubPackage(referenceClass));
		final Set<Class<?>> eligibles = reflections.getTypesAnnotatedWith(Validity.class);
		return eligibles;
	}
	
	private String resolveSubPackage(Class clazz) {
		return clazz.getName().toLowerCase();
	}
	
	private <Annotation> Annotation getAnnotation(Class<Annotation> annotationClass, Class<?> clazz) {
		return mirror.on(clazz).reflect().annotation(annotationClass).atClass();
	}
}
