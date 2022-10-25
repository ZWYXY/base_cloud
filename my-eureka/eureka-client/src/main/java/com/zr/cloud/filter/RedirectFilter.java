package com.zr.cloud.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Component
public class RedirectFilter implements Filter, Ordered {


    @Value("${token.dev-ip}")
    private String devIp;

    @Value("${token.dev-port}")
    private String devPort;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String myToken = httpReq.getHeader("myToken");
        if(myToken.equals("dev")) {
            HttpServletResponse httpResp = (HttpServletResponse) response;
            String requestURL = "http://" + devIp + ":" + devPort + httpReq.getRequestURI();
            httpResp.sendRedirect(requestURL);
        }
        chain.doFilter(request, response);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
