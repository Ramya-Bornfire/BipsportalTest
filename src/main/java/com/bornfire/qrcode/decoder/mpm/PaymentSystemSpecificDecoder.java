package com.bornfire.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

import com.bornfire.qrcode.core.exception.DuplicateTagException;
import com.bornfire.qrcode.core.exception.InvalidTagException;
import com.bornfire.qrcode.core.exception.PresentedModeException;
import com.bornfire.qrcode.core.model.mpm.TagLengthString;
import com.bornfire.qrcode.core.utils.TLVUtils;
import com.bornfire.qrcode.model.mpm.PaymentSystemSpecific;
import com.bornfire.qrcode.model.mpm.constants.MerchantAccountInformationFieldCodes;
import com.bornfire.qrcode.model.mpm.constants.PaymentSystemSpecificFieldCodes;

// @formatter:off
public final class PaymentSystemSpecificDecoder extends DecoderMpm<PaymentSystemSpecific> {

  private static final Map<String, Entry<Class<?>, BiConsumer<PaymentSystemSpecific, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_GLOBALLY_UNIQUE_IDENTIFIER, consumerTagLengthValue(String.class, PaymentSystemSpecific::setGloballyUniqueIdentifier));
    mapConsumers.put(MerchantAccountInformationFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC, consumerTagLengthValue(TagLengthString.class, PaymentSystemSpecific::addPaymentSystemSpecific));
    
    //new added fields
    mapConsumers.put(MerchantAccountInformationFieldCodes.PAYEE_PARTICIPANT_CODE, consumerTagLengthValue(String.class, PaymentSystemSpecific::setPayeeParticipantCode));
    mapConsumers.put(MerchantAccountInformationFieldCodes.MERCHANT_ACCOUNT_NUMBER, consumerTagLengthValue(String.class, PaymentSystemSpecific::setMerchantAccountNumber));
    mapConsumers.put(MerchantAccountInformationFieldCodes.MERCHANT_ID, consumerTagLengthValue(String.class, PaymentSystemSpecific::setMerchantID));

  }

  public PaymentSystemSpecificDecoder(final String source) {
    super(TLVUtils.valueOf(source));
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected PaymentSystemSpecific decode() throws PresentedModeException {

    final Set<String> tags = new HashSet<>();

    final PaymentSystemSpecific result = new PaymentSystemSpecific();

    while(iterator.hasNext()) {
      final String value = iterator.next();

      final String tag = TLVUtils.valueOfTag(value);

      final String derivateId = derivateId(tag);

      if (tags.contains(tag)) {
        throw new DuplicateTagException("PaymentSystemSpecific", tag, value);
      }

      tags.add(tag);

      final Entry<Class<?>, BiConsumer<PaymentSystemSpecific, ?>> entry = mapConsumers.get(derivateId);

      if (Objects.isNull(entry)) {
        throw new InvalidTagException("PaymentSystemSpecific", tag, value);
      }

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, DecoderMpm.decode(value, clazz));
    }

    return result;
  }

  private String derivateId(final String id) {

    if (betweenPaymentNetworkSpecificRange(id)) {
      return PaymentSystemSpecificFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC;
    }

    return id;
  }

  private boolean betweenPaymentNetworkSpecificRange(final String value) {
    return value.compareTo(PaymentSystemSpecificFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC_START) >= 0
        && value.compareTo(PaymentSystemSpecificFieldCodes.ID_PAYMENT_NETWORK_SPECIFIC_END) <= 0;
  }

}
// @formatter:on
