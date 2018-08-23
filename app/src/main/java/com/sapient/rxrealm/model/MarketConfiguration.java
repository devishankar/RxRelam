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
public class MarketConfiguration implements RealmModel {

    /**
     * This should be same as {@link #marketId}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "marketId";

    @PrimaryKey
    @SerializedName("marketId")
    private String marketId;

    @SerializedName("menuTypes")
    private RealmList<MenuType> menuTypes;
    @SerializedName("tenderTypes")
    private RealmList<TenderType> tenderTypes;
    @SerializedName("paymentMethods")
    private RealmList<PaymentMethod> paymentMethods;
    @SerializedName("feedbackTypes")
    private RealmList<FeedbackType> feedbackTypes;

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public List<MenuType> getMenuTypes() {
        return menuTypes;
    }

    public void setMenuTypes(RealmList<MenuType> menuTypes) {
        this.menuTypes = menuTypes;
    }

    public List<TenderType> getTenderTypes() {
        return tenderTypes;
    }

    public void setTenderTypes(RealmList<TenderType> tenderTypes) {
        this.tenderTypes = tenderTypes;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(RealmList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<FeedbackType> getFeedbackTypes() {
        return feedbackTypes;
    }

    public void setFeedbackTypes(RealmList<FeedbackType> feedbackTypes) {
        this.feedbackTypes = feedbackTypes;
    }
}
