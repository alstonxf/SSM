package com.atguigu.spring.test;

import com.atguigu.spring.pojo.HelloWorld;
import com.atguigu.spring.pojo.person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

public class test2 {
//    ## 2.2、基于XML管理bean
//        ### 2.2.1、实验一：入门案例
    public static void main(String[] args) {
        //获取IOC容器
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取IOC容器中的bean
//        HelloWorld helloworld = (HelloWorld)classPathXmlApplicationContext.getBean("helloworld");//方式一：根据id获取
//        HelloWorld helloworld = classPathXmlApplicationContext.getBean(HelloWorld.class);//方式二：根据类型获取
//        HelloWorld helloworld = classPathXmlApplicationContext.getBean("helloworld", HelloWorld.class);//方式三：根据id和类型
        HelloWorld helloworld = (HelloWorld)classPathXmlApplicationContext.getBean("helloworld", "a","b");

        helloworld.sayHello();

        //测试获取student
        //如果组件类实现了接口，根据接口类型可以获取 bean 吗？
        //> 可以，前提是bean唯一(person是个接口，stuent实现了这个接口，然后在xml中，student唯一)
        person student1 = classPathXmlApplicationContext.getBean(person.class);
        student1.say();
    }
}
