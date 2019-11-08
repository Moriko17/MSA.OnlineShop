package com.mc.gateway.Gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class PaymentFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        String requestUri = RequestContext.getCurrentContext().getRequest().getRequestURI();
        String[] list = requestUri.split("/");
        return list[list.length - 1].equals("payment");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        try {
            ctx.setRouteHost(new URL("http://localhost:8080/payment"));
            ctx.put("proxy", "payment");
            HttpServletRequest request2 = ctx.getRequest();
            System.out.println(request2.getMethod());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
        }
}
