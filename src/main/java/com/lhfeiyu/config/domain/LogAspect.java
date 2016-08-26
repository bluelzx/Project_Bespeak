package com.lhfeiyu.config.domain;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect implements Ordered{
	
	@Override  
    public int getOrder() {  
        return 1;  
    }

  @Pointcut("execution(* com.lhfeiyu.action..*.*(..))")
  public void anyMethod() {}// 定义一个切入点and args(method,args,target,ex)
  
  @AfterThrowing(pointcut="anyMethod()", throwing="ex")//  && args(Throwable)
  public void afterThrowing(JoinPoint joinPoint,Throwable ex) {
    addLog("AfterThrowing");
  }

  @Around("anyMethod()")
  public Object doBasicProfiling(ProceedingJoinPoint point) throws Throwable {
    addLog(point, null);
    Object[] args = point.getArgs();
    Object returnValue = point.proceed(args);
    addLog(point,returnValue);
    return returnValue;
  }

  private void addLog(String word) {
	  System.out.println("----------addLog----------"+word);
  }

  private void addLog(ProceedingJoinPoint point, Object returnValue) {
    addLog("Around");
  }
}
