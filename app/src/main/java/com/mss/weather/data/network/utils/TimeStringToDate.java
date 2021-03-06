package com.mss.weather.data.network.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TimeStringToDate {
    private static final SimpleDateFormat format = new SimpleDateFormat("hh:mm");

    @Nullable
    public static Date parseTimeString(@NonNull final String stringTime) {
        try {
            int offset = (int) TimeUnit.HOURS.convert(new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
            if (stringTime.indexOf("PM") >= 0)
                offset += 12;
            final Date date = format.parse(stringTime.replace(" PM", "").replace(" AM", ""));
            int hour = date.getHours() + offset;
            if (hour > 24) hour -= 24;
            if (hour < 0) hour += 24;
            date.setHours(hour);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}
