package io.nodom.messaging.listener;

import io.nodom.messaging.annotation.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;


@Component
public class RestApiEventsListener {

  private static final String LATEST = "/currency/latest";


  private CounterService counterService;

  public RestApiEventsListener(@Qualifier("counterService") CounterService counterService) {
    this.counterService = counterService;
  }

  @EventListener
  @Log(printParamsValues = true)
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ServletRequestHandledEvent) {
      if (((ServletRequestHandledEvent) event).getRequestUrl().equals(LATEST)) {
        counterService.increment("url.currency.latest.hits");
      }
    }
  }
}
