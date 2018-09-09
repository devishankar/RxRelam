/*
 * Copyright (c) 2018 Sapient. All rights reserved.
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
 * Type of Menu in menu category {@link MenuCategory}
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7628">SDK-7628</a>
 */
@RealmClass
public class MenuType implements RealmModel {

    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "id";

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("lastModification")
    private Date lastModification;
    @SerializedName("names")
    private RealmList<MenuTypeName> names;
    @SerializedName("sequence")
    private int sequence;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public List<MenuTypeName> getNames() {
        return names;
    }

    public void setNames(RealmList<MenuTypeName> names) {
        this.names = names;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
