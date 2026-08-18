package com.bornfire.qrcode.decoder.mpm;

import com.bornfire.qrcode.core.exception.PresentedModeException;
import com.bornfire.qrcode.core.utils.TLVUtils;
import com.bornfire.qrcode.model.mpm.MerchantAccountInformationReserved;

// @formatter:off
public final class MerchantAccountInformationReservedDecoder extends DecoderMpm<MerchantAccountInformationReserved> {

  public MerchantAccountInformationReservedDecoder(final String source) {
    super(source);
  }

  @Override
  protected MerchantAccountInformationReserved decode() throws PresentedModeException {
    final String value = iterator.next();
    return new MerchantAccountInformationReserved(TLVUtils.valueOf(value));
  }

}
// @formatter:on
