package com.finix.test.util;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class HttpUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    // utf-8字符编码
    public static final String CHARSET_UTF_8 = "UTF-8";


    /**
     * 发送 get 请求
     *
     * @param url 地址
     * @return
     */
    public final static String getJsonData(String url) {
        log.info("begin HttpUtils.getJsonResponse()");
        log.info("# GET 请求URL 为 {}", url);
        String resjsonStr = null;
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpGet get = new HttpGet(url);
        CloseableHttpClient closeableHttpClient = null;
        try {
            closeableHttpClient = httpClientBuilder.build();
            HttpResponse resp = closeableHttpClient.execute(get);
            resjsonStr = convertResponseBytes2JsonObj(resp);
        } catch (IOException e) {
            log.error("发送get请求出现异常", e);
        } finally {
            if (null != closeableHttpClient) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    log.error("关闭CloseableHttpClient链接出现异常", e);
                }
            }
        }
        log.info("end HttpUtils.getJsonResponse()");
        return resjsonStr;
    }

    /**
     * Header 为 application/json POST 请求数据
     *
     * @param resp
     * @return
     */
    private final static String convertResponseBytes2JsonObj(HttpResponse resp) {
        log.info("begin HttpUtils.convertResponseBytes2JsonObj()");
        String resjsonStr = null;

        try {
            InputStream respIs = resp.getEntity().getContent();
            byte[] respBytes = IOUtils.toByteArray(respIs);
            resjsonStr = new String(respBytes, Charset.forName(CHARSET_UTF_8));

            if (resjsonStr.length() == 0) {
                log.error("HttpResponse无响应");
            } else {
                log.debug(resjsonStr);
            }
        } catch (Exception e) {
            log.error("解析HttpResponse返回的流异常", e);
        }

        log.info("end HttpUtils.convertResponseBytes2JsonObj()");
        return resjsonStr;
    }

}
