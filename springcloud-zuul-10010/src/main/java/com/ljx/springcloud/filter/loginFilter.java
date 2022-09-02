package com.ljx.springcloud.filter;

import com.ljx.springcloud.config.FilterProperties;
import com.ljx.springcloud.config.JwtProperties;
import com.ljx.springcloud.utils.JwtUtils;
import com.ljx.springcloud.utils.cookieUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//网关Zuul的拦截器
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class loginFilter extends ZuulFilter {
    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    FilterProperties filterProperties;

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    //拦截哪些不在白名单上请求路径
    @Override
    public boolean shouldFilter() {
        //获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = currentContext.getRequest();
        //获取路径
        String requestURI = request.getRequestURI();
        return !isAllowPath(requestURI);
    }

    public boolean isAllowPath(String requestURI){
        //定义一个标记
        boolean flag = false;
        //遍历允许访问路径
        for(String path:filterProperties.getAllowPaths()){
            if(requestURI.startsWith(path)){
                flag=true;
                break;
            }
        }
        return flag;
    }

    //对那些不在白名单的请求路径进行拦截校验，校验通过则放行，不通过则拦截
    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = currentContext.getRequest();
        //获取token
        String token = cookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        //校验
        try {
            JwtUtils.getInfoFromToken(token,jwtProperties.getPublicKey());
        } catch (Exception e) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(403);
            logger.error("未登录，不可访问");
        }
        return null;
    }
}
