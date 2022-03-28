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
import com.example.mpr_ass2.model.ProductDatabase;
import com.example.mpr_ass2.utils.DownloadImagesTask;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartCheckOutAdapter extends RecyclerView.Adapter<CartCheckOutAdapter.CartCheckoutBinding> {

    ArrayList<ProductDatabase> list;
    OnClickItemCheckout onClickItem;

    public CartCheckOutAdapter(ArrayList<ProductDatabase> list, OnClickItemCheckout onClickItem) {
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
    public void onBindViewHolder(@NonNull CartCheckoutBinding holder, @SuppressLint("RecyclerView") int position) {
        ProductDatabase productDatabase = list.get(position);

        new DownloadImagesTask(holder.imgThumbnail).execute(productDatabase.thumbnail);

        holder.tvName.setText(productDatabase.name);
        holder.tvUnitPrice.setText("đ " + productDatabase.unitPrice);
        holder.tvQuantity.setText("" + productDatabase.quantity);
        Long sumPrice = productDatabase.unitPrice * productDatabase.quantity;
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
                if(productDatabase.quantity != 0){
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
