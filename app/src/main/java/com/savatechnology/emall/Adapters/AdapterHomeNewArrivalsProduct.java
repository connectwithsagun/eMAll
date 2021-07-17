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
import com.savatechnology.emall.JSONSchemas.NewArrivalProduct;
import com.savatechnology.emall.Models.HomeNewArrivalsProductList;
import com.savatechnology.emall.R;

import java.util.List;

public class AdapterHomeNewArrivalsProduct extends RecyclerView.Adapter<AdapterHomeNewArrivalsProduct.MyViewHolder> {
    Context context;
    private List<NewArrivalProduct> homeNewArrivalsProductList;

    public AdapterHomeNewArrivalsProduct(List<NewArrivalProduct> lists, Context cntx ) {
        this.homeNewArrivalsProductList = lists;
        this.context = cntx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_new_arrivals_product, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String image = homeNewArrivalsProductList.get(position).getImage();
        String product_name = homeNewArrivalsProductList.get(position).getName();
        int product_price = homeNewArrivalsProductList.get(position).getPrice();

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
        return homeNewArrivalsProductList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView tvProductName;
        TextView tvProductPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageHomeNewArrivalsProduct);
            tvProductName =(TextView) itemView.findViewById(R.id.tvHomeNewArrivalsdProductName);
            tvProductPrice =(TextView) itemView.findViewById(R.id.tvHomeNewArrivalsProductPrice);


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