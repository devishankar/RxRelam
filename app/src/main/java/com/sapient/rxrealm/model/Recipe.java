/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7626">SDK-7626</a>
 */
@RealmClass
public class Recipe implements RealmModel {

    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "id";

    @Nullable
    @LinkingObjects("recipe")
    private final RealmResults<Product> products = null;

    @PrimaryKey
    @SerializedName("RecipeID")
    private int id;

    @SerializedName("DefaultSolution")
    private int defaultSolutionProductCode;
    @SerializedName("IsCustomerFriendly")
    private boolean isCustomerFriendly;
    @SerializedName("Choices")
    private RealmList<RecipeItem> choices;
    @SerializedName("Comments")
    private RealmList<RecipeItem> comments;
    @SerializedName("Extras")
    private RealmList<RecipeItem> extras;
    @SerializedName("Ingredients")
    private RealmList<RecipeItem> ingredients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDefaultSolutionProductCode() {
        return defaultSolutionProductCode;
    }

    public void setDefaultSolutionProductCode(int defaultSolution) {
        this.defaultSolutionProductCode = defaultSolution;
    }

    public boolean isCustomerFriendly() {
        return isCustomerFriendly;
    }

    public void setCustomerFriendly(boolean customerFriendly) {
        isCustomerFriendly = customerFriendly;
    }

    @Nullable
    public RealmResults<Product> getProducts() {
        return products;
    }

    public List<RecipeItem> getChoices() {
        return choices;
    }

    public void setChoices(RealmList<RecipeItem> choices) {
        this.choices = choices;
    }

    public List<RecipeItem> getComments() {
        return comments;
    }

    public void setComments(RealmList<RecipeItem> comments) {
        this.comments = comments;
    }

    public List<RecipeItem> getExtras() {
        return extras;
    }

    public void setExtras(RealmList<RecipeItem> extras) {
        this.extras = extras;
    }

    public List<RecipeItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(RealmList<RecipeItem> ingredients) {
        this.ingredients = ingredients;
    }
}
