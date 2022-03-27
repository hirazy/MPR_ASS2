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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartBinding> {

    ArrayList<Product> list;
    OnClickItem onClickItem;

    public ShoppingCartAdapter(ArrayList<Product> list, OnClickItem onClickItem) {
        this.list = list;

        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ShoppingCartBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ShoppingCartBinding(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartBinding holder, int position) {
        Product product = list.get(position);

        // Thumbnail
        new DownloadImagesTask(holder.imgThumbnailProduct).execute(product.thumbnail);

        // Name
        holder.tvNameProduct.setText(product.name);

        // Unit Price
        holder.tvUnitPriceProduct.setText("đ " + product.unitPrice);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ShoppingCartBinding extends RecyclerView.ViewHolder {

        public ImageView imgThumbnailProduct;
        public TextView tvNameProduct;
        public TextView tvUnitPriceProduct;

        public ShoppingCartBinding(@NonNull View itemView) {
            super(itemView);
            //imgProduct = itemView.findViewById(R.id.);
            imgThumbnailProduct = itemView.findViewById(R.id.imgThumbnailProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvUnitPriceProduct = itemView.findViewById(R.id.tvUnitPriceProduct);
        }
    }

    public interface OnClickItem{
        void onClick(int pos);

        void onClickAdd(int pos);
    }
}
