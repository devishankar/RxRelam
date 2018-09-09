/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 03-Dec-2017.
 */

package com.sapient.rxrealm.internal;

import android.support.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sapient.rxrealm.model.ProductName;

import java.lang.reflect.Type;

public class ProductNameDeserializer implements JsonDeserializer<ProductName>,
        JsonSerializer<ProductName> {

    private static final String INNER_KEY = "Names";
    private static final String LANG_KEY = "LanguageID";

    @NonNull
    private static String getLanguage() {
        return "en-US";
    }

    @Override
    public ProductName deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) {
        // Get current element as Object
        JsonObject jsonObject = json.getAsJsonObject();
        // Extract inner element "Names" as Array
        JsonArray jsonArray = jsonObject.getAsJsonArray(INNER_KEY);
        // Proceed with parsing.
        JsonObject names = null;
        for (JsonElement element : jsonArray) {
            names = element.getAsJsonObject();
            if (names.get(LANG_KEY).getAsString().equalsIgnoreCase(getLanguage())) {
                break;
            }
        }

        return context.deserialize(names, typeOfT);
    }

    @Override
    public JsonElement serialize(ProductName src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        JsonElement serialized = context.serialize(src, typeOfSrc);
        // Create an array of names
        JsonArray nameArray = new JsonArray();
        nameArray.add(serialized);
        // Create names object
        JsonObject names = new JsonObject();
        // Add the name array to the object.
        names.add(INNER_KEY, nameArray);
        return names;
    }
}
