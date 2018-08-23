/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class ProductPrice implements RealmModel {

    /**
     * This should be same as {@link #productCode}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "productCode";

    @PrimaryKey
    @SerializedName("ProductCode")
    private long productCode;
    @SerializedName("Prices")
    private RealmList<Price> prices;

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(RealmList<Price> prices) {
        this.prices = prices;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }
}
