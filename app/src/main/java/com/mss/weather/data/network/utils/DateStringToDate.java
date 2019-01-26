package com.mss.weather.data.network.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringToDate {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    public static Date parseDateString(@NonNull final String dateString) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
