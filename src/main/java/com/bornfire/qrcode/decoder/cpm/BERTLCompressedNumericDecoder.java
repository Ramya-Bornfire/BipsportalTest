package com.bornfire.qrcode.decoder.cpm;

import com.bornfire.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.bornfire.qrcode.core.utils.BERUtils;

public final class BERTLCompressedNumericDecoder extends DecoderCpm<BERTLCompressedNumeric> {

  public BERTLCompressedNumericDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected BERTLCompressedNumeric decode() {
    final byte[] value = iterator.next();
    return new BERTLCompressedNumeric(BERUtils.valueOfTag(value), BERUtils.valueOf(value));
  }

}