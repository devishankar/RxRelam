/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>A suite of utilities surrounding the use of the {@link Date} object.</p>
 * <p>
 * <p>DateUtils contains a lot of common methods considering manipulations of Dates.
 * It is important to note these methods
 * use a {@code SimpleDateFormat} internally with default timezone and locale.
 */
public final class DateUtils {

    public static final String ISO8601_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";
    public static final String ISO8601_TIME_PATTERN = "HH:mm:ss";
    private static final String TAG = "DateUtils";

    private DateUtils() {
        // Hidden empty constructor
    }

    /**
     * Convert string to Date format for the specific date format type.
     *
     * @param date       in string type
     * @param dateFormat date format
     * @return return {@link Date} type
     */
    public static Date getFormattedDate(@NonNull String date, @NonNull String dateFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            return format.parse(date);
        } catch (ParseException ex) {
            Log.d(TAG, ex.getLocalizedMessage());
        }
        return null;
    }

    /**
     * Return string describing the elapsed time since startTime formatted.
     *
     * @param date   which is used to convert into string
     * @param format type of date format
     * @return the string value of the date
     */
    @NonNull
    public static String getRelativeDateTimeString(@NonNull Date date, @NonNull String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
