package com.atguigu.spring.aop.nospring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculatorDynamicProxyFactory {
    private Object target;

    public CalculatorDynamicProxyFactory(Object target){
        this.target = target;
    }

    public void getMethod(Object target) throws InvocationTargetException, IllegalAccessException {
        Class<?> aClass = target.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.invoke(target);
        }
    }

    public Object getProxy(){
        ClassLoader classLoader = target.getClass().getClassLoader();//加载动态生成的代理类的类加载器
        Class<?>[] interfaces = target.getClass().getInterfaces();//实现的所有对象
        InvocationHandler invocationHandler = new InvocationHandler() { //代理类重写接口中的抽象方法。

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                //环绕通知
                try{
                    System.out.println("[动态代理-前置通知][日志]"+method.getName()+",参数"+ Arrays.toString(args));
                    result = method.invoke(target, args);
                    System.out.println("[动态代理-返回通知][日志]"+method.getName()+",结果"+ result);
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("[动态代理-异常通知][日志]"+method.getName()+",异常"+ e.getMessage());
                } finally {
                    System.out.println("[动态代理-后置通知][日志]"+method.getName()+",方法执行完毕");
                }
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }

}
