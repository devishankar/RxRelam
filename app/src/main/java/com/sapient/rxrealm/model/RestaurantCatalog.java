/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class RestaurantCatalog implements RealmModel {

    @SerializedName("Products")
    private RealmList<Product> products;
    @SerializedName("ProductPrice")
    private RealmList<ProductPrice> productPrices;

    public List<ProductPrice> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(RealmList<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Product> products) {
        this.products = products;
    }
}
