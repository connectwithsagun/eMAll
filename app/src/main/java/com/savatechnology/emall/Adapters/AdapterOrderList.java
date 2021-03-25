package com.savatechnology.emall.Adapters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.savatechnology.emall.Models.OrderList;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Models.OrderList;
import java.util.List;

public class AdapterOrderList extends RecyclerView.Adapter<AdapterOrderList.MyViewHolder> {
    private List<OrderList> lists;
    public AdapterOrderList(List<OrderList> lists) {
        this.lists = lists;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int image = lists.get(position).getimgProduct();
        String product_title = lists.get(position).gettvProductTitle();
        String supplier_name = lists.get(position).gettvSupplierName();
        String product_status_result = lists.get(position).gettvProductStatusResult();


        holder.imgProduct.setImageResource(image);
        holder.tvProductTitle.setText(product_title);
        holder.tvSupplierName.setText(supplier_name);
        holder.tvProductStatusResult.setText(product_status_result);

    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvProductTitle;
        TextView tvSupplierName;
        TextView tvProductStatusResult;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductTitle = (TextView)itemView.findViewById(R.id.tvProductTitle);
            tvSupplierName =(TextView) itemView.findViewById(R.id.tvSupplierName);
            tvProductStatusResult =(TextView) itemView.findViewById(R.id.tvProductStatusResult);


        }
    }
}