package com.example.mpr_ass2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
    LinearLayout layoutCartEmpty, layoutInfoCheckout;
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
        layoutInfoCheckout = findViewById(R.id.layoutInfoCheckout);
        layoutCartEmpty = findViewById(R.id.layoutCartEmpty);

        if(products.size() == 0){
            showCartEmpty();
        }
        else{
            showCart();
        }

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
        int quantity = products.get(pos).getQuantity();
        cartManager.update(products.get(pos), isPlus);
        products = cartManager.getAll();
        setTotal();
        adapter.setList(products);
        if (quantity == 1 && !isPlus) {
            // adapter.notifyItemChanged(pos - 1);
            adapter.notifyDataSetChanged();
        }
        else {
            adapter.notifyItemChanged(pos);
        }

        if(products.size() == 0){
            showCartEmpty();
        }
        else{
            showCart();
        }
    }

    public void showCart(){
        layoutInfoCheckout.setVisibility(View.VISIBLE);
        layoutCartEmpty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void showCartEmpty(){
        layoutInfoCheckout.setVisibility(View.GONE);
        layoutCartEmpty.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void setTotal() {
        long sum = 0;
        for (int i = 0; i < products.size(); i++) {
            long totalCheckout = products.get(i).getUnitPrice() * products.get(i).getQuantity();
            sum += totalCheckout;
        }
        tvTotal.setText("đ " + Long.toString(sum));
    }
}