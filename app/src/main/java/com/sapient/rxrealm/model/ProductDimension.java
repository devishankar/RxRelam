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
public class ProductDimension implements RealmModel {

    @Nullable
    @LinkingObjects("dimensions")
    private final RealmResults<Product> products = null;

    @SerializedName("ShowSizeToCustomer")
    private boolean showSizeToCustomer;
    @SerializedName("SizeCodeID")
    private int sizeCodeId;
    @SerializedName("ProductCode")
    private long productCode;

    @Nullable
    public RealmResults<Product> getProducts() {
        return products;
    }

    public boolean isShowSizeToCustomer() {
        return showSizeToCustomer;
    }

    public void setShowSizeToCustomer(boolean showSizeToCustomer) {
        this.showSizeToCustomer = showSizeToCustomer;
    }

    public int getSizeCodeId() {
        return sizeCodeId;
    }

    public void setSizeCodeId(int sizeCodeId) {
        this.sizeCodeId = sizeCodeId;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }
}
