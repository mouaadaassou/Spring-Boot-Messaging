package io.nodom.redis.event;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConversionEventListener implements
    ApplicationListener<CurrencyConversionEvent> {

  private static final String DASH_LINE = "==============================";
  private static final String NEXT_LINE = "\n";
  private static final Logger logger = LoggerFactory
      .getLogger(CurrencyConversionEventListener.class);

  @Override
  public void onApplicationEvent(CurrencyConversionEvent currencyConversionEvent) {
    Object obj = currencyConversionEvent.getSource();
    StringBuilder str = new StringBuilder(NEXT_LINE);
    str.append(DASH_LINE);
    str.append(NEXT_LINE);
    str.append(" Class: " + obj.getClass().getSimpleName());
    str.append(NEXT_LINE);
    str.append(" Message: " + currencyConversionEvent.getMessage());
    str.append(NEXT_LINE);
    str.append("  Value: " + currencyConversionEvent.getConversion());
    str.append(NEXT_LINE);
    str.append(DASH_LINE);
    logger.error(str.toString());

  }
}
