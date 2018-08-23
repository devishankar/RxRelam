/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7626">SDK-7626</a>
 */
@RealmClass
public class SmartRouting implements RealmModel {

    @SerializedName("CytGroupDisplayOrder")
    private RealmList<CytGroupDisplayOrder> cytGroupDisplayOrder;
    @SerializedName("CytIngredientGroup")
    private String cytIngredientGroup;
    @SerializedName("CytIngredientType")
    private String cytIngredientType;
    @SerializedName("CytProduct")
    private String cytProduct;
    @SerializedName("DeliverEarlyEnabled")
    private boolean deliverEarlyEnabled;

    public List<CytGroupDisplayOrder> getCytGroupDisplayOrder() {
        return cytGroupDisplayOrder;
    }

    public void setCytGroupDisplayOrder(RealmList<CytGroupDisplayOrder> cytGroupDisplayOrder) {
        this.cytGroupDisplayOrder = cytGroupDisplayOrder;
    }

    public String getCytIngredientGroup() {
        return cytIngredientGroup;
    }

    public void setCytIngredientGroup(String cytIngredientGroup) {
        this.cytIngredientGroup = cytIngredientGroup;
    }

    public String getCytIngredientType() {
        return cytIngredientType;
    }

    public void setCytIngredientType(String cytIngredientType) {
        this.cytIngredientType = cytIngredientType;
    }

    public String getCytProduct() {
        return cytProduct;
    }

    public void setCytProduct(String cytProduct) {
        this.cytProduct = cytProduct;
    }

    public boolean isDeliverEarlyEnabled() {
        return deliverEarlyEnabled;
    }

    public void setDeliverEarlyEnabled(boolean deliverEarlyEnabled) {
        this.deliverEarlyEnabled = deliverEarlyEnabled;
    }
}
