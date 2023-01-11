package com.assignment.spring.aop.base;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IBaseAspect {

    @AfterThrowing(value = "methods()", throwing = "e")
    default void adviceThrowing(JoinPoint joinPoint, Exception e) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        logger.error("After throwing generic exception: {} - {} in method: {}", e.getMessage(), e.getCause(), joinPoint.getSignature());
    }

    @AfterReturning(value = "methods()")
    default void adviceAfterReturning(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        logger.info("After returning from method: {}", joinPoint.getSignature());
    }

    @After(value = "methods()")
    default void adviceAfter(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        logger.info("After method: {}", joinPoint.getSignature());
    }
}