package io.nodom.redis.aop;


import io.nodom.redis.event.CurrencyConversionEvent;
import io.nodom.redis.exception.BadCodeRuntimeException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CurrencyConversionAudit {

  private ApplicationEventPublisher publisher;

  public CurrencyConversionAudit(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Pointcut("execution(* io.nodom.redis.service.*Service.*(..))")
  public void exceptionPointCut() {
  }

  @AfterThrowing(pointcut = "exceptionPointCut()", throwing = "ex")
  public void badCodeException(JoinPoint jp, BadCodeRuntimeException ex) {
    if (ex.getConversion() != null) {
      publisher.publishEvent(
          new CurrencyConversionEvent(jp.getTarget(), ex.getMessage(), ex.getConversion()));
    }
  }
}
