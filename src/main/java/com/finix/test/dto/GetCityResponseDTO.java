package com.finix.test.dto;
import com.finix.test.entity.City;

import lombok.Data;


@Data
public class GetCityResponseDTO {
    private int code;
    private String msg;
    private City data;
}
