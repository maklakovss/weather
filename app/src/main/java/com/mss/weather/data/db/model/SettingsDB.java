package com.mss.weather.data.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class SettingsDB extends RealmObject {
    @PrimaryKey
    private int id = 1;
    private String lasCityId;
}
