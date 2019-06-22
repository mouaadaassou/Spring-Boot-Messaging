package io.nodom.redis.aop;

import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import io.nodom.redis.annotation.ToUpper;

@Aspect
@Component
public class CurrencyCodeAudit {

  @Pointcut("execution(* io.nodom.redis.service.*Service.*(.., @io.nodom.redis.annotation.ToUpper (*),..))")
  public void methodPointcut() {
  }

  @Around("methodPointcut()")
  public Object codeAudit(ProceedingJoinPoint pjp) throws Throwable {
    Object[] args = pjp.getArgs();
    Parameter[] parameters = ((MethodSignature) pjp.getSignature()).getMethod().getParameters();

    IntStream.range(0, args.length)
        .mapToObj(index -> (parameters[index].isAnnotationPresent(ToUpper.class)) ? (new String(
            args[index].toString().toUpperCase())) : (args[index]))
        .forEach(System.out::println);

    return pjp.proceed(IntStream.range(0, args.length)
        .mapToObj(index -> (parameters[index].isAnnotationPresent(ToUpper.class)) ? (new String(
            args[index].toString().toUpperCase())) : (args[index]))
        .toArray());
  }

}
