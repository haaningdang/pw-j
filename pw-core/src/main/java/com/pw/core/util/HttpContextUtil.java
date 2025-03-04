package com.pw.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpContextUtil {

    /**
     * 请求实体
     * @return
     */
    public static HttpServletRequest request(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if(attributes != null){
            return attributes.getRequest();
        }
        return null;
    }

    /**
     * 响应实体
     * @return
     */
    public static HttpServletResponse response(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if(attributes != null){
            return attributes.getResponse();
        }
        return null;
    }

    /**
     * 获取请求方法
     * @return
     */
    public static String method(){
        return request() == null ? "" : request().getMethod();
    }

    /**
     * 获取请求路径
     * @return
     */
    public static String path(){
        return request() == null ? "" : request().getServletPath();
    }

    /**
     * 获取请求的header
     * @return
     */
    public static Map<String, String> headers(){
        HashMap<String, String> values = new HashMap<>();
        HttpServletRequest request = request();
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String name = headers.nextElement();
            values.put(name, request.getHeader(name));
        }
        return values;
    }

    /**
     * 获取指定header
     * @param code
     * @return
     */
    public static String header(String code){
        Map<String, String> headerMaps = headers();
        if(MapUtil.isNotEmpty(headerMaps)){
            return headerMaps.get(code);
        }
        return "";
    }

    /**
     * 获取请求body
     * @param request
     * @return
     */
    public static String getRequestBodyStr(HttpServletRequest request){
        StringBuffer buffer = new StringBuffer();
        try{
            InputStream stream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line);
            }
        }catch(Exception e){
            log.error("trace={}", e);
            return JSON.toJSONString(Collections.emptyMap());
        }
        return buffer.toString();
    }

    /**
     * 获取请求body
     * @return
     */
    public static String getRequestBodyStr(){
        return getRequestBodyStr(request());
    }


    /**
     * 获取请求body - map
     * @return
     */
    public static Map<String, Object> getRequestBody(){
        String bodyStr = getRequestBodyStr();
        if(JSONUtil.isTypeJSON(bodyStr)){
            return (Map<String, Object>) JSON.parse(bodyStr);
        }
        return Collections.emptyMap();
    }

    /**
     * 获取请求body的指定内容
     * @param code
     * @return
     */
    public static Object getRequestBody(String code){
        return getRequestBody().get(code);
    }

    /**
     * 获取get请求的内容
     * @return
     */
    public static Map<String, String> getParameters(){
        HashMap<String, String> values = new HashMap<>();
        HttpServletRequest request = request();
        try{
            Enumeration enums = request.getParameterNames();
            while(enums.hasMoreElements()){
                String name = (String) enums.nextElement();
                String value = request.getParameter(name);
                values.put(name, value);
            }
        }catch(Exception e){
            log.error("trace={}", e);
            return Collections.emptyMap();
        }
        return values;
    }

    /**
     * 获取get请求的内容
     * @return
     */
    public static String getParametersStr(){
        return JSON.toJSONString(getParameters());
    }


    /**
     * 获取get请求的内容 - 特定code
     * @return
     */
    public static String getParameter(String code){
        return getParameters().get(code);
    }

    public static Map<String, Object> getRequest(){
        Map<String, Object> body = getRequestBody();
        Map<String, String> parameters = getParameters();
        body.putAll(parameters);
        return body;
    }

    /**
     * 获取参数值
     * @param code
     * @return
     */
    public static String getRequest(String code){
        Map<String, Object> request = getRequest();
        return CollUtil.isEmpty(request) ? "" : Convert.toStr(request.get(code), "").trim();
    }

}
