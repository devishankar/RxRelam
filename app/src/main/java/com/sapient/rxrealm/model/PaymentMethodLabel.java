/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Payment labels in Payment methods {@link PaymentMethod}
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7627">SDK-7627</a>
 */
@RealmClass
public class PaymentMethodLabel implements RealmModel {

    @SerializedName("locale")
    private String locale;
    @SerializedName("name")
    private String name;
    @SerializedName("optionName")
    private String optionName;
    @SerializedName("paymentMethod")
    private PaymentMethod paymentMethod;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
