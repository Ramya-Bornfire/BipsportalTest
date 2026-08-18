package com.bornfire.qrcode.decoder.mpm;

import com.bornfire.qrcode.core.exception.PresentedModeException;
import com.bornfire.qrcode.model.mpm.MerchantInformationLanguage;
import com.bornfire.qrcode.model.mpm.MerchantInformationLanguageTemplate;

// @formatter:off
public final class MerchantInformationLanguageTemplateDecoder extends DecoderMpm<MerchantInformationLanguageTemplate> {

  public MerchantInformationLanguageTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantInformationLanguageTemplate decode() throws PresentedModeException {
    final MerchantInformationLanguageTemplate result = new MerchantInformationLanguageTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();
      result.setValue(DecoderMpm.decode(value, MerchantInformationLanguage.class));
    }

    return result;
  }

}

// @formatter:on
