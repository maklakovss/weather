package com.mss.weather.data.network.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateStringToDate {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDateString(String dateString) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
