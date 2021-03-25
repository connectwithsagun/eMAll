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


import com.savatechnology.emall.Models.HomeSupplierList;
import com.savatechnology.emall.R;

import java.util.List;

public class AdapterHomeSupplier extends RecyclerView.Adapter<AdapterHomeSupplier.MyViewHolder> {
    Context context;
    private List<HomeSupplierList> homeSupplierList;

    public AdapterHomeSupplier(List<HomeSupplierList> lists, Context cntx ) {
        this.homeSupplierList = lists;
        this.context = cntx;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_supplier, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String image = homeSupplierList.get(position).getimgSupplier();
        String supplier_name = homeSupplierList.get(position).gettvSupplierName();


        //Log.v("SDc",image+product_name);


        Glide.with(context)
                .asBitmap()
                .load(image)
                .into(holder.imageSupplier);


        holder.tvSupplierName.setText(supplier_name);



    }
    @Override
    public int getItemCount() {
        return homeSupplierList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageSupplier;
        TextView tvSupplierName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSupplier = itemView.findViewById(R.id.imgHomeSupplier);
            tvSupplierName =(TextView) itemView.findViewById(R.id.tvHomeSupplierName);


        }
    }
}