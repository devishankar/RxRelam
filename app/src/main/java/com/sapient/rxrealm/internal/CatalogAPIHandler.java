/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.internal;

import android.support.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sapient.rxrealm.model.Product;
import com.sapient.rxrealm.model.RestaurantCatalog;
import com.sapient.rxrealm.util.FileUtils;
import com.sapient.rxrealm.util.PersistenceUtil;
import com.sapient.rxrealm.util.Util;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class CatalogAPIHandler implements CatalogHandler {

    private static final String TAG = "CatalogAPIHandler";
    private static final boolean ESCAPE_JSON = false;
    private static final boolean ENCODE = true;
    private static final boolean G_ZIP = true;

    @Override
    public Single<Boolean> insertProductsIntoStorage(final String path) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) {
                try {
                    String catalog = FileUtils.readFromAssets(path, G_ZIP, ENCODE, ESCAPE_JSON);

                    String catalogJson = new String(catalog.getBytes());

                    JsonObject response = Util.getGson().fromJson(catalogJson, JsonObject.class);
                    JsonArray stores = response.getAsJsonArray("Store");
                    JsonElement element = stores.get(0);

                    RestaurantCatalog restaurantCatalog = Util.getGson()
                            .fromJson(element, RestaurantCatalog.class);

                    if (restaurantCatalog != null) {
                        List<Product> allProducts = restaurantCatalog.getProducts();

                        PersistenceUtil.insertDataWithClose(path, allProducts);
                    }

                    emitter.onSuccess(true);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    @NonNull
    @Override
    public Single<List<Product>> getProducts(final String name) {
        // Return results.
        return Single.create(new SingleOnSubscribe<List<Product>>() {
            @Override
            public void subscribe(SingleEmitter<List<Product>> emitter) {
                try {
                    emitter.onSuccess(PersistenceUtil.getProducts(name));
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    @NonNull
    @Override
    public Single<Product> getProduct(String name, int productCode) {
        return new ProductRequestWithId(name, productCode);
    }
}
