package com.assignment.spring.aop.controller;

import com.assignment.spring.aop.base.IBaseAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect implements IBaseAspect {

    @Pointcut("execution(public * com.assignment.spring.controllers.WeatherController.*(..))")
    public void methods() {
    }
}
