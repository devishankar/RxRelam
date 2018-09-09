/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.sapient.rxrealm.internal.ProductNameDeserializer;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7626">SDK-7626</a>
 */
@RealmClass
public class Product implements RealmModel {

    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "id";
    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRODUCT_TYPE = "productType";
    /**
     * This should be a symbolic to {@link ProductCategory#displayCategoryID}
     */
    @Ignore
    public static final String CATEGORY_ID = "categories.displayCategoryID";
    /**
     * This should be a symbolic to {@link Price#productPrice}
     */
    @Ignore
    public static final String PRICE = "prices.productPrice";
    /**
     * This should be a symbolic to {@link Price#priceTypeId}
     */
    @Ignore
    public static final String PRICE_TYPE = "prices.priceTypeId";
    /**
     * This should be a symbolic to {@link ProductName#name}
     */
    @Ignore
    public static final String PRODUCT_NAME = "productName.name";
    /**
     * This should be a symbolic to {@link ProductName#locale}
     */
    @Ignore
    public static final String PRODUCT_LOCALE = "productName.locale";
    /**
     * This should be a symbolic to {@link ProductNutrition#energy}
     */
    @Ignore
    public static final String PRODUCT_CALORIE = "cumulativeCalorie";

    @PrimaryKey
    @SerializedName("ProductCode")
    private long id;

    @SerializedName("AcceptsLight")
    private boolean acceptsLight;
    @SerializedName("AcceptsOnly")
    private boolean acceptsOnly;
    @SerializedName("Categories")
    private RealmList<ProductCategory> categories;
    @SerializedName("Dimensions")
    private RealmList<ProductDimension> dimensions;
    @SerializedName("DisplayImageName")
    private String displayImageName;
    @SerializedName("ExtendedMenuTypeID")
    private RealmList<Integer> extendedMenuTypeId;
    @SerializedName("FamilyGroupID")
    private int familyGroupId;
    @SerializedName("IsMcCafe")
    private boolean isMcCafe;
    @SerializedName("IsPromotional")
    private boolean isPromotional;
    @SerializedName("IsPromotionalChoice")
    private boolean isPromotionalChoice;
    @SerializedName("IsSalable")
    private boolean isSalable;
    @SerializedName("MaxChoiceOptionsMOT")
    private int maxChoiceOptionsMot;
    @SerializedName("MaxExtraIngredientsQuantity")
    private int maxExtraIngredientsQuantity;
    @SerializedName("MaxQttyAllowedPerOrder")
    private int maxQttyAllowedPerOrder;
    @SerializedName("Nutrition")
    private ProductNutrition nutrition;
    @SerializedName("NutritionPrimaryProductCode")
    private String nutritionPrimaryProductCode;
    @SerializedName("POD")
    private RealmList<POD> pod;
    @SerializedName("ProductType")
    private int productType;
    @SerializedName("ProductUnit")
    private String productUnit;
    @SerializedName("PromotionEndDate")
    private Date promotionEndDate;
    @SerializedName("PromotionRestriction")
    private String promotionRestriction;
    @SerializedName("PromotionStartDate")
    private Date promotionStartDate;
    @SerializedName("PromotionalLabel")
    private String promotionLabel;
    @SerializedName("PromotionsAssociated")
    private String promotionsAssociated;
    @SerializedName("Recipe")
    private Recipe recipe;
    @SerializedName("SmartRouting")
    private SmartRouting smartRouting;
    @SerializedName("TimeRestriction")
    private RealmList<TimeRestriction> timeRestrictions;
    @JsonAdapter(ProductNameDeserializer.class)
    @SerializedName("Names")
    private ProductName productName;

    // This is not available directly from catalog, we create a fake variable
    // & assign it's value in runtime from downloaded catalog.
    @SerializedName("Prices")
    private RealmList<Price> prices;
    @SerializedName("cumulativeCalorie")
    private double cumulativeCalorie;

    private boolean isProcessed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAcceptsLight() {
        return acceptsLight;
    }

    public void setAcceptsLight(boolean acceptsLight) {
        this.acceptsLight = acceptsLight;
    }

    public boolean isAcceptsOnly() {
        return acceptsOnly;
    }

    public void setAcceptsOnly(boolean acceptsOnly) {
        this.acceptsOnly = acceptsOnly;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<ProductCategory> categories) {
        this.categories = categories;
    }

    public List<ProductDimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(RealmList<ProductDimension> dimensions) {
        this.dimensions = dimensions;
    }

    public String getDisplayImageName() {
        return displayImageName;
    }

    public void setDisplayImageName(String displayImageName) {
        this.displayImageName = displayImageName;
    }

    public List<Integer> getExtendedMenuTypeId() {
        return extendedMenuTypeId;
    }

    public void setExtendedMenuTypeId(RealmList<Integer> extendedMenuTypeId) {
        this.extendedMenuTypeId = extendedMenuTypeId;
    }

