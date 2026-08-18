package com.bornfire.qrcode.core.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bornfire.qrcode.core.model.mpm.TagLengthString;
import com.bornfire.qrcode.decoder.mpm.AdditionalDataFieldDecoder;
import com.bornfire.qrcode.decoder.mpm.AdditionalDataFieldTemplateDecoder;
import com.bornfire.qrcode.decoder.mpm.DecoderMpm;
import com.bornfire.qrcode.decoder.mpm.MerchantAccountInformationReservedAdditionalDecoder;
import com.bornfire.qrcode.decoder.mpm.MerchantAccountInformationReservedDecoder;
import com.bornfire.qrcode.decoder.mpm.MerchantAccountInformationTemplateDecoder;
import com.bornfire.qrcode.decoder.mpm.MerchantInformationLanguageDecoder;
import com.bornfire.qrcode.decoder.mpm.MerchantInformationLanguageTemplateDecoder;
import com.bornfire.qrcode.decoder.mpm.MerchantPresentedModeDecoder;
import com.bornfire.qrcode.decoder.mpm.PaymentSystemSpecificDecoder;
import com.bornfire.qrcode.decoder.mpm.PaymentSystemSpecificTemplateDecoder;
import com.bornfire.qrcode.decoder.mpm.StringDecoder;
import com.bornfire.qrcode.decoder.mpm.TagLengthStringDecoder;
import com.bornfire.qrcode.decoder.mpm.UnreservedDecoder;
import com.bornfire.qrcode.decoder.mpm.UnreservedTemplateDecoder;
import com.bornfire.qrcode.model.mpm.AdditionalDataField;
import com.bornfire.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.bornfire.qrcode.model.mpm.MerchantAccountInformationReserved;
import com.bornfire.qrcode.model.mpm.MerchantAccountInformationReservedAdditional;
import com.bornfire.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.bornfire.qrcode.model.mpm.MerchantInformationLanguage;
import com.bornfire.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.bornfire.qrcode.model.mpm.MerchantPresentedMode;
import com.bornfire.qrcode.model.mpm.PaymentSystemSpecific;
import com.bornfire.qrcode.model.mpm.PaymentSystemSpecificTemplate;
import com.bornfire.qrcode.model.mpm.Unreserved;
import com.bornfire.qrcode.model.mpm.UnreservedTemplate;

public final class DecodersMpmMap {

  private static final Map<Class<?>, Class<? extends DecoderMpm<?>>> MAP_DECODERS = new ConcurrentHashMap<>();

  static {
    MAP_DECODERS.put(String.class, StringDecoder.class);
    MAP_DECODERS.put(TagLengthString.class, TagLengthStringDecoder.class);
    MAP_DECODERS.put(MerchantPresentedMode.class, MerchantPresentedModeDecoder.class);
    MAP_DECODERS.put(AdditionalDataFieldTemplate.class, AdditionalDataFieldTemplateDecoder.class);
    MAP_DECODERS.put(AdditionalDataField.class, AdditionalDataFieldDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguageTemplate.class, MerchantInformationLanguageTemplateDecoder.class);
    MAP_DECODERS.put(MerchantInformationLanguage.class, MerchantInformationLanguageDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationTemplate.class, MerchantAccountInformationTemplateDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationReserved.class, MerchantAccountInformationReservedDecoder.class);
    MAP_DECODERS.put(MerchantAccountInformationReservedAdditional.class, MerchantAccountInformationReservedAdditionalDecoder.class);
    MAP_DECODERS.put(UnreservedTemplate.class, UnreservedTemplateDecoder.class);
    MAP_DECODERS.put(Unreserved.class, UnreservedDecoder.class);
    MAP_DECODERS.put(PaymentSystemSpecificTemplate.class, PaymentSystemSpecificTemplateDecoder.class);
    MAP_DECODERS.put(PaymentSystemSpecific.class, PaymentSystemSpecificDecoder.class);
  }

  private DecodersMpmMap() {
    super();
  }

  public static Class<? extends DecoderMpm<?>> getDecoder(final Class<?> clazz) {
    return MAP_DECODERS.get(clazz);
  }

}
