package com.example.demo.exception;

import cn.yueshutong.springbootstartercurrentlimiting.annotation.CurrentLimiter;
import cn.yueshutong.springbootstartercurrentlimiting.handler.CurrentAspectHandler;
import com.example.demo.pojo.vo.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * @author DrGilbert
 * @date 2021/4/2 21:17
 */
@Component
public class CurrentLimitHandler implements CurrentAspectHandler {
    @Override
    public Response<String> around(ProceedingJoinPoint pjp, CurrentLimiter rateLimiter) {
        return Response.createErr("接口访问繁忙!休息一下");
    }
}
