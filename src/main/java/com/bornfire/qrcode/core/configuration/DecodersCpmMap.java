package com.bornfire.qrcode.core.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bornfire.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.bornfire.qrcode.core.model.cpm.BERTLBinary;
import com.bornfire.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.bornfire.qrcode.core.model.cpm.BERTLNumeric;
import com.bornfire.qrcode.decoder.cpm.ApplicationSpecificTransparentTemplateDecoder;
import com.bornfire.qrcode.decoder.cpm.ApplicationTemplateDecoder;
import com.bornfire.qrcode.decoder.cpm.BERTLAlphanumericDecoder;
import com.bornfire.qrcode.decoder.cpm.BERTLBinaryDecoder;
import com.bornfire.qrcode.decoder.cpm.BERTLCompressedNumericDecoder;
import com.bornfire.qrcode.decoder.cpm.BERTLNumericDecoder;
import com.bornfire.qrcode.decoder.cpm.CommonDataTemplateDecoder;
import com.bornfire.qrcode.decoder.cpm.CommonDataTransparentTemplateDecoder;
import com.bornfire.qrcode.decoder.cpm.ConsumerPresentedModeDecoder;
import com.bornfire.qrcode.decoder.cpm.DecoderCpm;
import com.bornfire.qrcode.decoder.cpm.PayloadFormatIndicatorDecoder;
import com.bornfire.qrcode.model.cpm.ApplicationSpecificTransparentTemplate;
import com.bornfire.qrcode.model.cpm.ApplicationTemplate;
import com.bornfire.qrcode.model.cpm.CommonDataTemplate;
import com.bornfire.qrcode.model.cpm.CommonDataTransparentTemplate;
import com.bornfire.qrcode.model.cpm.ConsumerPresentedMode;
import com.bornfire.qrcode.model.cpm.PayloadFormatIndicator;

public final class DecodersCpmMap {

  private static final Map<Class<?>, Class<? extends DecoderCpm<?>>> MAP_DECODERS = new ConcurrentHashMap<>();

  static {
    MAP_DECODERS.put(BERTLBinary.class, BERTLBinaryDecoder.class);
    MAP_DECODERS.put(BERTLAlphanumeric.class, BERTLAlphanumericDecoder.class);
    MAP_DECODERS.put(BERTLCompressedNumeric.class, BERTLCompressedNumericDecoder.class);
    MAP_DECODERS.put(BERTLNumeric.class, BERTLNumericDecoder.class);
    MAP_DECODERS.put(ApplicationSpecificTransparentTemplate.class, ApplicationSpecificTransparentTemplateDecoder.class);
    MAP_DECODERS.put(ApplicationTemplate.class, ApplicationTemplateDecoder.class);
    MAP_DECODERS.put(CommonDataTemplate.class, CommonDataTemplateDecoder.class);
    MAP_DECODERS.put(CommonDataTransparentTemplate.class, CommonDataTransparentTemplateDecoder.class);
    MAP_DECODERS.put(ConsumerPresentedMode.class, ConsumerPresentedModeDecoder.class);
    MAP_DECODERS.put(PayloadFormatIndicator.class, PayloadFormatIndicatorDecoder.class);
  }

  private DecodersCpmMap() {
    super();
  }

  public static Class<? extends DecoderCpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