    public int getFamilyGroupId() {
        return familyGroupId;
    }

    public void setFamilyGroupId(int familyGroupId) {
        this.familyGroupId = familyGroupId;
    }

    public boolean isMcCafe() {
        return isMcCafe;
    }

    public void setMcCafe(boolean mcCafe) {
        isMcCafe = mcCafe;
    }

    public boolean isPromotional() {
        return isPromotional;
    }

    public void setPromotional(boolean promotional) {
        isPromotional = promotional;
    }

    public boolean isPromotionalChoice() {
        return isPromotionalChoice;
    }

    public void setPromotionalChoice(boolean promotionalChoice) {
        isPromotionalChoice = promotionalChoice;
    }

    public boolean isSalable() {
        return isSalable;
    }

    public void setSalable(boolean salable) {
        isSalable = salable;
    }

    public int getMaxChoiceOptionsMot() {
        return maxChoiceOptionsMot;
    }

    public void setMaxChoiceOptionsMot(int maxChoiceOptionsMot) {
        this.maxChoiceOptionsMot = maxChoiceOptionsMot;
    }

    public int getMaxExtraIngredientsQuantity() {
        return maxExtraIngredientsQuantity;
    }

    public void setMaxExtraIngredientsQuantity(int maxExtraIngredientsQuantity) {
        this.maxExtraIngredientsQuantity = maxExtraIngredientsQuantity;
    }

    public int getMaxQttyAllowedPerOrder() {
        return maxQttyAllowedPerOrder;
    }

    public void setMaxQttyAllowedPerOrder(int maxQttyAllowedPerOrder) {
        this.maxQttyAllowedPerOrder = maxQttyAllowedPerOrder;
    }

    public ProductNutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(ProductNutrition nutrition) {
        this.nutrition = nutrition;
    }

    public String getNutritionPrimaryProductCode() {
        return nutritionPrimaryProductCode;
    }

    public void setNutritionPrimaryProductCode(String nutritionPrimaryProductCode) {
        this.nutritionPrimaryProductCode = nutritionPrimaryProductCode;
    }

    public List<POD> getPod() {
        return pod;
    }

    public void setPod(RealmList<POD> pod) {
        this.pod = pod;
    }

    public Type getProductType() {
        return Type.forType(productType);
    }

    public void setProductType(Type productType) {
        this.productType = productType.getCode();
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Date getPromotionEndDate() {
        return promotionEndDate;
    }

    public void setPromotionEndDate(Date promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
    }

    public String getPromotionRestriction() {
        return promotionRestriction;
    }

    public void setPromotionRestriction(String promotionRestriction) {
        this.promotionRestriction = promotionRestriction;
    }

    public Date getPromotionStartDate() {
        return promotionStartDate;
    }

    public void setPromotionStartDate(Date promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
    }

    public String getPromotionLabel() {
        return promotionLabel;
    }

    public void setPromotionLabel(String promotionLabel) {
        this.promotionLabel = promotionLabel;
    }

    public String getPromotionsAssociated() {
        return promotionsAssociated;
    }

    public void setPromotionsAssociated(String promotionsAssociated) {
        this.promotionsAssociated = promotionsAssociated;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public SmartRouting getSmartRouting() {
        return smartRouting;
    }

    public void setSmartRouting(SmartRouting smartRouting) {
        this.smartRouting = smartRouting;
    }

    public List<TimeRestriction> getTimeRestrictions() {
        return timeRestrictions;
    }

    public void setTimeRestrictions(RealmList<TimeRestriction> timeRestrictions) {
        this.timeRestrictions = timeRestrictions;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(RealmList<Price> prices) {
        this.prices = prices;
    }

    public double getCumulativeCalorie() {
        return cumulativeCalorie;
    }

    public void setCumulativeCalorie(double cumulativeCalorie) {
        this.cumulativeCalorie = cumulativeCalorie;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public ProductName getProductName() {
        return productName;
    }

    public void setProductName(ProductName productName) {
        this.productName = productName;
    }

    /**
     * Represents the product type.
     * These types &mp; ordinal are directly in-sync with constants in server.
     */
    public enum Type {
        PRODUCT(0),
        INGREDIENT(1),
        MEAL(2),
        UNKNOWN_3(3),
        COMMENT(4),
        GIFT_CERTIFICATE(5),
        UNKNOWN_6(6),
        DELIVERY_FEE(7),
        UNKNOWN_8(8),
        CHOICE(9),
        NON_FOOD(10);

        private final int productType;

        Type(@IntRange(from = 0, to = 10) int productType) {
            this.productType = productType;
        }

        @Nullable
        public static Type forType(@IntRange(from = 0, to = 10) int productType) {
            for (Type type : Type.values()) {
                if (type.getCode() == productType) {
                    return type;
                }
            }
            return null;
        }

        public int getCode() {
            return productType;
        }
    }
}
