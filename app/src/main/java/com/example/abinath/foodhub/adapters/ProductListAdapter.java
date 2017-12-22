package com.example.abinath.foodhub.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abinath.foodhub.adapters.ViewHolders.ProductViewHolder;
import com.example.abinath.foodhub.model.Product;
import com.example.abinath.foodhub.model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Abinath on 10-Sep-17.
 */


/*
Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context mContext;
    private int layoutresource;
    private User user;
    private boolean isCart;
    private ArrayList<Product> products;
    private HashMap<Product,Integer> cartProducts;
    private static String TAG = ProductListAdapter.class.getSimpleName();

    public ProductListAdapter(Context mContext, int layoutresource, User user,ArrayList<Product> products) {
        this.mContext = mContext;
        this.layoutresource = layoutresource;
        this.user = user;
        this.isCart = false;
        this.products = products;
    }

    public ProductListAdapter(Context mContext, int layoutresource, User user, HashMap<Product,Integer> products) {
        this.mContext = mContext;
        this.layoutresource = layoutresource;
        this.user = user;
        this.isCart = true;
        this.cartProducts = products;
        Log.d(TAG, "ProductListAdapter: "+products.size());
    }

    /*
     Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     */
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layoutresource, parent, false);
        return new ProductViewHolder(view,mContext);
    }

    /*
     Called by RecyclerView to display the data at the specified position.
     */
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if(isCart){
            Product[] productArray = new Product[cartProducts.size()];
            cartProducts.keySet().toArray(productArray);
            Log.d(TAG, "onBindViewHolder: "+productArray[position].getId());
            holder.setValues(productArray[position],user,isCart, cartProducts.get(productArray[position]));
        }else {
            holder.setValues(products.get(position), user, isCart,0);
        }
    }

    /*
     Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return products!=null ? products.size() : cartProducts != null ? cartProducts.size() : 0;
    }
}
