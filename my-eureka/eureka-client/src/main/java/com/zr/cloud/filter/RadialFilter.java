package com.zr.cloud.filter;

import com.zr.cloud.utils.RequestBodyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

//@WebFilter
//@Component
public class RadialFilter implements Filter, Ordered {

    @Value("${redirectUrl}")
    private String redirectUrl;

    @Value("${token.dev}")
    private String devToken;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        String token = req.getHeader("MyAuthorization");
        // 去掉Bearer
//        String token = authorization.substring(7);
        if (devToken.equals(token)) {
            // 要处理的请求，本服务
            chain.doFilter(request,response);
        } else {
            // 转发请求，如果是同一个服务器可以直接调用转发请求的方法，这里要做的是转发请求另一个服务的接口，然后返回请求结果
            // 1、地址栏有传参数需要进行拼接参数
            String queryString = req.getQueryString();//地址栏参数
            if (queryString != null) {
                // 地址有参数，拼接
                requestURI = requestURI + "?" +queryString;
            }
            // 2、拼接转发的IP和端口
            String url = "https://" + redirectUrl;
//                    + ":" + serverPort + requestURI;

            // 3、请求类型
            String method = req.getMethod();
            HttpMethod httpMethod = HttpMethod.resolve(method);// method
            // 4、请求头
            MultiValueMap<String, String> headers = parseRequestHeader(req);// header
            // 5、请求体
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String body = RequestBodyUtils.read(bufferedReader);

            // 6、封装发http请求
            RequestEntity requestEntity = new RequestEntity(body, headers, httpMethod, URI.create(url));
            RestTemplate restTemplate = new RestTemplate();
            // 编码格式转换
            restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
            ResponseEntity<String> result = restTemplate.exchange(requestEntity, String.class);

            // 将转发请求得到的结果和响应头返回客户端
            String resultBody = result.getBody();
            HttpHeaders resultHeaders = result.getHeaders();
            MediaType contentType = resultHeaders.getContentType();
            if (contentType != null) {
                resp.setContentType(contentType.toString());
            }
            // 在getWriterz之前执行，否则不生效
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.write(resultBody);
            writer.flush();
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * request header
     * @param request
     * @return
     */
    private MultiValueMap<String, String> parseRequestHeader(HttpServletRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for(String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                httpHeaders.add(headerName, headerValue);
            }
        }
        return httpHeaders;
    }

}
