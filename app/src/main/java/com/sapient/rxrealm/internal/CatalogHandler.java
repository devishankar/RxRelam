/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.internal;

import android.support.annotation.NonNull;

import com.sapient.rxrealm.model.Product;

import java.util.List;

import io.reactivex.Single;

/**
 * Handles catalog related operation.
 * This is POC, method signature tend to change abruptly.
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-7626">SDK-7626</a>
 */
public interface CatalogHandler {

    /**
     * Inserts items from assets to Storage, for the given store id
     *
     * @param path file path to catalog data inside assets
     * @see <a href="https://us-jira.mcd.com/browse/SDK-7626">SDK-7626</a>
     */
    Single<Boolean> insertProductsIntoStorage(String path);

    /**
     * Retrieves list of products for the given store id.
     *
     * @see <a href="https://us-jira.mcd.com/browse/SDK-7626">SDK-7626</a>
     */
    @NonNull
    Single<List<Product>> getProducts(String name);

    @NonNull
    Single<Product> getProduct(String name, int productCode);
}
