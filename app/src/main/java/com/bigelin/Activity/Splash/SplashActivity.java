package com.bigelin.Activity.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bigelin.Activity.Couple.CoupleMainActivity;
import com.bigelin.R;

public class SplashActivity extends AppCompatActivity {
    private ConstraintLayout mPv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// hide status bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View bView = getWindow().getDecorView();// hide hardware buttons
        bView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);// hide hardware buttons
        try
        {
            this.getSupportActionBar().hide();// hide status bar
        }
        catch (NullPointerException e){}
        mPv1 = (ConstraintLayout) findViewById(R.id.splash);
        mPv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, CoupleMainActivity.class);
                SplashActivity.this.startActivity(intent);
                finish();
            }
        }, 5000);
    }
}