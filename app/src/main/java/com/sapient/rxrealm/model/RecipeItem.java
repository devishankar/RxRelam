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
public class RecipeItem implements RealmModel {

    @Nullable
    @LinkingObjects("choices")
    private final RealmResults<Recipe> choices = null;

    @Nullable
    @LinkingObjects("comments")
    private final RealmResults<Recipe> comments = null;

    @Nullable
    @LinkingObjects("extras")
    private final RealmResults<Recipe> extras = null;

    @Nullable
    @LinkingObjects("ingredients")
    private final RealmResults<Recipe> ingredients = null;

    @SerializedName("ChargeThreshold")
    private double chargeThreshold;
    @SerializedName("CostInclusive")
    private boolean costInclusive;
    @SerializedName("CytIngredientGroup")
    private String cytIngredientGroup;
    @SerializedName("CytIngredientType")
    private String cytIngredientType;
    @SerializedName("DefaultQuantity")
    private int defaultQuantity;
    @SerializedName("DefaultSolution")
    private String defaultSolution;
    @SerializedName("IsCustomerFriendly")
    private boolean isCustomerFriendly;
    @SerializedName("MaxQuantity")
    private int maxQuantity;
    @SerializedName("MinQuantity")
    private int minQuantity;
    @SerializedName("ProductCode")
    private long productCode;
    @SerializedName("ReferencePriceProductCode")
    private long referencePriceProductCode;
    @SerializedName("RefundThreshold")
    private double refundThreshold;

    private Product product;

    private Product referencePriceProduct;

    @Nullable
    public RealmResults<Recipe> getChoices() {
        return choices;
    }

    @Nullable
    public RealmResults<Recipe> getComments() {
        return comments;
    }

    @Nullable
    public RealmResults<Recipe> getExtras() {
        return extras;
    }

    @Nullable
    public RealmResults<Recipe> getIngredients() {
        return ingredients;
    }

    public double getChargeThreshold() {
        return chargeThreshold;
    }

    public void setChargeThreshold(double chargeThreshold) {
        this.chargeThreshold = chargeThreshold;
    }

    public boolean isCostInclusive() {
        return costInclusive;
    }

    public void setCostInclusive(boolean costInclusive) {
        this.costInclusive = costInclusive;
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

    public int getDefaultQuantity() {
        return defaultQuantity;
    }

    public void setDefaultQuantity(int defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
    }

    public String getDefaultSolution() {
        return defaultSolution;
    }

    public void setDefaultSolution(String defaultSolution) {
        this.defaultSolution = defaultSolution;
    }

    public boolean isCustomerFriendly() {
        return isCustomerFriendly;
    }

    public void setCustomerFriendly(boolean customerFriendly) {
        isCustomerFriendly = customerFriendly;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public long getReferencePriceProductCode() {
        return referencePriceProductCode;
    }

    public void setReferencePriceProductCode(long referencePriceProductCode) {
        this.referencePriceProductCode = referencePriceProductCode;
    }

    public double getRefundThreshold() {
        return refundThreshold;
    }

    public void setRefundThreshold(double refundThreshold) {
        this.refundThreshold = refundThreshold;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getReferencePriceProduct() {
        return referencePriceProduct;
    }

    public void setReferencePriceProduct(Product referencePriceProduct) {
        this.referencePriceProduct = referencePriceProduct;
    }
}
