package com.assignment.spring.aop.service;

import com.assignment.spring.aop.base.IBaseAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WeatherServiceAspect implements IBaseAspect {

    @Pointcut("execution(public * com.assignment.spring.services.impl.WeatherService.*(..))")
    public void methods() {
    }
}
