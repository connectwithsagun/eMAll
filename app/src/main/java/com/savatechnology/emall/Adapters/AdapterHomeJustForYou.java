package com.savatechnology.emall.Adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;


import com.savatechnology.emall.Models.HomeJustForYouList;
import com.savatechnology.emall.R;

import java.util.List;

public class AdapterHomeJustForYou extends RecyclerView.Adapter<AdapterHomeJustForYou.MyViewHolder> {
    Context context;
    private List<HomeJustForYouList> homeJustForYouList;

    public AdapterHomeJustForYou(List<HomeJustForYouList> lists, Context cntx ) {
        this.homeJustForYouList = lists;
        this.context = cntx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_just_for_you, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String image = homeJustForYouList.get(position).getimgProduct();
        String product_name = homeJustForYouList.get(position).gettvProductName();
        int product_price = homeJustForYouList.get(position).gettvProductPrice();

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
        return homeJustForYouList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView tvProductName;
        TextView tvProductPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageHomeJustForYou);
            tvProductName =(TextView) itemView.findViewById(R.id.tvHomeJustForYou);
            tvProductPrice =(TextView) itemView.findViewById(R.id.tvHomeJustForYouProductPrice);


        }
    }
}