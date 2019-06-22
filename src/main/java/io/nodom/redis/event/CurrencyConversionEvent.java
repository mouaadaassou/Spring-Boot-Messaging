package io.nodom.redis.event;

import io.nodom.redis.domain.CurrencyConversion;
import org.springframework.context.ApplicationEvent;

public class CurrencyConversionEvent extends ApplicationEvent {

  private static final Long serialVersionUID = -4481493963350551884L;
  private CurrencyConversion currencyConversion;
  private String message;

  public CurrencyConversionEvent(Object source, CurrencyConversion currencyConversion) {
    super(source);
    this.currencyConversion = currencyConversion;
  }

  public CurrencyConversionEvent(Object source, String message,
      CurrencyConversion currencyConversion) {
    super(source);
    this.message = message;
    this.currencyConversion = currencyConversion;
  }

  public CurrencyConversion getConversion() {
    return this.currencyConversion;
  }

  public String getMessage() {
    return this.message;
  }
}
