
package com.mss.weather.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LangRu {

    @SerializedName("value")
    @Expose
    private String value;

}
