package com.savatechnology.emall.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.savatechnology.emall.Adapters.AdapterHomeFeaturedProduct;
import com.savatechnology.emall.Adapters.AdapterHomeNewArrivalsProduct;
import com.savatechnology.emall.Adapters.AdapterSupplierProduct;
import com.savatechnology.emall.Adapters.AdapterSuppliers;
import com.savatechnology.emall.JSONSchemas.FeaturedProduct;
import com.savatechnology.emall.JSONSchemas.NewArrivalProduct;
import com.savatechnology.emall.JSONSchemas.SupplierProduct;
import com.savatechnology.emall.Models.SupplierProductList;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuppliersHomePageTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuppliersHomePageTabFragment extends Fragment {


    RecyclerView recyclerView;
    List<SupplierProduct> lists;
    AdapterSupplierProduct adapter;
    Context mContext;
    GridLayoutManager layoutManager;
    private View view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SuppliersHomePageTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuppliersHomePageTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuppliersHomePageTabFragment newInstance(String param1, String param2) {
        SuppliersHomePageTabFragment fragment = new SuppliersHomePageTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      initData();
     //   Log.v("SupplierHomePage","sthgdtyhn");
        view = inflater.inflate(R.layout.fragment_suppliers_home_page_tab, container, false);
        initRecycleView(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initRecycleView(View view) {

//        recyclerView = view.findViewById(R.id.recyclerView);
//        layoutManager=new GridLayoutManager(mContext,2);
//        //layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new AdapterSupplierProduct(lists,mContext);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("supplierId", "");
//        Log.v("SupplierHomePage", String.valueOf(s1));
        ApiService apiService = ApiUtil.getApiService();
        apiService.getSupplierProduct(s1).enqueue(new Callback<List<SupplierProduct>>() {
            @Override
            public void onResponse(Call<List<SupplierProduct>> call, Response<List<SupplierProduct>> response) {
//                try {
//                    Log.v("SupplierHomePage",response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (response.isSuccessful()) {
                    List<SupplierProduct> featuredProducts = response.body();

                    recyclerView = view.findViewById(R.id.recyclerView);
                    layoutManager = new GridLayoutManager(mContext, 2);
                    //layoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new AdapterSupplierProduct(featuredProducts,mContext);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }else{
                    Toast.makeText(mContext, "Ërror while fetching products"+response.errorBody(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<SupplierProduct>> call, Throwable t) {
                Toast.makeText(mContext, "Ërror while fetching products", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initData() {

        lists = new ArrayList<>();
//
//        lists.add(new SupplierProductList(R.drawable.laptop,"Gaming Laptop","25"));
//        lists.add(new SupplierProductList(R.drawable.laptop,"Gaming Laptop","3"));
//        lists.add(new SupplierProductList(R.drawable.laptop,"Gaming Laptop","2"));
//        lists.add(new SupplierProductList(R.drawable.laptop,"Gaming Laptop","0"));


    }
}


