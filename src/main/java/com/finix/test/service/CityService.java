package com.finix.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finix.test.entity.City;
import com.finix.test.mapper.CityMapper;

@Component
public class CityService {

    @Autowired
    private CityMapper cityDao;


    public City get(int idx) {
        return cityDao.get(idx);
    }

    public void add(City city) {
        cityDao.add(city);
    }

    public List<City> getAll() {
        return cityDao.getAll();
    }
}
