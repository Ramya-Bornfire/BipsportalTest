package com.bornfire.qrcode.decoder.mpm;

import com.bornfire.qrcode.core.exception.PresentedModeException;
import com.bornfire.qrcode.core.utils.TLVUtils;
import com.bornfire.qrcode.model.mpm.Unreserved;
import com.bornfire.qrcode.model.mpm.UnreservedTemplate;

// @formatter:off
public final class UnreservedTemplateDecoder extends DecoderMpm<UnreservedTemplate> {

  public UnreservedTemplateDecoder(final String source) {
    super(source);
  }

  @Override
  protected UnreservedTemplate decode() throws PresentedModeException {
    final UnreservedTemplate result = new UnreservedTemplate();

    while(iterator.hasNext()) {
      final String value = iterator.next();
      result.setTag(TLVUtils.valueOfTag(value));
      result.setValue(DecoderMpm.decode(value, Unreserved.class));
    }

    return result;
  }

}
// @formatter:on
