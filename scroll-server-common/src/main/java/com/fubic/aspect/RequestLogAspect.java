package com.fubic.aspect;

import com.fubic.aspect.annotation.RequestLog;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(10)
public class RequestLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    //定义切点
    @Pointcut("@annotation(com.fubic.aspect.annotation.RequestLog)")
    public void requestLog() {
    }

    //前置通知
    @Before("requestLog()")
    public void doBefore(JoinPoint jointPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获得方法注解中description中的描述信息
        String methodDescription = getAspectLogDescription(jointPoint);

        logger.info("============ Start ============");
        //打印请求url
        logger.info("URl              :{}", request.getRequestURL().toString());
        //打印获取方法描述信息
        logger.info("Description      :{}", methodDescription);
        //打印HTTP method
        logger.info("HTTP Method      :{}", request.getMethod());
        //打印发起请求的IP
        logger.info("IP               :{}", request.getRemoteHost());
        //打印请求的入参
        logger.info("Request Args     :{}", new Gson().toJson(jointPoint.getArgs()));
    }

    //环绕通知
    @Around("requestLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
        //执行连接点目标函数，并获得结果
        Object result = proceedingJoinPoint.proceed();
//        stopWatch.stop();
        logger.info("Response Args    :{}", new Gson().toJson(result));
        //打印耗时
//        logger.info("Time-Consuming   :{} ms", stopWatch.getTotalTimeMillis());
        return result;
    }

    //后置通知
    @After("requestLog()")
    public void doAfter() throws Throwable {
        logger.info("============ End ============");
    }


    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        //获得该连接点目标函数的类的全类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获得方法名
        String methodName = joinPoint.getSignature().getName();
        //连接点目标函数的入参
        Object[] arguments = joinPoint.getArgs();
        //通过反射获得类
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder sb = new StringBuilder("");
        for (Method method : methods) {
            //一个类中可能有很多方法
            if (method.getName().equals(methodName)) {
                //一个方法也可能被多次重载
                Class[] classes = method.getParameterTypes();
                if (classes.length == arguments.length) {
                    //该注解类对应的注解
                    sb.append(method.getAnnotation(RequestLog.class).description());
                    break;
                }
            }
        }
        return sb.toString();
    }


}
