/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.util;

import com.sapient.rxrealm.model.Product;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class PersistenceUtil {

    public static Realm getStorage(String suffix) {
        Util.isInMainThread();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("storage_" + suffix)
                .build();

        return Realm.getInstance(config);
    }

    public static void insertDataWithClose(String name, List<Product> allProducts) {
        for (Product product : allProducts) {
            Realm realm = getStorage(name);
            realm.beginTransaction();
            realm.insertOrUpdate(product);
            realm.commitTransaction();
            realm.close();
        }
    }

    public static RealmResults<Product> getProducts(String name) {
        Realm realm = getStorage(name);

        return realm.where(Product.class).findAll();
    }

    public static Product cloneItem(Product product) {
        if (product == null) {
            return null;
        }

        Realm realm = Realm.getDefaultInstance();

        return realm.copyFromRealm(product);
    }
}
