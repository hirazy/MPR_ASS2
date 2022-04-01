package com.example.mpr_ass2.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mpr_ass2.R;
import com.example.mpr_ass2.model.Product;
import com.example.mpr_ass2.utils.DownloadImagesTask;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartBinding>
    implements Filterable {

    ArrayList<Product> listAll;
    OnClickItem onClickItem;
    private ArrayList<Product> listFiltered;

    public ShoppingCartAdapter(ArrayList<Product> list, OnClickItem onClickItem) {
        this.listAll = list;
        this.listFiltered = list;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ShoppingCartBinding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ShoppingCartBinding(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartBinding holder, @SuppressLint("RecyclerView") int position) {
        Product product = listFiltered.get(position);

        // Thumbnail
        new DownloadImagesTask(holder.imgThumbnailProduct).execute(product.getThumbnail());

        // Name
        holder.tvNameProduct.setText(product.getName());

        // Unit Price
        holder.tvUnitPriceProduct.setText("đ " + product.getUnitPrice());

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickAdd(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = listAll;
                } else {
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product row : listAll) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ShoppingCartBinding extends RecyclerView.ViewHolder {

        public ImageView imgThumbnailProduct;
        public TextView tvNameProduct;
        public TextView tvUnitPriceProduct;
        public ImageButton btnAdd;

        public ShoppingCartBinding(@NonNull View itemView) {
            super(itemView);
            //imgProduct = itemView.findViewById(R.id.);
            imgThumbnailProduct = itemView.findViewById(R.id.imgThumbnailProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvUnitPriceProduct = itemView.findViewById(R.id.tvUnitPriceProduct);
            btnAdd = itemView.findViewById(R.id.btnAddCart);
        }
    }

    public interface OnClickItem{
        void onClick(int pos);

        void onClickAdd(int pos);
    }
}
