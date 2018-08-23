/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Menu names in Menu type {@link MenuType}
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7628">SDK-7628</a>
 */
@RealmClass
public class MenuTypeName implements RealmModel {

    @SerializedName("menuType")
    private MenuType menuType;
    @SerializedName("locale")
    private String locale;
    @SerializedName("longName")
    private String longName;
    @SerializedName("shortName")
    private String shortName;

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
