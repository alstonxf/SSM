package com.atguigu.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Date:2022/7/4
 * Author:ybc
 * Description:
 * 1、在切面中，需要通过指定的注解将方法标识为通知方法
 * @Before：前置通知，在目标对象方法执行之前执行
 * @After：后置通知，在目标对象方法的finally字句中执行
 * @AfterReturning：返回通知，在目标对象方法返回值之后执行
 * @AfterThrowing：异常通知，在目标对象方法的catch字句中执行
 *
 *     try{
 *          @Before
 *          method
 *          @AfterReturning
 *     }catch (Exception e){
 *          @AfterThrowing
 *         }
 *         finally {
 *          @After
 *         }
 *
 *
 * 2、切入点表达式：设置在标识通知的注解的value属性中
 * execution(public int com.atguigu.spring.aop.annotation.CalculatorImpl.add(int, int)
 * execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..)
 * 第一个*表示任意的访问修饰符和返回值类型
 * 第二个*表示类中任意的方法
 * ..表示任意的参数列表
 * 类的地方也可以使用*，表示包下所有的类
 *
 * 3、重用切入点表达式
 * //@Pointcut声明一个公共的切入点表达式
 * @Pointcut("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
 * public void pointCut(){}
 * 使用方式：@Before("pointCut()")
 *
 * 4、获取连接点的信息
 * 在通知方法的参数位置，设置JoinPoint类型的参数，就可以获取连接点所对应方法的信息
 * //获取连接点所对应方法的签名信息
 * Signature signature = joinPoint.getSignature();
 * //获取连接点所对应方法的参数
 * Object[] args = joinPoint.getArgs();
 *
 * 5、切面的优先级
 * 可以通过@Order注解的value属性设置优先级，默认值Integer的最大值
 * @Order注解的value属性值越小，优先级越高
 *
 */

@Aspect //将当前组件标识为切面
@Component //保证这个切面类能够放入IOC容器
public class LoggerAspect {

    //设定切入点为所有的CalculatorImpl所实现的方法，这样调用其每个方法时都会执行被标注的切面方法。
    @Pointcut("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
    public void pointCut1(){}

    //@Before("execution(public int com.atguigu.spring.aop.annotation.CalculatorImpl.add(int, int))")
    //@Before("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
    @Before("pointCut1()")
    public void beforeAdviceMethod(JoinPoint joinPoint) {
        //获取连接点所对应方法的签名信息 int com.atguigu.spring.aop.annotation.Calculator.div(int,int)
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        //获取连接点所对应方法的参数值 第一个参数是10，第二个参数是1
        Object[] args = joinPoint.getArgs();
        System.out.println("Before LoggerAspect，方法："+name+"，参数："+ Arrays.toString(args));
    }

    @After("pointCut1()")
    public void afterAdviceMethod(JoinPoint joinPoint){
        //获取连接点所对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("After LoggerAspect，方法："+signature.getName()+"，执行完毕");

    }

    /**
     * 在返回通知中若要获取目标对象方法的返回值
     * 只需要通过@AfterReturning注解的returning属性
     * 就可以将通知方法的某个参数指定为接收目标对象方法的返回值的参数
     */
    @AfterReturning(value = "pointCut1()", returning = "result")
    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result){
        //获取连接点所对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("AfterReturning LoggerAspect，方法："+signature.getName()+"，结果："+result);
    }

    /**
     * 在异常通知中若要获取目标对象方法的异常
     * 只需要通过AfterThrowing注解的throwing属性
     * 就可以将通知方法的某个参数指定为接收目标对象方法出现的异常的参数
     */
    @AfterThrowing(value = "pointCut1()", throwing = "ex")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable ex){
        //获取连接点所对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("AfterThrowing LoggerAspect，方法："+signature.getName()+"，异常："+ex);
    }

//    @Around("pointCut1()")
    //环绕通知的方法的返回值一定要和目标对象方法的返回值一致
    public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            System.out.println("环绕通知-->前置通知");
            //表示目标对象方法的执行
            Object[] args = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            String name = signature.getName();
            System.out.println("环绕通知 获取方法名："+name+"，参数："+ Arrays.toString(args));

            Class declaringType = signature.getDeclaringType();
            System.out.println("declaringType:"+declaringType.getSimpleName());

            String declaringTypeName = signature.getDeclaringTypeName();
            System.out.println("declaringTypeName:"+declaringTypeName);

            result = joinPoint.proceed();//这个才是执行方法，上面的都是获取相关的信息而已。
            System.out.println("环绕通知-->返回通知");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->异常通知");
        } finally {
            System.out.println("环绕通知-->后置通知");
        }
        return result;
    }

}
