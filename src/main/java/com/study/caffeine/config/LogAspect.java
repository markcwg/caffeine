package com.study.caffeine.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.study.caffeine.utils.LogUtils.logToFile;


/**
 *
 * @author markcwg
 * @date 2021/5/12 16:46
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 日志切面
     * @param joinPoint 切入点
     * @param ex 异常信息
     */
    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.study.caffeine.*.*.*(..)))")
    public void logPoint(JoinPoint joinPoint, Throwable ex) {
        logToFile(joinPoint,ex);
    }
}
