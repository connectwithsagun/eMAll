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
import com.savatechnology.emall.Activities.SuppliersDetailActivity;
import com.savatechnology.emall.JSONSchemas.Suppliers;
import com.savatechnology.emall.Models.SuppliersList;
import com.savatechnology.emall.R;

import java.io.Serializable;
import java.util.List;

public class AdapterSuppliers extends RecyclerView.Adapter<AdapterSuppliers.MyViewHolder> {
    private List<Suppliers> lists;
    Context context;
    public AdapterSuppliers(List<Suppliers> lists ,Context cntx) {
        this.context= cntx;
        this.lists = lists;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suppliers, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String image = lists.get(position).getImage();
        String supplier_name = lists.get(position).getName();
        String supplier_address = lists.get(position).getLocation();

        Glide.with(context)
                .asBitmap()
                .load(image)
                .into(holder.imgSupplier);

      //  holder.imgSupplier.setImageResource(image);
        holder.tvSupplierName.setText(supplier_name);
        holder.tvSupplierAddress.setText(supplier_address);

    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSupplier;
        TextView tvSupplierName;
        TextView tvSupplierAddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSupplier = itemView.findViewById(R.id.imgSupplier);
            tvSupplierName =(TextView) itemView.findViewById(R.id.tvSupplierName);
            tvSupplierAddress =(TextView) itemView.findViewById(R.id.tvSupplierAddress);



            //for calling supplier details screen


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SuppliersDetailActivity.class);
                    //intent.putExtra("Course", (Serializable) lists.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });






        }
    }
}