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

/**
 * Type of payment methods in market catalog
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7627">SDK-7627</a>
 */
@RealmClass
public class PaymentMethod implements RealmModel {

    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "id";

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("acceptsOneTimePayment")
    private boolean acceptsOneTimePayment;
    @SerializedName("cvvThresholdAmount")
    private double cvvThresholdAmount;
    @SerializedName("isEnabled")
    private boolean isEnabled;
    @SerializedName("minTransactionAmount")
    private double minTransactionAmount;
    @SerializedName("paymentLabels")
    private RealmList<PaymentMethodLabel> paymentLabels;
    @SerializedName("paymentMode")
    private int paymentMode;
    @SerializedName("paymentType")
    private int paymentType;
    @SerializedName("rank")
    private int rank;
    @SerializedName("registrationReturnUrl")
    private String registrationReturnUrl;
    @SerializedName("registrationType")
    private int registrationType;
    @SerializedName("requiresPwd")
    private boolean requiresPwd;
    @SerializedName("tenderTypes")
    private RealmList<TenderType> tenderTypes;
    @SerializedName("thresholdAmount")
    private double thresholdAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAcceptsOneTimePayment() {
        return acceptsOneTimePayment;
    }

    public void setAcceptsOneTimePayment(boolean acceptsOneTimePayment) {
        this.acceptsOneTimePayment = acceptsOneTimePayment;
    }

    public double getCvvThresholdAmount() {
        return cvvThresholdAmount;
    }

    public void setCvvThresholdAmount(double cvvThresholdAmount) {
        this.cvvThresholdAmount = cvvThresholdAmount;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public double getMinTransactionAmount() {
        return minTransactionAmount;
    }

    public void setMinTransactionAmount(double minTransactionAmount) {
        this.minTransactionAmount = minTransactionAmount;
    }

    public List<PaymentMethodLabel> getPaymentLabels() {
        return paymentLabels;
    }

    public void setPaymentLabels(RealmList<PaymentMethodLabel> paymentLabels) {
        this.paymentLabels = paymentLabels;
    }

    public int getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getRegistrationReturnUrl() {
        return registrationReturnUrl;
    }

    public void setRegistrationReturnUrl(String registrationReturnUrl) {
        this.registrationReturnUrl = registrationReturnUrl;
    }

    public int getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(int registrationType) {
        this.registrationType = registrationType;
    }

    public boolean isRequiresPwd() {
        return requiresPwd;
    }

    public void setRequiresPwd(boolean requiresPwd) {
        this.requiresPwd = requiresPwd;
    }

    public List<TenderType> getTenderTypes() {
        return tenderTypes;
    }

    public void setTenderTypes(RealmList<TenderType> tenderTypes) {
        this.tenderTypes = tenderTypes;
    }

    public double getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(double thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }
}
