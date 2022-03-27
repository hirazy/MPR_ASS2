package com.example.mpr_ass2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr_ass2.R;
import com.example.mpr_ass2.model.Product;
import com.example.mpr_ass2.utils.DownloadImagesTask;

import java.util.ArrayList;

public class CartCheckOutAdapter extends RecyclerView.Adapter<CartCheckOutAdapter.CartCheckoutBinding>{

    ArrayList<Product> list;
    ShoppingCartAdapter.OnClickItem onClickItem;

    public CartCheckOutAdapter(ArrayList<Product> list, ShoppingCartAdapter.OnClickItem onClickItem) {
        this.list = list;

        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public CartCheckoutBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartCheckOutAdapter.CartCheckoutBinding(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartCheckoutBinding holder, int position) {

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class CartCheckoutBinding extends RecyclerView.ViewHolder {

        public ImageView imgThumbnailProduct;
        public TextView tvNameProduct;
        public TextView tvUnitPriceProduct;

        public CartCheckoutBinding(@NonNull View itemView) {
            super(itemView);
            //imgProduct = itemView.findViewById(R.id.);
            imgThumbnailProduct = itemView.findViewById(R.id.imgThumbnailProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvUnitPriceProduct = itemView.findViewById(R.id.tvUnitPriceProduct);
        }
    }

    public interface OnClickItemCheckout{
        void onClickAddCheckout(int pos);

        void onClickRemoveCheckout(int pos);
    }
}
