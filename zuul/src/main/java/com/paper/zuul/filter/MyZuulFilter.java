package com.paper.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class MyZuulFilter  extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(MyZuulFilter.class);

    /**
     * filter类型。
     *      分为以下几种：
     *          pre:请求执行之前filter
     *          route: 处理请求，进行路由
     *          post: 请求处理完成后执行的filter
     *          error:出现错误时执行的filter
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter执行顺序，通过数字指定
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * filter是否需要执行 true执行 false 不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        log.info(String.format("自定义zuul过滤器拦截请求：%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        // 这里就简单的判断一下是否有token参数，只作为测试
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                HttpServletResponse res = ctx.getResponse();
                res.getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("自定义zuul过滤器处理完成 ok！");
        return null;
    }
}
