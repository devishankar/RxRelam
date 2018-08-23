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
 * Type of Menu Category in category list
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7628">SDK-7628</a>
 */
@RealmClass
public class MenuCategory implements RealmModel {

    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "id";

    /**
     * This should be same as {@link #parentCategory}
     */
    @Ignore
    public static final String PARENT_ID = "parentCategory";

    /**
     * This should be same as {@link #menuTypeId}
     */
    @Ignore
    public static final String MENU_TYPE_ID = "menuTypeId";

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("displayOrder")
    private int displayOrder;
    @SerializedName("imageName")
    private String imageName;
    @SerializedName("menuTypeId")
    private int menuTypeId;
    @SerializedName("names")
    private RealmList<MenuCategoryName> categoryNames;
    @SerializedName("parentId")
    private int parentCategory;
    private RealmList<MenuCategory> subCategories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getMenuTypeId() {
        return menuTypeId;
    }

    public void setMenuTypeId(int menuTypeId) {
        this.menuTypeId = menuTypeId;
    }

    public List<MenuCategoryName> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(RealmList<MenuCategoryName> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public int getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(int parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<MenuCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(RealmList<MenuCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
