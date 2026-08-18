package com.bornfire.qrcode.decoder.cpm;

import com.bornfire.qrcode.core.exception.PresentedModeException;
import com.bornfire.qrcode.core.model.cpm.BERTag;
import com.bornfire.qrcode.core.utils.BERUtils;
import com.bornfire.qrcode.model.cpm.PayloadFormatIndicator;

public final class PayloadFormatIndicatorDecoder extends DecoderCpm<PayloadFormatIndicator> {

  public PayloadFormatIndicatorDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected PayloadFormatIndicator decode() throws PresentedModeException {

    final PayloadFormatIndicator result = new PayloadFormatIndicator();

    final byte[] value = iterator.next();

    final BERTag tag = new BERTag(BERUtils.valueOfTag(value));
    result.setTag(tag);
    result.setValue(BERUtils.valueOf(value));

    return result;

  }

}
