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
import com.savatechnology.emall.Models.HomeFeaturedProductList;
import com.savatechnology.emall.R;

import java.util.List;

public class AdapterHomeFeaturedProduct extends RecyclerView.Adapter<AdapterHomeFeaturedProduct.MyViewHolder> {
    Context context;
    private List<HomeFeaturedProductList> homeFeaturedProductList;

    public AdapterHomeFeaturedProduct(List<HomeFeaturedProductList> lists, Context cntx ) {
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
        String image = homeFeaturedProductList.get(position).getimgSupplier();
        String product_name = homeFeaturedProductList.get(position).gettvProductName();
        int product_price = homeFeaturedProductList.get(position).gettvProductPrice();

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
                    // intent.putExtra("product", (Serializable) homeFeaturedProductList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });




        }
    }
}