package com.finix.test.controller;


import com.finix.test.dto.GetCityResponseDTO;
import com.finix.test.entity.City;
import com.finix.test.service.CityService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestController
@RequestMapping("/city")
@Slf4j
public class CityController {


    @Autowired
    CityService cityService;

    @RequestMapping(value = {"/{cityId}"}, method = RequestMethod.GET)
    public GetCityResponseDTO getCity(@PathVariable(value = "cityId") int cityId) {
        GetCityResponseDTO response = new GetCityResponseDTO();

        City city = cityService.get(cityId);
        if (city != null) {
            response.setCode(0);
            response.setMsg("");
            response.setData(city);

        } else {
            response.setCode(-1);
            response.setMsg("not found");
            response.setData(null);
        }

        Map<String, String> frequencyMetric = new TreeMap<>();
        frequencyMetric.put("cityId", String.valueOf(cityId));
        frequencyMetric.put("fun", "getCity");


        return response;
    }

    @RequestMapping(value = {"/saveAndGet"}, method = RequestMethod.POST)
    public List<City> save(@RequestBody City city) {


            cityService.add(city);

        return cityService.getAll();
    }
}
