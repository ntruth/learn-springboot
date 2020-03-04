package com.ucmed.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class BrokerAspect {

    /**
     * 定义切入点 切入点为com.ucmed.aop.controller.*
     * 通过@Pointcut注解声明频繁的使用的切点表达式
     */
    @Pointcut("execution(* com.ucmed.aop.controller.BasketballController.*(..)))")
    public void BrokerAspect() {
    }

    /**
     * 在连接点执行之前执行的通知
     */
    @Before("BrokerAspect()")
    public void doBeforeGame() {
        System.out.println("经纪人正在处理球星赛前事务");
    }

    @After("BrokerAspect()")
    public void doAfterGame() {
        System.out.println("经纪人为球星的精彩表演热烈鼓掌");
    }

    @AfterReturning("BrokerAspect()")
    public void doAfterReturningGame() {
        System.out.println("返回通知：经纪人为球星的表现疯狂鼓掌");
    }

    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowing() {
        System.out.println("异常通知：球迷要求退票");
    }


}
