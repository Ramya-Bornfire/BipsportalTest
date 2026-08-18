package com.bornfire.valid.transform;

import com.bornfire.valid.context.ValidationResult;

public interface ValidationResultTransform<E> {

  /**
   *
   * @param validationResult
   * @return
   */
  E transform(final ValidationResult validationResult);

}
