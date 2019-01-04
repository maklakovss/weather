
package com.mss.weather.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class SearchApi {

    @SerializedName("result")
    @Expose
    public List<Result> result = null;

}
