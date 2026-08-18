package com.bornfire.valid.rule;

import java.util.function.Function;
import java.util.function.Predicate;
import com.bornfire.valid.Validator;
import com.bornfire.valid.exception.ValidationException;
import com.bornfire.valid.handler.HandlerInvalidField;

interface ValidationRule<T, P> extends Rule<P> {

  void when(final Predicate<P> when);

  void must(final Predicate<P> must);

  void withFieldName(final Function<?, String> fieldName);

  void withMessage(final Function<?, String> message);

  void withCode(final Function<?, String> code);

  void withAttemptedValue(final Function<?, Object> attemptedValue);

  void withHandlerInvalidField(final HandlerInvalidField<P> handleInvalid);

  void critical();

  void critical(final Class<? extends ValidationException> clazz);

  void whenever(final Predicate<P> whenever);

  void withValidator(final Validator<T> validator);

}
