/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Tender amount, types in Payment methods {@link PaymentMethod}
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7627">SDK-7627</a>
 */
@RealmClass
public class TenderType implements RealmModel {

    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "id";

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("code")
    private int code;
    @SerializedName("defaultTenderAmountDisplay")
    private double defaultTenderAmountDisplay;
    @SerializedName("isDefault")
    private boolean isDefault;
    @SerializedName("lastModification")
    private Date lastModification;
    @SerializedName("marketId")
    private int marketId;
    @SerializedName("minimumTenderAmount")
    private double minimumTenderAmount;
    @SerializedName("name")
    private String name;
    @SerializedName("paymentMethod")
    private RealmList<PaymentMethod> paymentMethod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getDefaultTenderAmountDisplay() {
        return defaultTenderAmountDisplay;
    }

    public void setDefaultTenderAmountDisplay(double defaultTenderAmountDisplay) {
        this.defaultTenderAmountDisplay = defaultTenderAmountDisplay;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public double getMinimumTenderAmount() {
        return minimumTenderAmount;
    }

    public void setMinimumTenderAmount(double minimumTenderAmount) {
        this.minimumTenderAmount = minimumTenderAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaymentMethod> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(RealmList<PaymentMethod> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
