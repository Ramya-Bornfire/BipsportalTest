package com.bornfire.qrcode.decoder.mpm;

import com.bornfire.qrcode.core.exception.PresentedModeException;
import com.bornfire.qrcode.model.mpm.AdditionalDataField;
import com.bornfire.qrcode.model.mpm.AdditionalDataFieldTemplate;

// @formatter:off
public final class AdditionalDataFieldTemplateDecoder extends DecoderMpm<AdditionalDataFieldTemplate> {

  public AdditionalDataFieldTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected AdditionalDataFieldTemplate decode() throws PresentedModeException {
    final AdditionalDataFieldTemplate result = new AdditionalDataFieldTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();
      result.setValue(DecoderMpm.decode(value, AdditionalDataField.class));
    }

    return result;
  }

}
// @formatter:on
