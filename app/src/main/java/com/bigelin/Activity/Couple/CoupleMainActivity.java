package com.bigelin.Activity.Couple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigelin.Activity.Couple.Fragment.CampaingFragment;
import com.bigelin.Activity.Couple.Fragment.MainFragment;
import com.bigelin.Activity.Login.LoginActivity;
import com.bigelin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class CoupleMainActivity extends AppCompatActivity {
    private String TAG = "CoupleMainAct";
    private SweetSheet mSweetSheet;
    private RelativeLayout sheetRL;
    private String loginResponse;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new MainFragment();
                    break;
                case R.id.navigation_category:
                    fragment = new CampaingFragment();
                    break;
                case R.id.navigation_campaign:
                    fragment = new CampaingFragment();
                    break;
                case R.id.navigation_profile:
                    if (mSweetSheet.isShow()) {
                        mSweetSheet.dismiss();
                    }
                    mSweetSheet.toggle();
                    break;
            }
            return loadFragment(fragment);
        }
    };
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.navHostFragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// hide status bar

        View bView = getWindow().getDecorView();// hide hardware buttons
        bView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);// hide hardware buttons
        try
        {
            this.getSupportActionBar().hide();// hide status bar
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_couple_main);
        loadFragment(new MainFragment());
        BottomNavigationView navView = findViewById(R.id.bottomNav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setItemIconTintList(null);
        ButterKnife.bind(this);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#EAF3F3"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        sheetRL = (RelativeLayout) findViewById(R.id.sheetRL);
        setupRecyclerView();

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            loginResponse= null;
        } else {
            loginResponse= extras.getString("loginResponse");
        }
    }

    private void setupRecyclerView() {

        final ArrayList<MenuEntity> list = new ArrayList<>();

        if(loginResponse!=null) {
            MenuEntity menuEntity = new MenuEntity();
            //menuEntity.iconId = R.drawable.crying;
            menuEntity.title = "Ajandam";
            MenuEntity menuEntity1 = new MenuEntity();
            menuEntity1.title = "Davetli Listesi";
            MenuEntity menuEntity2 = new MenuEntity();
            menuEntity2.title = "Oturma Düzeni";
            MenuEntity menuEntity3 = new MenuEntity();
            menuEntity3.title = "Yapılacaklar Listem";
            MenuEntity menuEntity4 = new MenuEntity();
            menuEntity4.title = "Çıkış Yap";

            list.add(menuEntity);
            list.add(menuEntity1);
            list.add(menuEntity2);
            list.add(menuEntity3);
            list.add(menuEntity4);
        }else{
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.title = "Giriş Yap";

            list.add(menuEntity);
        }

        mSweetSheet = new SweetSheet(sheetRL);
        mSweetSheet.setMenuList(list);
        mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
        mSweetSheet.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity) {

                if(loginResponse!=null) {
                    if (position==0){
                        Toast.makeText(CoupleMainActivity.this, " btn1 " + position, Toast.LENGTH_SHORT).show();
                    }else if(position==1){
                        Toast.makeText(CoupleMainActivity.this, " btn2 " + position, Toast.LENGTH_SHORT).show();
                    }else if(position==2){
                        Toast.makeText(CoupleMainActivity.this, " btn3 " + position, Toast.LENGTH_SHORT).show();
                    }else if(position==3){
                        Toast.makeText(CoupleMainActivity.this, " btn4 " + position, Toast.LENGTH_SHORT).show();
                    }else if(position==4){
                        Toast.makeText(CoupleMainActivity.this, " btn5 " + position, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Intent match_intent = new Intent(CoupleMainActivity.this, LoginActivity.class);
                    startActivity(match_intent);
                }
                return false;
            }
        });
    }
}