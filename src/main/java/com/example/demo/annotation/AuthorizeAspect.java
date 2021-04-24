package com.example.demo.annotation;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ErrorException;
import com.example.demo.service.AdminService;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AuthorizeUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AuthorizeAspect {

    @Resource
    private AdminService adminService;
    @Resource
    private TokenService tokenService;
    public static final String TYPE_ADMIN = "admin";
    public static final String TYPE_USER = "user";
    public static final String TOKEN = "Token";

    /**
     * 目标方法
     */
    @Pointcut("@annotation(com.example.demo.annotation.Authorize)")
    private void permission() {

    }

    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     */
    @Around("permission()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*
         * 获取当前http请求中的token
         * 解析Token :
         * 1、Token是否存在
         * 2、Token格式是否正确
         * 3、Token是否已过期（解析信息或者redis中是否存在）
         * */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader(TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new ErrorException(ErrorCode.BIZ_PARAM_ILLEGAL, "Token为空");
        }
        if (!tokenService.loginCheck(token)) {
            throw new ErrorException(ErrorCode.TOKEN_AUTHORIZE_ILLEGAL, "对不起，您没有权限访问！");
        }

        Integer userId = tokenService.getUserIdByToken(token);
        String loginType = tokenService.getLoginType(token);
        Integer permissionCodes = null;
        if (loginType.equals(TYPE_ADMIN)) {
            permissionCodes = (adminService.getAdminType(userId));
        } else if (loginType.equals(TYPE_USER)) {
            permissionCodes = AuthorizeUtil.Character.TYPE_USER.getCode();
        } else {
            throw new ErrorException(ErrorCode.TOKEN_AUTHORIZE_ILLEGAL, "登录类型不存在!");
        }
        /*
         * 获取注解的值，并进行权限验证
         * */
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        Authorize authorize = method.getAnnotation(Authorize.class);

        Integer value = authorize.value().getCode();
        // 将注解的值和token解析后的值进行对比，查看是否有该权限，如果权限通过，允许访问方法；否则不允许，并抛出异常
        if (permissionCodes > value) {
            throw new ErrorException(ErrorCode.TOKEN_AUTHORIZE_ILLEGAL, "对不起，您没有权限访问！");
        }
        // 执行具体方法
        Object result = proceedingJoinPoint.proceed();

        return result;
    }

}
