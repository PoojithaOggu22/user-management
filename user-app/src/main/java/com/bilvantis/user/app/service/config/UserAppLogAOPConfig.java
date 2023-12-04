package com.bilvantis.user.app.service.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class UserAppLogAOPConfig {

    @Around("execution (* com.bilvantis.rewards.api.service.*..*(..))")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        log.info("RewardsAppLogAOP || {} method START", joinPoint.getSignature().getName());

        Object response = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("RewardsAppLogAOP || {} method END", joinPoint.getSignature().getName());

        log.info("Total time taken for method: {} execution is {} seconds", joinPoint.getSignature().getName(), (endTime-startTime)/1000);

        return response;
    }
}