package com.sapient.rxrealm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sapient.rxrealm.model.Product;

import java.util.List;

public class productsAdapter extends ArrayAdapter<Product> {

    Context context;
    int layout;
    List<Product> products;

    public productsAdapter(@NonNull Context context, int resource,
                           @NonNull List<Product> products) {
        super(context, resource, products);
        this.context = context;
        this.layout = resource;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Nullable
    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout, parent, false);
        TextView productid = (TextView) rowView.findViewById(R.id.product_id);
        TextView productname = (TextView) rowView.findViewById(R.id.product_name);

        productid.setText(String.valueOf(products.get(position).getId()));
        productname.setText(products.get(position).getProductName().getLongName());

        return rowView;
    }
}
