package com.mss.weather.data.network.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TimeStringToDate {
    private static final SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");

    public static Date parseTimeString(String stringTime) {
        try {
            return format.parse(stringTime);
        } catch (ParseException e) {
            return null;
        }
    }
}
