package com.bornfire.valid.rule;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import com.bornfire.valid.Validator;
import com.bornfire.valid.annotation.CleanValidationContextException;
import com.bornfire.valid.builder.AttemptedValue;
import com.bornfire.valid.builder.Code;
import com.bornfire.valid.builder.Critical;
import com.bornfire.valid.builder.FieldName;
import com.bornfire.valid.builder.HandleInvalidField;
import com.bornfire.valid.builder.Message;
import com.bornfire.valid.builder.Must;
import com.bornfire.valid.builder.RuleBuilderCollection;
import com.bornfire.valid.builder.WhenCollection;
import com.bornfire.valid.builder.WheneverCollection;
import com.bornfire.valid.builder.WithValidator;
import com.bornfire.valid.context.ValidationContext;
import com.bornfire.valid.exception.ValidationException;
import com.bornfire.valid.handler.HandlerInvalidField;

public class RuleBuilderCollectionImpl<T, P> extends AbstractRuleBuilder<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> implements RuleBuilderCollection<T, P>, WhenCollection<T, P>, WheneverCollection<T, P> {

  private final Collection<Rule<Collection<P>>> rules = new LinkedList<>();

  private final RuleProcessorStrategy ruleProcessor = RuleProcessorStrategy.getFailFast();

  private ValidationRule<P, Collection<P>> currentValidation;

  public RuleBuilderCollectionImpl(final String fieldName, final Function<T, Collection<P>> function) {
    super(fieldName, function);
  }

  public RuleBuilderCollectionImpl(final Function<T, Collection<P>> function) {
    super(function);
  }

  @Override
  public boolean apply(final T instance) {
    final Collection<P> value = Objects.nonNull(instance) ? function.apply(instance) : null;
    return ruleProcessor.process(instance, value, rules);
  }

  @Override
  public WheneverCollection<T, P> whenever(final Predicate<Collection<P>> whenever) {
    this.currentValidation = new ValidatorRuleInternal(fieldName, whenever);
    this.rules.add(this.currentValidation);
    return this;
  }

  @Override
  public Must<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> must(final Predicate<Collection<P>> must) {
    this.currentValidation = new ValidationRuleInternal(fieldName, must);
    this.rules.add(this.currentValidation);
    return this;
  }

  @Override
  public Message<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withMessage(final String message) {
    this.currentValidation.withMessage(obj -> message);
    return this;
  }

  @Override
  public Message<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withMessage(final Function<T, String> message) {
    this.currentValidation.withMessage(message);
    return this;
  }

  @Override
  public Code<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withCode(final String code) {
    this.currentValidation.withCode(obj -> code);
    return this;
  }

  @Override
  public Code<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withCode(final Function<T, String> code) {
    this.currentValidation.withCode(code);
    return this;
  }

  @Override
  public FieldName<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withFieldName(final String fieldName) {
    this.currentValidation.withFieldName(obj -> fieldName);
    return this;
  }

  @Override
  public FieldName<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withFieldName(final Function<T, String> fieldName) {
    this.currentValidation.withFieldName(fieldName);
    return this;
  }

  @Override
  public AttemptedValue<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withAttempedValue(final Object attemptedValue) {
    this.currentValidation.withAttemptedValue(obj -> attemptedValue);
    return this;
  }

  @Override
  public AttemptedValue<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withAttempedValue(final Function<T, Object> attemptedValue) {
    this.currentValidation.withAttemptedValue(attemptedValue);
    return this;
  }

  @Override
  public HandleInvalidField<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> handlerInvalidField(final HandlerInvalidField<Collection<P>> handlerInvalidField) {
    this.currentValidation.withHandlerInvalidField(handlerInvalidField);
    return this;
  }

  @Override
  public Critical<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> critical() {
    this.currentValidation.critical();
    return this;
  }

  @Override
  public Critical<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> critical(final Class<? extends ValidationException> clazz) {
    this.currentValidation.critical(clazz);
    return this;
  }

  @Override
  public WithValidator<T, Collection<P>, WhenCollection<T, P>, WheneverCollection<T, P>> withValidator(final Validator<P> validator) {
    this.currentValidation.withValidator(validator);
    return this;
  }

  @Override
  public WhenCollection<T, P> when(final Predicate<Collection<P>> when) {
    this.currentValidation.when(when);
    return this;
  }

  class ValidationRuleInternal extends AbstractValidationRule<P, Collection<P>> {

    ValidationRuleInternal(final Function<T, String> fieldName, final Predicate<Collection<P>> must) {
      super.must(must);
      super.withFieldName(fieldName);
    }

    @Override
    public boolean support(final Collection<P> instance) {
      return Boolean.TRUE.equals(getWhen().test(instance));
    }

    @Override
    @CleanValidationContextException
    public boolean apply(final Object obj, final Collection<P> instance) {

      final boolean apply = getMust().test(instance);

      if (Boolean.FALSE.equals(apply)) {
        ValidationContext.get().addErrors(getHandlerInvalid().handle(obj, instance));
      }

      if (Objects.nonNull(getCriticalException()) && Boolean.FALSE.equals(apply)) {
        throw ValidationException.create(getCriticalException());
      }

      return !(Boolean.TRUE.equals(isCritical()) && Boolean.FALSE.equals(apply));

    }

  }

  class ValidatorRuleInternal extends AbstractValidationRule<P, Collection<P>> {

    ValidatorRuleInternal(final Function<T, String> fieldName, final Predicate<Collection<P>> whenever) {
      super.whenever(whenever);
      super.withFieldName(fieldName);
    }

    @Override
    public boolean support(final Collection<P> instance) {
      return Boolean.TRUE.equals(getWhenever().test(instance));
    }

    @Override
    @CleanValidationContextException
    public boolean apply(final Object obj, final Collection<P> instance) {

      final boolean apply = ruleProcessor.process(obj, instance, getValidator());

      if (Objects.nonNull(getCriticalException()) && Boolean.FALSE.equals(apply)) {
        throw ValidationException.create(getCriticalException());
      }

      return !(Boolean.TRUE.equals(isCritical()) && Boolean.FALSE.equals(apply));
    }

  }

}
