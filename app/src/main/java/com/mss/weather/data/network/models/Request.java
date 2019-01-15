
package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Request {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("query")
    @Expose
    private String query;

}
