package com.atguigu.spring.test;

import com.atguigu.spring.aop.nospring.*;
import org.junit.Test;

public class noSpringTest {
    @Test
    public void TestCaculatorLogImpl(){
        CalculatorLogImpl calculatorLog = new CalculatorLogImpl();
        int add = calculatorLog.add(1, 2);
        int sub = calculatorLog.sub(2, 1);
        int mul = calculatorLog.mul(3, 5);
        int div = calculatorLog.div(8, 2);
    }

    @Test
    public void TestCaculatorStaticProxy(){
        CalculatorPureImpl calculatorPure = new CalculatorPureImpl();
        CalculatorStaticProxy calculatorStaticProxy = new CalculatorStaticProxy(calculatorPure);
        int add = calculatorStaticProxy.add(1, 2);
        int sub = calculatorStaticProxy.sub(2, 1);
        int mul = calculatorStaticProxy.mul(3, 5);
        int div = calculatorStaticProxy.div(8, 2);
    }

    @Test
    public void TestCalculatorDynamicProxyFactory(){
        CalculatorPureImpl calculatorPure = new CalculatorPureImpl();
        CalculatorDynamicProxyFactory calculatorDynamicProxyFactory = new CalculatorDynamicProxyFactory(calculatorPure);
        Calculator proxy = (Calculator) calculatorDynamicProxyFactory.getProxy();//注意:只能强制转化为接口类，不能是实现类
        int add = proxy.add(1, 2);
        int sub = proxy.sub(2, 1);
        int mul = proxy.mul(3, 5);
        int div = proxy.div(8, 2);
    }


}
