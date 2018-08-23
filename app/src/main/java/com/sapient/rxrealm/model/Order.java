/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmModel;

/**
 * Temp Order information
 * This class will be removed when Ordering is implemented
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-13449">SDK-13449</a>
 */
public class Order implements RealmModel {

    @SerializedName("products")
    private List<Product> products;

    @SerializedName("orderID")
    private Long orderID;

    @SerializedName("isFinalized")
    private boolean isFinalized;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void setIsFinalized(boolean isFinalized) {
        this.isFinalized = isFinalized;
    }
}
