package com.savatechnology.emall.Adapters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.savatechnology.emall.Models.ProductList;
import com.savatechnology.emall.R;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyViewHolder> {
    private List<ProductList> lists;
    public AdapterProduct(List<ProductList> lists) {
        this.lists = lists;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int image = lists.get(position).getimageProduct();
        String product_name = lists.get(position).gettvProductName();
        String product_price = lists.get(position).gettvProductPrice();



        holder.imageProduct.setImageResource(image);
        holder.tvProductName.setText(product_name);
        holder.tvProductPrice.setText(product_price);


    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView tvProductName;
        TextView tvProductPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            tvProductName = (TextView)itemView.findViewById(R.id.tvProductName);
            tvProductPrice =(TextView) itemView.findViewById(R.id.tvProductPrice);


        }
    }
}