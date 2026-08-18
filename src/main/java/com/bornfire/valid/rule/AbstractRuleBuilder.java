package com.bornfire.valid.rule;

import java.util.function.Function;

import com.bornfire.valid.builder.AttemptedValue;
import com.bornfire.valid.builder.Code;
import com.bornfire.valid.builder.Critical;
import com.bornfire.valid.builder.FieldName;
import com.bornfire.valid.builder.HandleInvalidField;
import com.bornfire.valid.builder.Message;
import com.bornfire.valid.builder.Must;
import com.bornfire.valid.builder.When;
import com.bornfire.valid.builder.Whenever;
import com.bornfire.valid.builder.WithValidator;

abstract class AbstractRuleBuilder<T, P, W extends When<T, P, W, N>, N extends Whenever<T, P, W, N>>
    implements Must<T, P, W, N>, Message<T, P, W, N>, FieldName<T, P, W, N>, Code<T, P, W, N>, Critical<T, P, W, N>, WithValidator<T, P, W, N>, HandleInvalidField<T, P, W, N>, AttemptedValue<T, P, W, N>, Rule<T> {

  protected final Function<T, String> fieldName;

  protected final Function<T, P> function;

  protected AbstractRuleBuilder(final Function<T, String> fieldName, final Function<T, P> function) {
    this.fieldName = fieldName;
    this.function = function;
  }

  protected AbstractRuleBuilder(final String fieldName, final Function<T, P> function) {
    this(obj -> fieldName, function);
  }

  protected AbstractRuleBuilder(final Function<T, P> function) {
    this(obj -> null, function);
  }

}
