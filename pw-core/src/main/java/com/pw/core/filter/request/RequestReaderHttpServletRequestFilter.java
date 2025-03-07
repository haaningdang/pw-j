package com.pw.core.filter.request;

import cn.hutool.core.text.CharSequenceUtil;
import com.pw.core.util.HttpContextUtil;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestReaderHttpServletRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request = servletRequest;
        if(CharSequenceUtil.equals(HttpContextUtil.method().toUpperCase(), RequestMethod.POST.name())){
            if(servletRequest.getContentType() != null){
                if(!servletRequest.getContentType().contains("multipart")){
                    request = new RequestReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
                }
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
