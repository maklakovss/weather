
package com.mss.weather.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CitiesResponse {

    @SerializedName("search_api")
    @Expose
    public SearchApi searchApi;

}
