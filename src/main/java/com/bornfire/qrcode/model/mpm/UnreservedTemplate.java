package com.bornfire.qrcode.model.mpm;

import com.bornfire.qrcode.core.model.TLV;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;


public class UnreservedTemplate implements TLV<String, Unreserved> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private Unreserved value;

  public UnreservedTemplate() {
    super();
  }

  public UnreservedTemplate(final String tag) {
    this.setTag(tag);
  }

  public UnreservedTemplate(final String tag, final String globallyUniqueIdentifier) {
    this.setTag(tag);
    this.setValue(new Unreserved(globallyUniqueIdentifier));
  }

  public void addContextSpecificData(final String tag, final String value) {   
    this.setValue(Optional.ofNullable(this.getValue()).orElse(new Unreserved()));
    this.getValue().addContextSpecificData(tag, value);
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public Unreserved getValue() {
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

    return String.format("%s%02d%s", tag, string.length(), string);
  }

public void setTag(String tag) {
	this.tag = tag;
}

public void setValue(Unreserved value) {
	this.value = value;
}
  
  
}
