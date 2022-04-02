package com.example.mpr_ass2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mpr_ass2.adapter.CartCheckOutAdapter;
import com.example.mpr_ass2.data.CartManager;
import com.example.mpr_ass2.model.Product;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CartActivity extends AppCompatActivity implements CartCheckOutAdapter.OnClickItemCheckout {

    CartManager cartManager;
    CartCheckOutAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Product> products;
    LottieAnimationView animCartShopping, animToolBar;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();
    }

    public void init() {
        products = new ArrayList<>();
        cartManager = CartManager.getInstance(this);

        products = cartManager.getAll();

        adapter = new CartCheckOutAdapter(products, this);

        recyclerView = findViewById(R.id.rcvCartCheckout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animCartShopping = findViewById(R.id.animCartShopping);

        animToolBar = findViewById(R.id.animToolBar);

        tvTotal = findViewById(R.id.tvTotal);

        setTotal();

        animCartShopping = findViewById(R.id.animCartShopping);
        animCartShopping.setAnimation(R.raw.shopping_cart);
        animCartShopping.playAnimation();

        animToolBar = findViewById(R.id.animToolBar);
        animToolBar.setAnimation(R.raw.shopping_cart);
        animToolBar.playAnimation();

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClickAddCheckout(int pos) {
        updateItem(pos, true);
    }

    @Override
    public void onClickMinusCheckout(int pos) {
        updateItem(pos, false);
    }

    public void updateItem(int pos, boolean isPlus) {
        cartManager.update(products.get(pos), isPlus);
        products = cartManager.getAll();
        setTotal();
        adapter.setList(products);
        adapter.notifyItemChanged(pos);
    }

    public void setTotal(){
        long sum = 0;
        for(int i = 0; i < products.size();i++){
            long totalCheckout = products.get(i).getUnitPrice() * products.get(i).getQuantity();
            sum += totalCheckout;
        }
        tvTotal.setText("đ " + Long.toString(sum));
    }
}