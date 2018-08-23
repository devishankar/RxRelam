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
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7626">SDK-7626</a>
 */
@RealmClass
public class ProductCategory implements RealmModel {

    @Nullable
    @LinkingObjects("categories")
    private final RealmResults<Product> products = null;

    @SerializedName("DisplaySizeSelection")
    private int displaySizeSelected;
    @SerializedName("DisplayCategoryID")
    private int displayCategoryID;
    @SerializedName("DisplayOrder")
    private int displayOrder;

    @Nullable
    public RealmResults<Product> getProducts() {
        return products;
    }

    public int isDisplaySizeSelected() {
        return displaySizeSelected;
    }

    public void setDisplaySizeSelected(int displaySizeSelected) {
        this.displaySizeSelected = displaySizeSelected;
    }

    public int getDisplayCategoryID() {
        return displayCategoryID;
    }

    public void setDisplayCategoryID(int displayCategoryID) {
        this.displayCategoryID = displayCategoryID;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
