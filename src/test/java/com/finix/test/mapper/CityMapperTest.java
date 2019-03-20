package com.finix.test.mapper;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.finix.test.entity.City;


@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase()
public class CityMapperTest {
	
    @Autowired
    private CityMapper cityMapper;

    @Before
    public void setUp() throws Exception {
        cityMapper.delAll();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get() throws Exception {
        final City city = new City(0, "台州", "浙江省");
        cityMapper.add(city);
        City realCity = cityMapper.get(city.getCityId());
        Assert.assertEquals("插入后查找不到！", city, realCity);

    }

    @Test
    public void add() throws Exception {
        final City city = new City(0, "台州", "浙江省");
        cityMapper.add(city);
        City realCity = cityMapper.get(city.getCityId());
        Assert.assertEquals("插入后查找不到！", city, realCity);
    }

    @Test
    public void getAll() throws Exception {
        final City city = new City(0, "台州", "浙江省");
        cityMapper.add(city);
        List<City> realCity = cityMapper.getAll();
        boolean find = false;
        for (City t : realCity) {
            if (city.equals(city)) {
                find = true;
            }
        }
        Assert.assertTrue("插入后查找不到！ 查看全部找不到", find);
    }

}