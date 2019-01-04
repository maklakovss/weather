
package com.mss.weather.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Timezone {

    @SerializedName("offset")
    @Expose
    public float offset;
    @SerializedName("zone")
    @Expose
    public String zone;

}
