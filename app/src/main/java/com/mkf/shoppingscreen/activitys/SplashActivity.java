package com.mkf.shoppingscreen.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkf.shoppingscreen.*;

/*  First; left_anim start - finish with listener
    if Android version >= Android Marshmallow
        Second; animated vector start - finish with listener
    else
        Second; rotate_anim with listener
    Third; right_anim start - finish with listener
* */
public class SplashActivity extends AppCompatActivity {

    ImageView splash_icon;
    TextView splash_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_icon = (ImageView)findViewById(R.id.splash_icon);
        splash_text = (TextView)findViewById(R.id.splash_text);
        final Activity activity = this;
        Animation left_anim  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left_start_anim);
        final Animation right_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right_end_anim);
        Animation alpha_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha_anim);
        final Animation rotate_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anim);
        splash_text.startAnimation(alpha_anim);
        splash_icon.startAnimation(left_anim);
        final AnimatedVectorDrawable drawable = (AnimatedVectorDrawable)splash_icon.getDrawable();
        left_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    drawable.start();
                }
                else{
                    splash_icon.startAnimation(rotate_anim);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                @Override
                public void onAnimationEnd(Drawable drawable) {
                    super.onAnimationEnd(drawable);
                    splash_icon.startAnimation(right_anim);
                }
            });
        }
        rotate_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                splash_icon.setImageResource(R.drawable.app_icon);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash_icon.startAnimation(right_anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        right_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash_icon.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
