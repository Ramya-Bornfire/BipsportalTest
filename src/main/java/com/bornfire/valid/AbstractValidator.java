package com.bornfire.valid;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.bornfire.valid.builder.RuleBuilderCollection;
import com.bornfire.valid.builder.RuleBuilderProperty;
import com.bornfire.valid.context.ProcessorContext;
import com.bornfire.valid.context.ValidationContext;
import com.bornfire.valid.context.ValidationResult;
import com.bornfire.valid.rule.Rule;
import com.bornfire.valid.rule.RuleBuilderCollectionImpl;
import com.bornfire.valid.rule.RuleBuilderPropertyImpl;
import com.bornfire.valid.rule.RuleProcessorStrategy;
import com.bornfire.valid.transform.ValidationResultTransform;

public abstract class AbstractValidator<T> implements Validator<T> {

	private final List<Rule<T>> rules = new LinkedList<>();

	private final Initializer<T> initialize;

	private String property;

	private RuleProcessorStrategy ruleProcessor = RuleProcessorStrategy.getDefault();

	private static class Initializer<T> {

		private final AtomicReference<Boolean> atomicReference = new AtomicReference<>(Boolean.FALSE);

		private final Validator<T> validator;

		Initializer(final Validator<T> validator) {
			this.validator = validator;
		}

		/**
		 * This method cause Race Condition. We are using Compare And Swap (CAS)
		 * <p>
		 * {@link https://en.wikipedia.org/wiki/Race_condition}
		 * {@link https://en.wikipedia.org/wiki/Compare-and-swap}
		 */
		public void init() {
			if (isNotInitialized()) {
				// Attempt to set atomicReference to true if it is still false
				if (atomicReference.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
					validator.rules();
				}
			}
		}

		private boolean isNotInitialized() {
			return atomicReference.get() == Boolean.FALSE;
		}
	}

	protected AbstractValidator() {
		this.initialize = new Initializer<>(this);
	}

	/**
	 * {@link #failFastRule() AbstractValidator}
	 */
	@Override
	public void failFastRule() {
		this.ruleProcessor = RuleProcessorStrategy.getFailFast();
	}

	/**
	 * {@link #getCounter() AbstractValidator}
	 */
	@Override
	public Integer getCounter() {
		return ProcessorContext.get().get();
	}

	/**
	 * {@link #setPropertyOnContext(String) AbstractValidator }
	 */
	@Override
	public void setPropertyOnContext(final String property) {
		this.property = property;
	}

	/**
	 * {@link #getPropertyOnContext(String, Class) AbstractValidator }
	 */
	@Override
	public <P> P getPropertyOnContext(final String property, final Class<P> clazz) {
		return ValidationContext.get().getProperty(property, clazz);
	}

	/**
	 * {@link #validate(Object) AbstractValidator }
	 */
	@Override
	public ValidationResult validate(final T instance) {
		ruleProcessor.process(instance, this);
		return ValidationContext.get().getValidationResult();
	}

	/**
	 * {@link #validate(Object, ValidationResultTransform) AbstractValidator}
	 */
	@Override
	public <E> E validate(final T instance, final ValidationResultTransform<E> resultTransform) {
		return resultTransform.transform(validate(instance));
	}

	/**
	 * {@link #validate(Collection) AbstractValidator}
	 */
	@Override
	public List<ValidationResult> validate(final Collection<T> instances) {
		return Collections.unmodifiableList(instances.stream().map(this::validate).collect(Collectors.toList()));
	}

	/**
	 * {@link #validate(Collection, ValidationResultTransform) AbstractValidator}
	 */
	@Override
	public <E> List<E> validate(final Collection<T> instances, final ValidationResultTransform<E> resultTransform) {
		return Collections.unmodifiableList(instances.stream().map(instance -> this.validate(instance, resultTransform))
				.collect(Collectors.toList()));
	}

	/**
	 * {@link #apply(Object) AbstractValidator}
	 */
	@Override
	public boolean apply(final T instance) {
		this.initialize.init();
		ValidationContext.get().setProperty(this.property, instance);
		return ruleProcessor.process(instance, instance, rules);
	}

	/**
	 * {@link #ruleFor(Function) AbstractValidator}
	 */
	@Override
	public <P> RuleBuilderProperty<T, P> ruleFor(final Function<T, P> function) {
		final RuleBuilderPropertyImpl<T, P> rule = new RuleBuilderPropertyImpl<>(function);
		this.rules.add(rule);
		return rule;
	}

	/**
	 * {@link #ruleFor(String, Function) AbstractValidator}
	 */
	@Override
	public <P> RuleBuilderProperty<T, P> ruleFor(final String fieldName, final Function<T, P> function) {
		final RuleBuilderPropertyImpl<T, P> rule = new RuleBuilderPropertyImpl<>(fieldName, function);
		this.rules.add(rule);
		return rule;
	}

	/**
	 * {@link #ruleForEach(String, Function) AbstractValidator}
	 */
	@Override
	public <P> RuleBuilderCollection<T, P> ruleForEach(final String fieldName,
			final Function<T, Collection<P>> function) {
		final RuleBuilderCollectionImpl<T, P> rule = new RuleBuilderCollectionImpl<>(fieldName, function);
		this.rules.add(rule);
		return rule;
	}

	/**
	 * {@link #ruleForEach(Function) AbstractValidator}
	 */
	@Override
	public <P> RuleBuilderCollection<T, P> ruleForEach(final Function<T, Collection<P>> function) {
		final RuleBuilderCollectionImpl<T, P> rule = new RuleBuilderCollectionImpl<>(function);
		this.rules.add(rule);
		return rule;
	}

}
