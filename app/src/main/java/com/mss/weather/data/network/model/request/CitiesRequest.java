package com.mss.weather.data.network.model.request;

import lombok.Data;

@Data
public class CitiesRequest {
    String key;
    String format;
    String startWith;
}
