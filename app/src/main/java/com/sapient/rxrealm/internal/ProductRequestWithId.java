/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.LongSparseArray;

import com.sapient.rxrealm.model.Product;
import com.sapient.rxrealm.model.Recipe;
import com.sapient.rxrealm.model.RecipeItem;
import com.sapient.rxrealm.util.PersistenceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Handles the catalog data to get product and it's full recipe.
 *
 * @see <a href="https://us-jira.mcd.com/browse/SDK-13313">SDK-13313</a>
 */
@ThreadSafe
final class ProductRequestWithId extends Single<Product> {

    private static final String TAG = "ProductRequestWithId";

    private static final long NANO_TO_MILL = 1000L * 1000L;

    private final int mProductId;
    private final String mPath;
    private LongSparseArray<Product> mProducts;
    private LongSparseArray<Product> mCachedFullProduct = new LongSparseArray<>();

    /**
     * Gets the {@link Product} by it's id for the store specified.
     *
     * @param productId id to get full recipe of a product.
     */
    ProductRequestWithId(@NonNull String path, int productId) {
        this.mPath = path;
        this.mProductId = productId;
    }

    @Override
    protected synchronized void subscribeActual(SingleObserver<? super Product> observer) {
        try {

            long startTime = System.currentTimeMillis();

            Product product = getProduct();

            long totalTime = System.currentTimeMillis() - startTime;
            Log.d(TAG, "Time to build full recipe for product " + mProductId + " " +
                    totalTime + "ms");

            if (product != null) {
                observer.onSuccess(product);
            } else {
                observer.onError(new Throwable("Product is null"));
            }
        } catch (Exception ex) {
            observer.onError(ex);
        } finally {
            mProducts = null;
            mCachedFullProduct = null;
        }
    }

    /**
     * Finding the product using ProductId from the selected Storage and initiates fetch
     * full recipe based on recipe items available for the product.
     *
     * @see <a href="https://us-jira.mcd.com/browse/SDK-13313">SDK-13313</a>
     */
    @Nullable
    private Product getProduct() {
        Realm storage = PersistenceUtil.getStorage(mPath);
        RealmQuery<Product> query = storage.where(Product.class);
        query.equalTo(Product.PRIMARY_KEY, mProductId);

        // Fetching the product data based on ProductId from storage.
        Product product = PersistenceUtil.cloneItem(query.findFirst());
        if (product != null) {

            // Initiating fetch full recipe based on the parent Product.
            processRecipe(product);

            return product;
        }
        return null;
    }

    /**
     * Updates {@link Product} recipe items.
     *
     * @param product that needs full recipe.
     * @see <a href="https://us-jira.mcd.com/browse/SDK-13313">SDK-13313</a>
     */
    private void processRecipe(@NonNull Product product) {

        long id = product.getId();
        Log.d(TAG, "Initiating update recipe for product " + id);
        Recipe recipe = product.getRecipe();
        if (recipe != null) {
            Log.d(TAG, "Processing Recipes");

            List<RecipeItem> choices = recipe.getChoices();
            List<RecipeItem> extras = recipe.getExtras();
            List<RecipeItem> comments = recipe.getComments();
            List<RecipeItem> ingredients = recipe.getIngredients();

            // Updating products for each recipe type available
            if (!choices.isEmpty()) {
                Log.d(TAG, "Processing choices " + id);
                updateRecipeProducts(product, choices);
            }
            if (!extras.isEmpty()) {
                Log.d(TAG, "Processing extras " + id);
                updateRecipeProducts(product, extras);
            }
            if (!comments.isEmpty()) {
                Log.d(TAG, "Processing comments " + id);
                updateRecipeProducts(product, comments);
            }
            if (!ingredients.isEmpty()) {
                Log.d(TAG, "Processing ingredients " + id);
                updateRecipeProducts(product, ingredients);
            }
        }
    }

    /**
     * Gets all the products available for recipeItems and updates it's respective recipe products
     * and initiated {@link ProductRequestWithId#processRecipe(Product)} to process it's recipe
     * again to build full recipe.
     *
     * @param product     on which we need to update recipe items and it's Products
     * @param recipeItems list of different types of RecipeItem.
     * @see <a href="https://us-jira.mcd.com/browse/SDK-13313">SDK-13313</a>
     */
    private void updateRecipeProducts(@NonNull Product product,
                                      @NonNull List<RecipeItem> recipeItems) {
        long startTime = 0L;
        startTime = System.nanoTime();
        Log.d(TAG, "Total recipe count for product " + product.getId() + " " +
                recipeItems.size());
        Log.d(TAG, "Query building started " + startTime / NANO_TO_MILL);

        List<Product> products = new ArrayList<>();
        for (RecipeItem recipeItem : recipeItems) {

            Product recipeProduct = getProduct(recipeItem.getProductCode());

            if (recipeItem.getReferencePriceProductCode() != 0) {
                Product priceProduct = getProduct(recipeItem.getReferencePriceProductCode());
                if (priceProduct != null) {
                    recipeItem.setReferencePriceProduct(recipeProduct);
                }
            }

            if (recipeProduct != null) {
                if (mCachedFullProduct.get(recipeProduct.getId()) == null) {
                    products.add(recipeProduct);
                } else {
                    recipeProduct = mCachedFullProduct.get(recipeProduct.getId());
                }
                recipeItem.setProduct(recipeProduct);
            }
        }

        Log.d(TAG, "Query building completed " + (System.nanoTime() - startTime) / NANO_TO_MILL);

        long totalTime = System.nanoTime() - startTime;
        Log.d(TAG, "Total recipe's products fetched " + totalTime + " " + totalTime / NANO_TO_MILL);

        for (Product recipeProduct : products) {
            processRecipe(recipeProduct);
            mCachedFullProduct.put(recipeProduct.getId(), recipeProduct);
        }
    }

    private Product getProduct(long productCode) {
        if (mProducts == null) {
            mProducts = new LongSparseArray<>();
            // Executing the query to get all the products from the Storage.
            Realm storage = PersistenceUtil.getStorage(mPath);
            for (Product product : storage.where(Product.class).findAll()) {
                mProducts.put(product.getId(), product);
            }
        }

        return PersistenceUtil.cloneItem(mProducts.get(productCode));
    }
}
