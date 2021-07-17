package com.savatechnology.emall.Adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import com.savatechnology.emall.Activities.ProductDetailsActivity;
import com.savatechnology.emall.JSONSchemas.FeaturedProduct;
import com.savatechnology.emall.Models.HomeFeaturedProductList;
import com.savatechnology.emall.R;

import java.io.Serializable;
import java.util.List;

public class AdapterHomeFeaturedProduct extends RecyclerView.Adapter<AdapterHomeFeaturedProduct.MyViewHolder> {
    Context context;
    private List<FeaturedProduct> homeFeaturedProductList;

    public AdapterHomeFeaturedProduct(List<FeaturedProduct> lists, Context cntx ) {
        this.homeFeaturedProductList = lists;
        this.context = cntx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_featured_product, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String productId = homeFeaturedProductList.get(position).getId();
        String image = homeFeaturedProductList.get(position).getImage();
        String product_name = homeFeaturedProductList.get(position).getName();
        int product_price = homeFeaturedProductList.get(position).getPrice();


        //Log.v("SDc",image+product_name);


        Glide.with(context)
                .asBitmap()
                .load(image)
                .into(holder.imageProduct);


        holder.tvProductName.setText(product_name);
        holder.tvProductPrice.setText("Rs. "+product_price+"");



    }
    @Override
    public int getItemCount() {
        return homeFeaturedProductList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView tvProductName;
        TextView tvProductPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageHomeFeaturedProduct);
            tvProductName =(TextView) itemView.findViewById(R.id.tvHomeFeaturedProductName);
            tvProductPrice =(TextView) itemView.findViewById(R.id.tvHomeFeaturedProductPrice);


//calling for product detail screen


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
Log.v("abc",homeFeaturedProductList.get(getAdapterPosition()).getId());
                    intent.putExtra("productId", (Serializable) homeFeaturedProductList.get(getAdapterPosition()).getId());
                    intent.putExtra("productName", (Serializable) homeFeaturedProductList.get(getAdapterPosition()).getName());
                    intent.putExtra("productPrize", (Serializable) homeFeaturedProductList.get(getAdapterPosition()).getPrice());
                    intent.putExtra("productImage", (Serializable) homeFeaturedProductList.get(getAdapterPosition()).getImage());
                    intent.putExtra("productDescription", (Serializable) homeFeaturedProductList.get(getAdapterPosition()).getDesc());
                    intent.putExtra("supplierId", (Serializable) homeFeaturedProductList.get(getAdapterPosition()).getSupplier());
                    context.startActivity(intent);
                }
            });





        }
    }
}