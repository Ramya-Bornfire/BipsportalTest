package com.bornfire.qrcode.decoder.cpm;

import com.bornfire.qrcode.core.model.cpm.BERTLNumeric;
import com.bornfire.qrcode.core.utils.BERUtils;

public final class BERTLNumericDecoder extends DecoderCpm<BERTLNumeric> {

  public BERTLNumericDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected BERTLNumeric decode() {
    final byte[] value = iterator.next();
    return new BERTLNumeric(BERUtils.valueOfTag(value), BERUtils.valueOf(value));
  }

}