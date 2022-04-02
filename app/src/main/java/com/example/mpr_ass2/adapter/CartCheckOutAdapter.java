package com.example.mpr_ass2.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr_ass2.R;
import com.example.mpr_ass2.model.Product;
import com.example.mpr_ass2.utils.DownloadImagesTask;

import java.util.ArrayList;

public class CartCheckOutAdapter extends RecyclerView.Adapter<CartCheckOutAdapter.CartCheckoutBinding> {

    ArrayList<Product> list;
    OnClickItemCheckout onClickItem;

    public CartCheckOutAdapter(ArrayList<Product> list, OnClickItemCheckout onClickItem) {
        this.list = list;

        this.onClickItem = onClickItem;
    }

    public void setList(ArrayList<Product> list){
        this.list = list;
    }

    @NonNull
    @Override
    public CartCheckoutBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_checkout, parent, false);
        return new CartCheckOutAdapter.CartCheckoutBinding(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartCheckoutBinding holder, @SuppressLint("RecyclerView") int position) {
        Product product = list.get(position);

        new DownloadImagesTask(holder.imgThumbnail).execute(product.getThumbnail());

        holder.tvName.setText(product.getName());
        holder.tvUnitPrice.setText("đ " + product.getUnitPrice());
        holder.tvQuantity.setText("" + product.getQuantity());
        Long sumPrice = product.getUnitPrice() * product.getQuantity();
        holder.tvSumPrice.setText("" + sumPrice);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickAddCheckout(position);
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getQuantity() != 0){
                    onClickItem.onClickMinusCheckout(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class CartCheckoutBinding extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvName;
        public TextView tvUnitPrice;
        public ImageButton btnAdd;
        public ImageButton btnMinus;
        public TextView tvSumPrice;
        public TextView tvQuantity;

        public CartCheckoutBinding(@NonNull View itemView) {
            super(itemView);
            //imgProduct = itemView.findViewById(R.id.);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnailCartCheckout);
            tvName = itemView.findViewById(R.id.tvNameCheckoutProduct);
            tvUnitPrice = itemView.findViewById(R.id.tvUnitPriceCheckoutProduct);
            btnAdd = itemView.findViewById(R.id.btnAddCartCheckout);
            btnMinus = itemView.findViewById(R.id.btnMinusCartCheckout);
            tvSumPrice = itemView.findViewById(R.id.tvSumPriceCart);
            tvQuantity = itemView.findViewById(R.id.tvQuantityCart);
        }
    }

    public interface OnClickItemCheckout {
        void onClickAddCheckout(int pos);

        void onClickMinusCheckout(int pos);
    }
}
