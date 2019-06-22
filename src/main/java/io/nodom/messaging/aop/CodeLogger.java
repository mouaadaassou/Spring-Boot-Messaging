package io.nodom.messaging.aop;


import io.nodom.messaging.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CodeLogger {


  private static final Logger logger = LoggerFactory.getLogger(CodeLogger.class);
  private static final String DASH_LINE = "==============================";
  private static final String NEXT_LINE = "\n";

  @Pointcut("execution(@io.nodom.messaging.annotation.Log * io.nodom.messaging..*.*(..)) && @annotation(codeLog)")
  public void codeLogger(Log codeLog) {
  }

  @Before("codeLogger(codeLog)")
  public void doCodeLogger(JoinPoint jp, Log codeLog) {
    StringBuilder str = new StringBuilder(NEXT_LINE);
    str.append(DASH_LINE);
    str.append(NEXT_LINE);
    str.append("Class " + jp.getTarget().getClass().getSimpleName());
    str.append(NEXT_LINE);
    str.append("Method " + jp.getSignature().getName());
    str.append(NEXT_LINE);
    if (codeLog.printParamsValues()) {
      Object[] args = jp.getArgs();
      str.append(NEXT_LINE);
      for (Object object : args) {
        str.append("Params : " + object.getClass().getSimpleName());
        str.append(NEXT_LINE);

        try {
          String methodToCall = codeLog.callMethodWithNoParamsToString();
          if ("toStrting".equals(methodToCall)) {
            str.append(" Value: " + object);
          } else {
            str.append(" Value: " + object.getClass().getDeclaredMethod(methodToCall, new Class[]{})
                .invoke(object, new Object[]{}));
          }
        } catch (Exception e) {
          str.append(" Value: [ERROR]> " + e.getMessage());
        }
        str.append(NEXT_LINE);
      }
    }
    str.append(DASH_LINE);
    logger.info(str.toString());
  }
}
