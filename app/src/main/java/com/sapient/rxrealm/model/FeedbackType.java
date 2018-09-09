/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmModel;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Feedback types in market catalog
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7627">SDK-7627</a>
 */
@RealmClass
public class FeedbackType implements RealmModel {

    /**
     * This should be same as {@link #id}/ {@link PrimaryKey}
     */
    @Ignore
    public static final String PRIMARY_KEY = "id";

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("cultureAbbreviation")
    private String cultureAbbreviation;
    @SerializedName("lastModification")
    private Date lastModification;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCultureAbbreviation() {
        return cultureAbbreviation;
    }

    public void setCultureAbbreviation(String cultureAbbreviation) {
        this.cultureAbbreviation = cultureAbbreviation;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
