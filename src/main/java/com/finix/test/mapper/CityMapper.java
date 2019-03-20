package com.finix.test.mapper;

import java.util.List;

import com.finix.test.entity.City;


public interface CityMapper {
    City get(int idx);

    int add(City city);

    List<City> getAll();

    int delAll();
}
