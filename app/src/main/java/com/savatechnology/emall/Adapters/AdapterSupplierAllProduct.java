package com.savatechnology.emall.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.savatechnology.emall.JSONSchemas.FeaturedProduct;
import com.savatechnology.emall.JSONSchemas.SupplierProduct;
import com.savatechnology.emall.Models.SupplierProductList;
import com.savatechnology.emall.R;

import java.util.List;

public class AdapterSupplierAllProduct extends RecyclerView.Adapter<AdapterSupplierAllProduct.MyViewHolder> {
    Context context;
    private List<SupplierProduct> lists;
    public AdapterSupplierAllProduct(List<SupplierProduct> lists, Context mContext) {
        this.lists = lists;
        context = mContext;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supplier_products, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String  image = lists.get(position).getImage();
        String product_name = lists.get(position).getName();
        Integer product_price = lists.get(position).getPrice();



        //holder.imageProduct.setImageResource(image);


        Glide.with(context)
                .asBitmap()
                .load(image)
                .into(holder.imageProduct);
        holder.tvProductName.setText(product_name);
        holder.tvProductPrice.setText(product_price.toString());


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
            tvProductPrice =(TextView) itemView.findViewById(R.id.tvItemProductPrice);


        }
    }
}