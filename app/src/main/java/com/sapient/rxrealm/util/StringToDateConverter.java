/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.util;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * A clone of {@link com.google.gson.internal.bind.DateTypeAdapter}
 * <p>
 * Adapter for Date. Although this class appears stateless, it is not.
 * DateFormat captures its time zone and locale when it is created, which gives
 * this class state. DateFormat isn't thread safe either, so this class has
 * to synchronize its read and write methods.
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-8593">SDK-8593</a>
 */
final class StringToDateConverter extends TypeAdapter<Date> {

    static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        // we use a runtime check to make sure the 'T's equal
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return typeToken.getRawType() == Date.class ?
                    (TypeAdapter<T>) new StringToDateConverter() :
                    null;
        }
    };
    private static final String TAG = "StringToDateConverter";
    private static final Set<SimpleDateFormat> DATE_FORMATTER = new HashSet<>();
    private static final String[] DATE_FORMATS = new String[] {
            // ISO8601 no timezone
            DateUtils.ISO8601_DATETIME_PATTERN,
            DateUtils.ISO8601_DATE_PATTERN,
            DateUtils.ISO8601_TIME_PATTERN
    };

    @VisibleForTesting
    private StringToDateConverter() {
        for (String pattern : DATE_FORMATS) {
            DATE_FORMATTER.add(new SimpleDateFormat(pattern, Locale.ENGLISH));
        }

        Log.d(TAG, "Available formatter types " + DATE_FORMATTER.size());
    }

    private static synchronized Date deserializeToDate(String json) {
        // Filter all invalid/empty date
        if (json.isEmpty()) {
            return null;
        }
        // Very important, since MW can send anything
        json = json.trim();
        Date formattedDate = getCustomFormattedDate(json);
        if (formattedDate != null) {
            return formattedDate;
        }
        try {
            // Father of all valid ISO Formats
            return ISO8601Utils.parse(json, new ParsePosition(0));
        } catch (ParseException ex) {
            // Simply swallow the exception.
            Log.i(TAG, "" + new JsonSyntaxException(json, ex));
        }

        return null;
    }

    @Nullable
    private static Date getCustomFormattedDate(String json) {
        for (SimpleDateFormat format : DATE_FORMATTER) {
            try {
                return format.parse(json);
            } catch (ParseException ex) {
                Log.i(TAG, "" + new JsonSyntaxException(json, ex));
            }
        }
        return null;
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        return deserializeToDate(in.nextString());
    }

    @Override
    public synchronized void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        String dateFormatAsString = ISO8601Utils.format(value);
        out.value(dateFormatAsString);
    }
}
