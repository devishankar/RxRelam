/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import io.realm.Realm;

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Realm.init(this);
    }
}
