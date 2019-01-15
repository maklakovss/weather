package com.mss.weather.data.db.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class ChanceWeatherDB extends RealmObject {

    @PrimaryKey
    private String id;

    private String cityID;
    private Date date;

    private int chanceofrain;
    private int chanceofremdry;
    private int chanceofwindy;
    private int chanceofovercast;
    private int chanceofsunshine;
    private int chanceoffrost;
    private int chanceofhightemp;
    private int chanceoffog;
    private int chanceofsnow;
    private int chanceofthunder;
}
