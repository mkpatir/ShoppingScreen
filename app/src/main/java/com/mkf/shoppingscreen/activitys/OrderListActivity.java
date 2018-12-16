package com.mkf.shoppingscreen.activitys;

import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.mkf.shoppingscreen.*;
import com.mkf.shoppingscreen.extras.Extra;
import com.mkf.shoppingscreen.extras.Products;
import com.mkf.shoppingscreen.extras.SectionPageAdapter;
import com.mkf.shoppingscreen.fragments.GridItemFragment;
import com.mkf.shoppingscreen.fragments.ListItemFragment;
import com.mkf.shoppingscreen.models.Ecommerce;
import com.mkf.shoppingscreen.models.Items;

import java.util.ArrayList;
import java.util.List;

/*
*  Lottie library using for animation
* */
public class OrderListActivity extends AppCompatActivity {


    ViewPager orderlist_viewpager;
    TabLayout orderlist_tablayout;
    LinearLayout orderlist_main_linlay;
    LottieAnimationView orderlist_loading_icon;
    RelativeLayout orderlist_loading_layout;
    LinearLayout orderlist_detail_layout;
    TextView orderlist_total;
    TextView orderlist_date;
    TextView orderlist_address;
    TextView orderlist_length;
    ImageView orderlist_detail_image;
    ImageView orderlist_opendetailimage;
    String OrderID;
    public static boolean isScrool;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            orderlist_main_linlay.setVisibility(View.VISIBLE);
            orderlist_loading_icon.cancelAnimation();
            List<Items> items = new ArrayList<>();
            Items item1 = new Items();
            item1.setName("Ülker Çikolata");
            item1.setCode("as54d5as5d");
            item1.setPrice(5.35);
            item1.setPhoto(String.valueOf(R.drawable.product1));
            items.add(item1);

            Items item2 = new Items();
            item2.setName("Eti Negro");
            item2.setCode("as4d5s4a5d");
            item2.setPrice(1.50);
            item2.setPhoto(String.valueOf(R.drawable.product2));
            items.add(item2);

            Items item3 = new Items();
            item3.setName("Coca Cola");
            item3.setCode("85a4s8da5s6");
            item3.setPrice(3.75);
            item3.setPhoto(String.valueOf(R.drawable.product3));
            items.add(item3);

            Items item4 = new Items();
            item4.setName("Domates");
            item4.setCode("8545sd");
            item4.setPrice(2.50);
            item4.setPhoto(String.valueOf(R.drawable.product4));
            items.add(item4);

            Items item5 = new Items();
            item5.setName("Damla Su");
            item5.setCode("88as7d5as");
            item5.setPrice(0.75);
            item5.setPhoto(String.valueOf(R.drawable.product5));
            items.add(item5);

            Ecommerce ecommerce = new Ecommerce();
            ecommerce.setTotal(String.valueOf(13.85));
            ecommerce.setShippingAddress("Mobilist");
            ecommerce.setItems(items);
            ecommerce.setDate("24/07/2018");
            Products.Items = ecommerce;
            orderlist_total.setText(Products.Items.getTotal());
            orderlist_date.setText(Products.Items.getDate());
            orderlist_address.setText(Products.Items.getShippingAddress());
            orderlist_length.setText("Total Product : " + String.valueOf(Products.Items.getItems().size()));
            setupPages();
            viewPagerPageChange();
            orderDetail();
            orderlist_loading_layout.setVisibility(View.GONE);
        }
    };

    Runnable pageChangeRunnable = new Runnable() {
        @Override
        public void run() {
            setupPages();
            isScrool = false;
            orderlist_viewpager.setCurrentItem(selectedPagePosition);
            orderlist_viewpager.setVisibility(View.VISIBLE);
        }
    };
    Handler handler = new Handler();
    int selectedPagePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        Intent intent = getIntent();
        OrderID = intent.getStringExtra("OrderID");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#d32f2f\">" +OrderID+" <font/>"));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backward_arrow);
        /*CustomToast toast = new CustomToast(getApplicationContext());
        toast.setCustomView(getLayoutInflater(),(ViewGroup)findViewById(R.id.custom_toast_layout),OrderID);*/
        orderlist_main_linlay = (LinearLayout)findViewById(R.id.orderlist_main_linlay);
        orderlist_viewpager = (ViewPager) findViewById(R.id.orderlist_viewpager);
        orderlist_tablayout = (TabLayout) findViewById(R.id.orderlist_tablayout);
        orderlist_loading_icon = (LottieAnimationView) findViewById(R.id.orderlist_loading_icon);
        orderlist_loading_layout = (RelativeLayout)findViewById(R.id.orderlist_loading_layout);
        orderlist_total = (TextView)findViewById(R.id.orderlist_total);
        orderlist_date = (TextView)findViewById(R.id.orderlist_date);
        orderlist_address = (TextView)findViewById(R.id.orderlist_address);
        orderlist_tablayout.setupWithViewPager(orderlist_viewpager);
        orderlist_length = (TextView)findViewById(R.id.orderlist_length);
        orderlist_detail_layout = (LinearLayout)findViewById(R.id.orderlist_detail_layout);
        orderlist_detail_image = (ImageView)findViewById(R.id.orderlist_detail_image);
        orderlist_opendetailimage = (ImageView)findViewById(R.id.orderlist_opendetailimage);
        orderlist_loading_icon.playAnimation();
        handler.postDelayed(runnable,3000);
    }

    //Fragments setup to viewpager
    private void setupPages(){
        SectionPageAdapter pageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new GridItemFragment(),"Table");
        pageAdapter.addFragment(new ListItemFragment(),"List");
        orderlist_viewpager.setAdapter(pageAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // ViewPager Page Change Listener
    private void viewPagerPageChange(){
        orderlist_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isScrool){
                    orderlist_viewpager.setVisibility(View.INVISIBLE);
                    selectedPagePosition = position;
                    handler.postDelayed(pageChangeRunnable,500);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //Order Detail Open Close
    private void orderDetail(){
        orderlist_detail_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderlist_opendetailimage.setVisibility(View.VISIBLE);
                orderlist_detail_layout.setVisibility(View.GONE);
            }
        });

        orderlist_opendetailimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderlist_opendetailimage.setVisibility(View.INVISIBLE);
                orderlist_detail_layout.setVisibility(View.VISIBLE);
            }
        });
    }
}
