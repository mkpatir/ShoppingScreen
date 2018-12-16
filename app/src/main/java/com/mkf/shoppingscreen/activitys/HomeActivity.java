package com.mkf.shoppingscreen.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mkf.shoppingscreen.*;
import com.mkf.shoppingscreen.extras.CustomToast;

public class HomeActivity extends AppCompatActivity {

    LinearLayout home_scan_layout;
    TextView home_start_text;
    Button home_enterbarcode;
    LinearLayout home_enterbarcode_layout;
    LinearLayout home_enterbarcodebutton_layout;
    EditText home_enterbarcode_text;
    final Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_scan_layout = (LinearLayout)findViewById(R.id.home_scan_layout);
        home_start_text = (TextView)findViewById(R.id.home_start_text);
        home_enterbarcode = (Button)findViewById(R.id.home_enterbarcode);
        home_enterbarcode_layout = (LinearLayout)findViewById(R.id.home_enterbarcode_layout);
        home_enterbarcodebutton_layout = (LinearLayout)findViewById(R.id.home_enterbarcodebutton_layout);
        home_enterbarcode_text = (EditText)findViewById(R.id.home_enterbarcode_text);
        Animation alpha_anim_1250ms = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha_anim_1250ms);
        home_start_text.startAnimation(alpha_anim_1250ms);
        alpha_anim_1250ms.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                home_scan_layout.setVisibility(View.VISIBLE);
                home_enterbarcodebutton_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        scanBarcode();
        edittextKeyListener();
    }

    //Barcode scan event
    private void scanBarcode(){
        home_scan_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    IntentIntegrator ıntentIntegrator = new IntentIntegrator(activity);
                    ıntentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    ıntentIntegrator.setPrompt("ScanBarcode");
                    ıntentIntegrator.setCameraId(0);
                    ıntentIntegrator.setBeepEnabled(false);
                    ıntentIntegrator.setBarcodeImageEnabled(false);
                    ıntentIntegrator.initiateScan();
                }
                catch (Exception ex){
                    CustomToast toast = new CustomToast(getApplicationContext());
                    toast.setCustomView(getLayoutInflater(),(ViewGroup)findViewById(R.id.custom_toast_layout),ex.getMessage());
                }
            }
        });
    }

    //Result of scanning and start OrderList Activity with result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
            if (result.getContents() != null){
                Intent intent = new Intent(HomeActivity.this,OrderListActivity.class);
                intent.putExtra("OrderID",result.getContents());
                startActivity(intent);
            }
        }
        catch (Exception ex){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Enter Barcode Button Event
    public void enterBarcodeButton(View view){
        home_enterbarcode.setVisibility(View.GONE);
        home_enterbarcode_layout.setVisibility(View.VISIBLE);
    }

    //Get OderList Button Event
    public void getOrderList(View view){
        try{
            if (home_enterbarcode_text.getText().toString().equals("")){
                CustomToast toast = new CustomToast(getApplicationContext());
                toast.setCustomView(getLayoutInflater(),(ViewGroup)findViewById(R.id.custom_toast_layout),"Enter a ordercode");
            }
            else{
                Intent intent = new Intent(HomeActivity.this,OrderListActivity.class);
                intent.putExtra("OrderID",home_enterbarcode_text.getText().toString());
                startActivity(intent);
            }
        }
        catch (Exception ex){

        }
    }

    //Keybord Key Listener Event
    public void edittextKeyListener(){
        try{
            home_enterbarcode_text.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN){
                        if (keyCode == KeyEvent.KEYCODE_ENTER){
                            if (home_enterbarcode_text.getText().toString().equals("")){
                                CustomToast toast = new CustomToast(getApplicationContext());
                                toast.setCustomView(getLayoutInflater(),(ViewGroup)findViewById(R.id.custom_toast_layout),"Enter a ordercode");
                            }
                            else{
                                Intent intent = new Intent(HomeActivity.this,OrderListActivity.class);
                                intent.putExtra("OrderID",home_enterbarcode_text.getText().toString());
                                startActivity(intent);
                            }
                        }
                    }
                    return true;
                }
            });
        }
        catch (Exception ex){

        }
    }
}
