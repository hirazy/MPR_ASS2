package com.example.mpr_ass2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.mpr_ass2.adapter.ShoppingCartAdapter;
import com.example.mpr_ass2.data.CartManager;
import com.example.mpr_ass2.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShoppingCartAdapter.OnClickItem {

    private LottieAnimationView animShoppingItem;
    private ShoppingCartAdapter shoppingCartAdapter;
    private RecyclerView rcvShoppingCart;
    private ArrayList<Product> products;
    private ProgressBar progressBar;
    private CartManager cartManager;
    private EditText edtProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        animShoppingItem = findViewById(R.id.animShoppingMain);
        animShoppingItem.setAnimation(R.raw.shopping_cart);
        animShoppingItem.playAnimation();

        animShoppingItem.setRepeatCount(LottieDrawable.INFINITE);

        animShoppingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);

                startActivity(intent);
            }
        });

        products = new ArrayList<>();

        progressBar = findViewById(R.id.pbMain);
        rcvShoppingCart = findViewById(R.id.rcvShoppingCart);
        rcvShoppingCart.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        cartManager = CartManager.getInstance(this);

        GetProductList getProductList = new GetProductList();
        getProductList.execute();

        edtProduct = findViewById(R.id.edtProduct);

        edtProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                shoppingCartAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public class GetProductList extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray jsonArray = new JSONArray(s);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    products.add(new Product(
                            jsonObject.getInt("id"),
                            jsonObject.getString("thumbnail"),
                            jsonObject.getString("name"),
                            jsonObject.getLong("unitPrice")
                    ));
                }

                progressBar.setVisibility(View.GONE);
                rcvShoppingCart.setVisibility(View.VISIBLE);
                shoppingCartAdapter = new ShoppingCartAdapter(products, MainActivity.this);
                rcvShoppingCart.setAdapter(shoppingCartAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(Constants.BASE_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();

                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);
                    }
                    Log.d("datalength", "" + current.length());
                    // return the data to onPostExecute method
                    return current;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }
    }

    @Override
    public void onClick(int pos) {

    }

    @Override
    public void onClickAdd(Product product) {
        Log.e("onClickAdd", product.getId() + "");
        cartManager.update(product, true);
    }
}