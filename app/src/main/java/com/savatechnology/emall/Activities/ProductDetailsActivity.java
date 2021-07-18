package com.savatechnology.emall.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.savatechnology.emall.Adapters.AdapterHomeFeaturedProduct;
import com.savatechnology.emall.Adapters.AdapterHomeSupplier;
import com.savatechnology.emall.Fragments.LoginFragment;
import com.savatechnology.emall.JSONSchemas.FeaturedProduct;
import com.savatechnology.emall.JSONSchemas.Suppliers;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;


public class ProductDetailsActivity extends AppCompatActivity {

    private View parent_view;
    private TextView tv_qty;
    String pDesc, pName, pImage, supplierId, pId;
    Integer pPrize;
    ImageView imageProduct;
    AlertDialog.Builder builder;
    TextView ProductName, ProductPrize, ProductDesc,FavouriteIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        parent_view = findViewById(R.id.parent_view);


        initComponent();

    }

    @SuppressLint("WrongViewCast")
    private void initComponent() {
//        findViewById(R.id.image_1);
//        findViewById(R.id.image_2);
//        findViewById(R.id.image_3);
//        findViewById(R.id.image_4);
//        findViewById(R.id.image_5);


        ProductPrize = findViewById(R.id.tvProductPrize);
        ProductDesc = findViewById(R.id.tvProductDescription);
        ProductName = findViewById(R.id.tvProductName);
        imageProduct = (ImageView) findViewById(R.id.imgProduct);
        FavouriteIcon = findViewById(R.id.tvFavouriteIcon);

        FavouriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ProductDetailsActivity.this,SuppliersDetailActivity.class);
//                startActivity(intent);
                SharedPreferences sh = ProductDetailsActivity.this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String uId = sh.getString("id","");
                // Log.v("xyz",uId);
                ApiService apiService = ApiUtil.getApiService();
                apiService.createWishList(uId, pId).enqueue(new Callback<ResponseBody>() {
                    @Override


                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ProductDetailsActivity.this, "Product Added to Wishlist Successfully",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(ProductDetailsActivity.this, ProductDetailsActivity.class);
//                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(ProductDetailsActivity.this, ""+response.errorBody(),Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ProductDetailsActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });



        builder = new AlertDialog.Builder(this);

        pPrize = getIntent().getIntExtra("productPrize", 0);
        pName = getIntent().getStringExtra("productName");
        pDesc = getIntent().getStringExtra("productDescription");
        pImage = getIntent().getStringExtra("productImage");
        supplierId = getIntent().getStringExtra("supplierId");
        pId = getIntent().getStringExtra("productId");

        ProductPrize.setText("Rs. " + pPrize.toString());
        ProductName.setText(pName);
        ProductDesc.setText(pDesc);
//        Log.v("abc",pId);

        // loading product image
        Glide.with(this)
                .asBitmap()
                .load(pImage)
                // .apply(new RequestOptions().override(250,500))
                .into(imageProduct);


//        tv_qty = (TextView) findViewById(R.id.tvProductQuantity);
//        ((FloatingActionButton) findViewById(R.id.fab_qty_sub)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int qty = Integer.parseInt(tv_qty.getText().toString());
//                if (qty > 1) {
//                    qty--;
//                    tv_qty.setText(qty + "");
//
//                }
//            }
//        });
//
//        ((FloatingActionButton) findViewById(R.id.fab_qty_add)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int qty = Integer.parseInt(tv_qty.getText().toString());
//                if (qty < 10) {
//                    qty++;
//                    tv_qty.setText(qty + "");
//                }
//            }
//        });

        ((AppCompatButton) findViewById(R.id.bt_visit_store)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Snackbar.make(parent_view, "Store Visited", Snackbar.LENGTH_SHORT).show();
                //startActivity(new Intent(ProductDetailsActivity.this,SuppliersDetailActivity.class));


                ApiService apiService = ApiUtil.getApiService();
                apiService.getSupplierDetail(supplierId).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String val = response.body().string();
                                JSONObject obj = new JSONObject(val);
                                String name = obj.getString("name");
                                String location = obj.getString("location");
                                String phone = obj.getString("phone");
                                String image = obj.getString("image");

//                                Log.v("abc",name);
//                                Log.v("abc",location);
//                                Log.v("abc",phone);
//                                Log.v("abc",image);

                                Intent intent = new Intent(ProductDetailsActivity.this, SuppliersDetailActivity.class);
                                intent.putExtra("supplierName", name);
                                intent.putExtra("supplierLocation", location);
                                intent.putExtra("supplierPhone", phone);
                                intent.putExtra("supplierImage", image);
                                intent.putExtra("supplierId", supplierId);
                                startActivity(intent);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // startActivity(new Intent(ProductDetailsActivity.this,SuppliersDetailActivity.class));
                        } else {
                            Toast.makeText(ProductDetailsActivity.this, "" + response.errorBody(), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ProductDetailsActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
//        Log.v("abc",pId);
//        Log.v("xyz",supplierId);
        SharedPreferences sh = ProductDetailsActivity.this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Boolean b = sh.getBoolean("isLoggedIn", false);

              //  Log.v("xyz",uId);

        ((AppCompatButton) findViewById(R.id.bt_add_to_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!b) {
                    Toast.makeText(ProductDetailsActivity.this, "You are not logged in for this action!!", Toast.LENGTH_SHORT).show();
                } else
                    {
                    addCart();
                }
              }

        });
    }

    private void addCart() {
        SharedPreferences sh = ProductDetailsActivity.this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String uId = sh.getString("id","");
       // Log.v("xyz",uId);
        ApiService apiService = ApiUtil.getApiService();
        apiService.createCart(uId, pId).enqueue(new Callback<ResponseBody>() {
            @Override


            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProductDetailsActivity.this, "Product Added to Cart Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ProductDetailsActivity.this, ""+response.errorBody(),Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}