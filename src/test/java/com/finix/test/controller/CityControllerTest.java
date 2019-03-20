package com.finix.test.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSON;
import com.finix.test.entity.City;
import com.finix.test.service.CityService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CityService cityService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testGetCity_hasData() throws Exception {
        int cityId = 2;
        City expectCity = new City( cityId,"北京", "北京市");
        when(cityService.get(cityId)).thenReturn(expectCity);

        this.mvc.perform(get("/city/" + cityId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(expectCity.getName()));
    }

    @Test
    public void testGetCity_notFound() throws Exception {
        int cityId = 2;
        City expectCity = null;
        when(cityService.get(2)).thenReturn(expectCity);

        this.mvc.perform(get("/city/" + cityId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value((Object) null))
                .andExpect(jsonPath("$.code").value(-1))
        ;
    }

    @Test
    public void testSaveAndGet() throws Exception {

        final City city = new City(0,"台州", "浙江省");

        List<City> expectCityList = Arrays.asList(city);

        when(cityService.getAll()).thenReturn(expectCityList);

        this.mvc.perform(post("/city/saveAndGet").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(city)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectCityList.size()))
                .andExpect(jsonPath("$[-1:].name").value(city.getName()))
                .andExpect(jsonPath("$[-1:].province").value(city.getProvince()))
        ;

        verify(cityService).add(city);
    }

} 

