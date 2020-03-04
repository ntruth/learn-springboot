package com.ucmed.aop.aspect;

import com.alibaba.fastjson.JSONObject;
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

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Date;

/**
 * 自定义Syslog注解
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.ucmed.aop.aspect.SysLog)")
    public void logAspect() { }


    @Around("logAspect()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        Date date = new Date();
        // 执行方法
        Object result = point.proceed();
        this.saveSysLog(point, date, result);
    }

    private void saveSysLog(ProceedingJoinPoint point, Date date, Object result) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = method.getAnnotation(SysLog.class);

        if (sysLog != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // 打印客户端访问IP
            System.out.println("客户端ip是" + getRemoteHost(request));
            // 打印执行的类名
            System.out.println(signature.getDeclaringTypeName() + "." + method.getName());
            // 打印执行的入参, 最好转成json格式
            System.out.println(JSONObject.toJSONString(point.getArgs()));
            // 打印执行的回参
            System.out.println(postHandle(result));
        }
    }

    private String postHandle(Object retVal) {
        if (null == retVal) {
            return "";
        }

        return JSONObject.toJSONString(retVal);
    }

    /**
     * 获取客户端ip地址
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr ();
            if (ip.equals ("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost ();
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                ip = inet.getHostAddress ();
            }

        }

        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length () > 15) {
            if (ip.indexOf (",") > 0) {
                ip = ip.substring (0, ip.indexOf (","));
            }
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
