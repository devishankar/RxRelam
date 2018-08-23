/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {

    private static Gson mPrettyGson;
    private static Gson mGson;

    /**
     * Returns a Gson instance
     *
     * @return {@link Gson}
     */
    public static Gson getGson() {
        if (mGson == null) {
            // Cache Gson, as there are many network calls happening.
            mGson = getGsonBuilder().create();
        }
        return mGson;
    }

    /**
     * Returns a Gson instance
     *
     * @return {@link Gson}
     */
    public static Gson getPrettyGson() {
        if (mPrettyGson == null) {
            mPrettyGson = getGsonBuilder()
                    .setLenient()
                    .setPrettyPrinting()
                    .create();
        }
        return mPrettyGson;
    }

    /**
     * Method will help to get the GsonBuilder instance.
     *
     * @return {@link GsonBuilder}
     * GSONBuilder provides a way where you can mark certain fields of your objects to be excluded
     * for consideration for serialization and deserialization to JSON.
     */
    public static GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .enableComplexMapKeySerialization()
                .registerTypeAdapterFactory(StringToDateConverter.FACTORY)
                .setLenient();
    }

    public static void hideKeyBoard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
