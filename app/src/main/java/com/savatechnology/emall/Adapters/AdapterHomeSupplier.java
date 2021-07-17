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


import com.savatechnology.emall.Activities.SuppliersDetailActivity;
import com.savatechnology.emall.JSONSchemas.Suppliers;
import com.savatechnology.emall.Models.HomeSupplierList;
import com.savatechnology.emall.R;

import java.io.Serializable;
import java.util.List;

public class AdapterHomeSupplier extends RecyclerView.Adapter<AdapterHomeSupplier.MyViewHolder> {
    Context context;
    private List<Suppliers> homeSupplierList;

    public AdapterHomeSupplier(List<Suppliers> lists, Context cntx ) {
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
        String image = homeSupplierList.get(position).getImage();
        String supplier_name = homeSupplierList.get(position).getName();


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
            tvSupplierName = (TextView) itemView.findViewById(R.id.tvHomeSupplierName);


            //for calling supplier details screen


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SuppliersDetailActivity.class);
//                    intent.putExtra("Course", (Serializable) homeSupplierList.get(getAdapterPosition()));
//                    intent.putExtra("supplierName",name);
//                    intent.putExtra("supplierLocation",location);
//                    intent.putExtra("supplierPhone",phone);
//                    intent.putExtra("supplierImage",image);
                    Log.v("abc", String.valueOf(homeSupplierList.get(getAdapterPosition()).getPhone()));

                    intent.putExtra("supplierName", (Serializable) homeSupplierList.get(getAdapterPosition()).getName());
                    intent.putExtra("supplierLocation", (Serializable) homeSupplierList.get(getAdapterPosition()).getLocation());
                    intent.putExtra("supplierPhone", (Serializable) String.valueOf(homeSupplierList.get(getAdapterPosition()).getPhone()));
                    intent.putExtra("supplierImage", (Serializable) homeSupplierList.get(getAdapterPosition()).getImage());
                    context.startActivity(intent);
                }
            });

        }
    }
}