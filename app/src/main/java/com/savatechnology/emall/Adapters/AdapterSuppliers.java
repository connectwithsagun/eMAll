package com.savatechnology.emall.Adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.savatechnology.emall.Activities.ProductDetailsActivity;
import com.savatechnology.emall.Activities.SuppliersDetailActivity;
import com.savatechnology.emall.JSONSchemas.Suppliers;
import com.savatechnology.emall.Models.SuppliersList;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                   intent.putExtra("supplierId", lists.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

           //         String abc = lists.get(getAdapterPosition()).getId();


//                    ApiService apiService = ApiUtil.getApiService();
//                    apiService.getSupplierDetail(abc).enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            if(response.isSuccessful()){
//                                try {
//                                    String val = response.body().string();
//                                    JSONObject obj = new JSONObject(val);
//                                    String name = obj.getString("name");
//                                    String location = obj.getString("location");
//                                    String phone = obj.getString("phone");
//                                    String image = obj.getString("image");
//
////                                Log.v("abc",name);
////                                Log.v("abc",location);
////                                Log.v("abc",phone);
////                                Log.v("abc",image);
//
//                                    Intent intent = new Intent(context, SuppliersDetailActivity.class);
//                                    intent.putExtra("supplierName",name);
//                                    intent.putExtra("supplierLocation",location);
//                                    intent.putExtra("supplierPhone",phone);
//                                    intent.putExtra("supplierImage",image);
//                                    intent.putExtra("supplierId",abc);
//                                    context.startActivity(intent);
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                // startActivity(new Intent(ProductDetailsActivity.this,SuppliersDetailActivity.class));
//                            }
//                            else{
//                                Toast.makeText(context, "" +response.errorBody(), Toast.LENGTH_SHORT);
//
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Toast.makeText(context, "Error" + t.getMessage(), Toast.LENGTH_SHORT);
//                        }
//                    });



                }
            });






        }
    }
}