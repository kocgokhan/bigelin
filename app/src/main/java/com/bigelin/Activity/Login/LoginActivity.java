package com.bigelin.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.bigelin.Adapter.LoginRecyclerAdapter;
import com.bigelin.R;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private Button coupleLoginBtn, guestLoginBtn;
    private RecyclerView login_recyc;
    private LinearLayoutManager linearLayoutManager;
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// hide status bar
        super.onCreate(savedInstanceState);
        View bView = getWindow().getDecorView();// hide hardware buttons
        bView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);// hide hardware buttons
        try
        {
            this.getSupportActionBar().hide();// hide status bar
        }catch (NullPointerException e){}
        setContentView(R.layout.activity_login);

        login_recyc = (RecyclerView) findViewById(R.id.login_recyc);
        coupleLoginBtn = (Button) findViewById(R.id.coupleLoginBtn);
        guestLoginBtn = (Button) findViewById(R.id.guestLoginBtn);

        coupleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CoupleLoginActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });
        guestLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GuestLoginActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });

        // set a LinearLayoutManager with default horizontal orientation and false value for reverseLayout to show the items from start to end
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        login_recyc.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        LoginRecyclerAdapter customAdapter = new LoginRecyclerAdapter(LoginActivity.this,personImages);
        login_recyc.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
}