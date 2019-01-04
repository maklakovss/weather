
package com.mss.weather.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Country {

    @SerializedName("value")
    @Expose
    public String value;

}
