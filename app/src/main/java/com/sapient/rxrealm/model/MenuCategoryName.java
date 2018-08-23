/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.RealmClass;

/**
 * Category name in menu category {@link MenuCategory}
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7628">SDK-7628</a>
 */
@RealmClass
public class MenuCategoryName implements RealmModel {

    @Nullable
    @LinkingObjects("categoryNames")
    private final RealmResults<MenuCategory> menuCategory = null;

    @SerializedName("locale")
    private String locale;
    @SerializedName("longName")
    private String longName;

    @Nullable
    public RealmResults<MenuCategory> getMenuCategory() {
        return menuCategory;
    }

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
}
