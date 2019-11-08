package com.mc.gateway.Gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;

public class PaymentFilter extends ZuulFilter {

    @Autowired
    private ZuulProperties zuulProperties;

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
        HttpServletRequest request = ctx.getRequest();

        try {
            ctx.setRouteHost(new URL("http://localhost:8080/payment"));
            ctx.put("proxy", "payment");
            HttpServletRequest request2 = ctx.getRequest();
            System.out.println(request2.getMethod());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        if ("YOUR_A_LOGIC".equals(header) ) {
//            ctx.put("serviceId", "serviceA");
//            //ctx.setRouteHost(new URL("http://Service_A_URL”));
//        } else { // "YOUR_B_LOGIC"
//            ctx.put("serviceId", "serviceB");
//            //ctx.setRouteHost(new URL("http://Service_B_URL”));
//        }
//        log.info(String.format("%s request to %s", request.getMethod(),
//        request.getRequestURL().toString()));
        return null;
        }
}
