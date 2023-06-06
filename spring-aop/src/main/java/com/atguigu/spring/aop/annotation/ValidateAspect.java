package com.atguigu.spring.aop.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Date:2022/7/5
 * Author:ybc
 * Description:
 */
@Component
@Aspect
@Order(1)
public class ValidateAspect {

    //@Before("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
    @Before("com.atguigu.spring.aop.annotation.LoggerAspect.pointCut1()")
    public void beforeMethod1(){
        System.out.println("ValidateAspect-->前置通知1");
    }

    @After("com.atguigu.spring.aop.annotation.LoggerAspect.pointCut1()")
    public void afterMethod1(){
        System.out.println("ValidateAspect-->后置通知1");
    }
}
