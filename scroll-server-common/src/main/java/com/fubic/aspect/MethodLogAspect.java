package com.fubic.aspect;


import com.google.gson.Gson;
import org.apache.tomcat.jni.Proc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(11)//@Order指示切面优先级，数字越小优先级越高
public class MethodLogAspect {
    private final Logger logger = LoggerFactory.getLogger(MethodLogAspect.class);

    @Pointcut("@annotation(com.fubic.aspect.annotation.MethodLog)")
    public void methodLog() {
    }

    //环绕通知计算方法执行时间，需要返回result否则连接点执行结果会丢失
    @Around("methodLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        logger.info("计算结果为    :{}", new Gson().toJson(result));
        StringBuilder sb = new StringBuilder("方法： ");
        sb.append(joinPoint.getSignature().toString());
//        sb.append(joinPoint.getSignature().getName());
        logger.info("{} 执行时间为 {} ms", sb.toString(), stopWatch.getTotalTimeMillis());
        return result;
    }
}

