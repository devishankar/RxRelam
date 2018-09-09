package com.sapient.rxrealm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sapient.rxrealm.internal.CatalogAPIHandler;
import com.sapient.rxrealm.model.Product;
import com.sapient.rxrealm.util.Util;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ListActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ListView list;
    private productsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dialog = new ProgressDialog(ListActivity.this);
        findViewById(R.id.load_products).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProduct();
            }
        });
        list = findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                delete(i);
            }
        });
    }

    private void delete(int position) {
        Product product = adapter.getItem(position);

        product.getProductName().setLongName("some name");
    }

    private void loadProduct() {

        CatalogAPIHandler handler = new CatalogAPIHandler();

        handler.getProducts("us_catalog")
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        dialog.setMessage("Build product...");
                        dialog.show();
                        Util.hideKeyBoard(ListActivity.this);
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        adapter = new productsAdapter(ListActivity.this,
                                R.layout.product_list, products);
                        list.setAdapter(adapter);

                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ListActivity.this, "Failed to load product",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }
}
