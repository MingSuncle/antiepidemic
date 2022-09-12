package com.zza.antiepidemic.aspect;

import com.zza.antiepidemic.exception.RRException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Redis切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Configuration
public class RedisAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.zza.antiepidemic.common.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        try {
            result = point.proceed();
        } catch (Exception e) {
            logger.error("redis error", e);
            throw new RRException("Redis服务异常");
        }
        return result;
    }
}
