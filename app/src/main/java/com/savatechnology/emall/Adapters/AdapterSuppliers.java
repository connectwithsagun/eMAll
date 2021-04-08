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

import com.savatechnology.emall.Activities.ProductDetailsActivity;
import com.savatechnology.emall.Activities.SuppliersDetailActivity;
import com.savatechnology.emall.Models.SuppliersList;
import com.savatechnology.emall.R;

import java.io.Serializable;
import java.util.List;

public class AdapterSuppliers extends RecyclerView.Adapter<AdapterSuppliers.MyViewHolder> {
    private List<SuppliersList> lists;
    Context context;
    public AdapterSuppliers(List<SuppliersList> lists ,Context cntx) {
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
        int image = lists.get(position).getimgSupplier();
        String supplier_name = lists.get(position).gettvSupplierName();
        String supplier_address = lists.get(position).gettvSupplierAddress();



        holder.imgSupplier.setImageResource(image);
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
                    intent.putExtra("Course", (Serializable) lists.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });






        }
    }
}