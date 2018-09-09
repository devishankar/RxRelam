/*
 * Copyright (c) 2018 Sapient. All rights reserved.
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
public class POD implements RealmModel {

    @Nullable
    @LinkingObjects("pod")
    private final RealmResults<Product> products = null;

    @SerializedName("SaleTypeID")
    private int saleTypeId;
    @SerializedName("TypeName")
    private String type;

    public int getSaleTypeId() {
        return saleTypeId;
    }

    public void setSaleTypeId(int saleTypeId) {
        this.saleTypeId = saleTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    public RealmResults<Product> getProducts() {
        return products;
    }
}
