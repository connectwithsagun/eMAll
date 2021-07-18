package com.savatechnology.emall.Adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.savatechnology.emall.JSONSchemas.Cart;
import com.savatechnology.emall.Models.CartList;
import com.savatechnology.emall.R;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyViewHolder> {
    Context context;
    private List<Cart> lists;
    public AdapterCart(List<Cart> lists,Context context) {
        this.lists = lists;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String image = lists.get(position).getProduct().getImage();
        String product_name = lists.get(position).getProduct().getName();
        int product_price = lists.get(position).getProduct().getPrice();
       // int product_shipping_cost = lists.get(position).getProduct().get;



        //Log.v("abc",image);

        //adding images from online

      //  Log.v("abc", String.valueOf(product_price));

        Glide.with(context)
                .asBitmap()
                .load(image)
                .into(holder.imgProduct);


        holder.tvProductTitle.setText(product_name);
        holder.tvProductPrice.setText("Rs " +product_price);
     //   holder.tvShippingCost.setText("Rs "+product_shipping_cost);

    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvProductTitle;
        TextView tvProductPrice;
        TextView tvShippingCost;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductTitle =(TextView) itemView.findViewById(R.id.tvProductTitle);
            tvProductPrice =(TextView) itemView.findViewById(R.id.tvProductPrice);
           // tvShippingCost =(TextView) itemView.findViewById(R.id.tvShippingCost);


        }

    }
}