package com.poc.sst.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeCountAspect {
    @Around("@annotation(ExecutionTimeCount)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
       var executionTimeStart = System.currentTimeMillis();
       var proceed = proceedingJoinPoint.proceed();
       var executionTime = System.currentTimeMillis() - executionTimeStart;
       System.out.println(proceedingJoinPoint.getSignature() + " executed in " + executionTime + " ms" );

       return proceed;
    }
}
