package com.finix.test.util;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClientBuilder.class})
public class HttpUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testGetJsonData() throws Exception {

        TestBean expectBean = new TestBean("战士");
        String expect = JSON.toJSONString(expectBean);

        String url = "test";
        PowerMockito.mockStatic(HttpClientBuilder.class);
        HttpClientBuilder mockClientBuilder = mock(HttpClientBuilder.class);
        PowerMockito.when(HttpClientBuilder.create()).thenReturn(mockClientBuilder).getMock();

        CloseableHttpClient mockHttpClient = mock(CloseableHttpClient.class);
        CloseableHttpResponse mockResponse = mock(CloseableHttpResponse.class);
        StatusLine mockStatusLine = mock(StatusLine.class);


        //PowerMockito.when(HttpClientBuilder.class, "create").thenReturn(mockClientBuilder);
        PowerMockito.when(mockClientBuilder.build()).thenReturn(mockHttpClient);
        PowerMockito.when(mockHttpClient.execute(BDDMockito.any(HttpGet.class))).thenReturn(mockResponse);
        PowerMockito.when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
        PowerMockito.when(mockStatusLine.getStatusCode()).thenReturn(200);

        StringEntity res = new StringEntity(expect, ContentType.APPLICATION_JSON);
        res.setContentEncoding("utf8");
        PowerMockito.when(mockResponse.getEntity()).thenReturn(res);

        PowerMockito.when(mockStatusLine.getStatusCode()).thenReturn(201);
        String real = HttpUtils.getJsonData("http://baidu.com");
        Assert.assertEquals(expect, real);
        PowerMockito.verifyStatic(HttpClientBuilder.class);
        HttpClientBuilder.create();

    }


    /**
     * Method: convertResponseBytes2JsonObj(HttpResponse resp)
     */
    @Test
    public void testConvertResponseBytes2JsonObj() throws Exception {

        String expectTxt = "Hello Text";

        HttpResponse mockResponse = mock(HttpResponse.class);

        InputStream inputStream = IOUtils.toInputStream(expectTxt, StandardCharsets.UTF_8);
        when(mockResponse.getEntity()).thenReturn(
                new InputStreamEntity(inputStream)
        );

        String text = Whitebox.invokeMethod(HttpUtils.class, "convertResponseBytes2JsonObj", mockResponse);

        Assert.assertEquals("读取Http失败", expectTxt, text);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TestBean {
        String a;
    }

} 
