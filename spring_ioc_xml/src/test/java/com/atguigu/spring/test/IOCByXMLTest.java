package com.atguigu.spring.test;

import com.atguigu.spring.pojo.Clazz;
import com.atguigu.spring.pojo.Person;
import com.atguigu.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Date:2022/7/1
 * Author:ybc
 * Description:
 */
public class IOCByXMLTest {

    /**
     * 获取bean的三种方式：
     * 1、根据bean的id获取
     * 2、根据bean的类型获取
     * 注意：根据类型获取bean时，要求IOC容器中有且只有一个类型匹配的bean
     * 若没有任何一个类型匹配的bean，此时抛出异常：NoSuchBeanDefinitionException
     * 若有多个类型匹配的bean，此时抛出异常：NoUniqueBeanDefinitionException
     * 3、根据bean的id和类型获取
     * 结论：
     * 根据类型来获取bean时，在满足bean唯一性的前提下
     * 其实只是看：『对象 instanceof 指定的类型』的返回结果
     * 只要返回的是true就可以认定为和类型匹配，能够获取到。
     * 即通过bean的类型、bean所继承的类的类型、bean所实现的接口的类型都可以获取bean
     */

    //2.2.2、实验二：获取bean
    @Test
    public void testIOC(){
        //获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        //获取bean
//             * 1、根据bean的id获取
        Student studentOne = (Student) ioc.getBean("studentOne");
//             * 2、根据bean的类型获取
//                * 注意：根据类型获取bean时，要求IOC容器中有且只有一个类型匹配的bean
//                * 若没有任何一个类型匹配的bean，此时抛出异常：NoSuchBeanDefinitionException
//                * 若有多个类型匹配的bean，此时抛出异常：NoUniqueBeanDefinitionException
//        Student student = ioc.getBean(Student.class);
//
//             * 3、根据bean的id和类型获取
        Student student = ioc.getBean("studentOne", Student.class);
        System.out.println(student);

        //只有当xml中只有唯一的由这个接口实现的类时，才可以通过接口获取类型。否则报NoUniqueBeanDefinitionException错误
//        Person person = ioc.getBean(Person.class);
//        System.out.println(person);
    }

    //2.2.3、实验三：依赖注入之setter注入
    @Test
    public void testDIwithSetter(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");
        Student studentTwo = (Student)classPathXmlApplicationContext.getBean("studentTwo");
        Integer age = studentTwo.getAge();
        String sname = studentTwo.getSname();
        Integer sid = studentTwo.getSid();
        String gender = studentTwo.getGender();
        System.out.println(age+sname+sid+gender);
        studentTwo.toString();
    }

    //2.2.4、实验四：依赖注入之构造器注入
    @Test
    public void testDIwithConstruct(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");
        Student studentThree = classPathXmlApplicationContext.getBean("studentThree", Student.class);
        System.out.println(studentThree);
    }


    @Test
    public void testDI(){
        //获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        //获取bean
        Student student4 = ioc.getBean("studentFour", Student.class);//特殊字面量
        System.out.println(student4);
        Student student5 = ioc.getBean("studentFive", Student.class);//array,map,class 属性
        System.out.println(student5);
        /*Clazz clazz = ioc.getBean("clazzInner", Clazz.class);//为类类型属性赋值
        System.out.println(clazz);*/
        /*Clazz clazz = ioc.getBean("clazzOne", Clazz.class);
        System.out.println(clazz);*/
    }

}
