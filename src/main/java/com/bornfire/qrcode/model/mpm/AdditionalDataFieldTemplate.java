package com.bornfire.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.bornfire.qrcode.core.model.TLV;
import com.bornfire.qrcode.model.mpm.constants.MerchantPresentedModeCodes;



public class AdditionalDataFieldTemplate implements TLV<String, AdditionalDataField> {

  private static final long serialVersionUID = 2232991556283235445L;

  private AdditionalDataField value;

  @Override
  public String getTag() {
    return MerchantPresentedModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE;
  }

  @Override
  public AdditionalDataField getValue() {
    return value;
  }

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", getTag(), string.length(), string);
  }

public void setValue(AdditionalDataField value) {
	this.value = value;
}

}
