
package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class SearchApi {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;

}
