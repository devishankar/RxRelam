/*
 * Copyright (c) 2018 McDonald's. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm;

import android.Manifest;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sapient.rxrealm.internal.CatalogAPIHandler;
import com.sapient.rxrealm.model.Product;
import com.sapient.rxrealm.util.Util;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private long startTime;
    private String message = "";
    private TextView mMsg;
    private TextView txtResult;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMsg = findViewById(R.id.txt_msg);
        askPermissions();
        findViewById(R.id.btn_us_catalog).setOnClickListener(this);
        findViewById(R.id.btn_cn_catalog).setOnClickListener(this);
        findViewById(R.id.btn_au_catalog).setOnClickListener(this);
        findViewById(R.id.btn_us_product).setOnClickListener(this);
        findViewById(R.id.btn_cn_product).setOnClickListener(this);
        findViewById(R.id.btn_au_product).setOnClickListener(this);
        txtResult = findViewById(R.id.txt_result);
        mDialog = new ProgressDialog(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_us_catalog:
            case R.id.btn_au_catalog:
            case R.id.btn_cn_catalog:
                loadCatalog(view.getTag().toString());
                break;
            case R.id.btn_us_product:
            case R.id.btn_au_product:
            case R.id.btn_cn_product:
                loadProduct(view.getTag().toString());
                break;
            default:
                Log.i(TAG, "Nothing to handle");
                break;
        }
    }

    private void loadCatalog(String path) {
        message = "";
        CatalogAPIHandler handler = new CatalogAPIHandler();
        handler.insertProductsIntoStorage(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDialog.setMessage("Loading catalog...");
                        mDialog.show();
                        startTime = System.currentTimeMillis();
                        message += "Avail Memory Before : " + currentMem() + "MB";
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        long totalDuration = System.currentTimeMillis() - startTime;
                        message += "\nTotal execution time: " + totalDuration + "ms\n";
                        message += "Avail Memory After : " + currentMem() + "MB";
                        mMsg.setText(message);
                        mDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        message += "Insertion failed ";
                        mMsg.setText(message);
                        mDialog.dismiss();
                    }
                });
    }

    private void loadProduct(String path) {
        EditText editText = findViewById(R.id.txt_product_code);
        String strProductCode = editText.getText().toString();
        if (TextUtils.isEmpty(strProductCode)) {
            editText.setError("Enter product code");
            return;
        }

        int productCode = Integer.parseInt(editText.getText().toString());
        CatalogAPIHandler handler = new CatalogAPIHandler();

        handler.getProduct(path, productCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Product>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDialog.setMessage("Build product...");
                        mDialog.show();
                        Util.hideKeyBoard(MainActivity.this);
                    }

                    @Override
                    public void onSuccess(Product product) {
                        Toast.makeText(MainActivity.this, "Loaded product", Toast.LENGTH_SHORT)
                                .show();
                        txtResult.setText(Util.getPrettyGson().toJson(product));
                        mDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Failed to load product",
                                Toast.LENGTH_SHORT).show();
                        txtResult.setText("");
                        mDialog.dismiss();
                    }
                });
    }

    private void askPermissions() {
        String[] permissionList = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!hasAllPermissions(permissionList)) {
            ActivityCompat.requestPermissions(this, permissionList, 1);
        }
    }

    private boolean hasAllPermissions(String[] permissionList) {
        for (String permission : permissionList) {
            return this.checkPermission(permission, Process.myPid(), Process.myUid())
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private double currentMem() {
        try {
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) getSystemService(
                    Context.ACTIVITY_SERVICE);
            activityManager.getMemoryInfo(mi);
            return mi.availMem / 0x100000L;
        } catch (Exception ex) {
            return -1.0;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDialog.dismiss();
    }
}
