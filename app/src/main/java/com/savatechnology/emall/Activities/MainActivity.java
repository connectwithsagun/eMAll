package com.savatechnology.emall.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.savatechnology.emall.Fragments.SettingsFragment;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final float END_SCALE = 0.85f;
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavView;
    private CoordinatorLayout contentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //   navigationView =findViewById(R.id.bottom_nav_view);

        //  bottomNavView = findViewById(R.id.bottom_nav_view);

//        navController.addOnDestinationChangedListener{ _, nd: NavDestination, _->
//            if(nd.id == R.id.contactsFragment || nd.id == R.id.profileFragment){
//                navView.visibility = View.VISIBLE
//            }else{
//                navView.visibility = View.GONE
//            }


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        initToolbar();

        initNavigation();
        getUserDetails();


        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

//        String s1 = sh.getString("email", "");
//
        Boolean b1 = sh.getBoolean("isLoggedIn", false);
//        Log.v("sdcds",b1.toString());
//        Toast.makeText(MainActivity.this, "Your Email Id is "+s1, Toast.LENGTH_LONG).show();
//
        if (b1) {

            navigationView.getMenu().findItem(R.id.nav_login).setEnabled(false);
            navigationView.getMenu().findItem(R.id.nav_register).setEnabled(false);

        }
//        else{
//            NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
//            navigation.getMenu().findItem(R.id.nav_login1).setEnabled(true);
//        }


    }


    //for getting user details
    private void getUserDetails() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String UserId = sh.getString("id", "");
        ApiService apiService = ApiUtil.getApiService();
        apiService.userDetailsGet(UserId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()) {
                    try {
                        String val = response.body().string();
                        JSONObject obj = new JSONObject(val);
                        String username = obj.getString("username");
                        String phone = obj.getString("phone");
                        String address = obj.getString("addres");
                        String gender = obj.getString("gender");
                        String image = obj.getString("image");

                        //Log.v("abc",address);

                        //  Toast.makeText(getActivity(), "" + username, Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor loginPreferences = sharedPreferences.edit();
                        loginPreferences.putString("username", username);
                        loginPreferences.putString("phone", phone);
                        loginPreferences.putString("address", address);
                        loginPreferences.putString("gender", gender);
                        loginPreferences.putString("image", image);
                        loginPreferences.apply();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


//                else{
//                    try {
//                        String val = response.errorBody().string();
//                        JSONObject obj= new JSONObject(val);
//                        String message = obj.getString("message");
//
//                        Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
//
//
//                    } catch (IOException | JSONException e) {
//                        e.printStackTrace();
//                    }
//                }


            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    private void initNavigation() {

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavView = findViewById(R.id.bottom_nav_view);
        contentView = findViewById(R.id.content_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
//               R.id.nav_order, R.id.nav_help,R.id.nav_settings, R.id.nav_dark_mode, R.id.nav_login,R.id.nav_register,
                R.id.nav_register, R.id.action_home, R.id.action_account, R.id.action_category, R.id.action_favourites)
//                    R.id.nav_tools, R.id.nav_share, R.id.nav_send,
//                    R.id.bottom_home, R.id.bottom_dashboard, R.id.bottom_notifications)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavView, navController);


        animateNavigationDrawer();

//hiding bottom navigation when user clicked on navigation drawer
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull @NotNull NavController controller, @NonNull @NotNull NavDestination destination, @Nullable @org.jetbrains.annotations.Nullable Bundle arguments) {

                if (destination.getId() == Integer.valueOf(R.id.nav_login) || destination.getId() == Integer.valueOf(R.id.nav_register)
                        || destination.getId() == Integer.valueOf(R.id.nav_dark_mode) || destination.getId() == Integer.valueOf(R.id.nav_settings)
                        || destination.getId() == Integer.valueOf(R.id.nav_order)
                        || destination.getId() == Integer.valueOf(R.id.nav_notification)) {

                    bottomNavView.setVisibility(View.GONE);
                } else {
                    bottomNavView.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    private void animateNavigationDrawer() {
//        drawerLayout.setScrimColor(getResources().getColor(R.color.text_brown));
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent i = new Intent(this, CartActivity.class);
                this.startActivity(i);
                return true;
            case R.id.action_settings:
                Intent i1 = new Intent(this, SettingsFragment.class);
                this.startActivity(i1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}