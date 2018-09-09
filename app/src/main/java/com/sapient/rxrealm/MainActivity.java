/*
 * Copyright (c) 2018 Sapient. All rights reserved.
 * Created by Devishankar Ramasamy on 19-Aug-2018.
 */

package com.sapient.rxrealm;

import android.Manifest;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sapient.rxrealm.internal.CatalogAPIHandler;
import com.sapient.rxrealm.util.PersistenceUtil;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private long mStartTime;
    private String message = "";
    private TextView mMsg;
    private TextView txtResult;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMsg = findViewById(R.id.txt_msg);
        askPermissions();
        findViewById(R.id.btn_us_catalog).setOnClickListener(this);
        findViewById(R.id.btnloadproducts).setOnClickListener(this);
        txtResult = findViewById(R.id.txt_result);
        dialog = new ProgressDialog(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_us_catalog:
                loadCatalog(view.getTag().toString());
                break;
            case R.id.btnloadproducts:
                startActivity(new Intent(this, ListActivity.class));
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
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        dialog.setMessage("Loading catalog...");
                        dialog.show();
                        mStartTime = System.currentTimeMillis();
                        message += "Avail Memory Before : " + currentMem() + "MB";
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        long totalDuration = System.currentTimeMillis() - mStartTime;
                        message += "\nTotal execution time: " + totalDuration + "ms\n";
                        message += "Avail Memory After : " + currentMem() + "MB";
                        mMsg.setText(message);
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        message += "\nInsertion failed ";
                        mMsg.setText(message);
                        dialog.dismiss();
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
        dialog.dismiss();
    }
}
