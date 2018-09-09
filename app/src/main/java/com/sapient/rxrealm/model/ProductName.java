/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 10-Nov-2017.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.Index;
import io.realm.annotations.RealmClass;

@RealmClass
public class ProductName implements RealmModel {

    @SerializedName("LanguageID")
    private String locale;
    @SerializedName("LongName")
    private String longName;
    @Index
    @SerializedName("Name")
    private String name;
    @SerializedName("ShortName")
    private String shortName;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
