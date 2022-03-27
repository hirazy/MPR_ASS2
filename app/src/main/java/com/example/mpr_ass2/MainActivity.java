package com.example.mpr_ass2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mpr_ass2.adapter.ShoppingCartAdapter;

public class MainActivity extends AppCompatActivity implements ShoppingCartAdapter.OnClickItem {

    private LottieAnimationView animShoppingItem;
    private ShoppingCartAdapter shoppingCartAdapter;
    private RecyclerView rcvShoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        animShoppingItem = findViewById(R.id.animShoppingMain);
        animShoppingItem.setAnimation(R.raw.ic_shopping_main);
        animShoppingItem.playAnimation();

        animShoppingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);

                startActivity(intent);
            }
        });


        rcvShoppingCart = findViewById(R.id.rcvShoppingCart);
        rcvShoppingCart.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        shoppingCartAdapter = new ShoppingCartAdapter(Constants.listProduct(), this);
        rcvShoppingCart.setAdapter(shoppingCartAdapter);

    }

    @Override
    public void onClick(int pos) {

    }

    @Override
    public void onClickAdd(int pos) {

    }
}