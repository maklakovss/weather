package com.mss.weather.data.db.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class InfoWeatherDB extends RealmObject {

    @PrimaryKey
    private String cityID;

    private Date dateState;
}
