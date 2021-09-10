package com.bigelin.Activity.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.bigelin.Adapter.LoginCompaniesAdapter;
import com.bigelin.Pojo.LoginCompanies;
import com.bigelin.R;
import com.bigelin.Request.AqJSONObjectRequest;
import com.bigelin.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.bigelin.Util.StaticFields.BASE_URL;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginAct";
    private Button coupleLoginBtn, guestLoginBtn;
    private RecyclerView login_recyc;
    private LinearLayoutManager linearLayoutManager;
    //ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
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
        companyRecyclerRequest();


    }

    private void companyRecyclerRequest(){
        ArrayList<LoginCompanies> loginCompaniesArrayList = new ArrayList<>();
        JSONObject params = new JSONObject();
        try {
            params.put("aqGokhan", 1);
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = response.getJSONArray("data");
                        JSONObject jsonObject;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            LoginCompanies loginCompanies = new LoginCompanies(jsonObject, false);
                            loginCompaniesArrayList.add(loginCompanies);
                        }
                        drawCart(loginCompaniesArrayList);
                        MyApplication.get().getRequestQueue().getCache().clear();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "onErrorResponse : " + error);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null) {
                        Log.e("Volley", "Error. HTTP Status Code:"+networkResponse.statusCode);
                    }
                    if (error instanceof TimeoutError) {
                        Log.e("Volley", "TimeoutError");
                    }else if(error instanceof NoConnectionError){
                        Log.e("Volley", "NoConnectionError");
                    } else if (error instanceof AuthFailureError) {
                        Log.e("Volley", "AuthFailureError");
                    } else if (error instanceof ServerError) {
                        Log.e("Volley", "ServerError");
                    } else if (error instanceof NetworkError) {
                        Log.e("Volley", "NetworkError");
                    } else if (error instanceof ParseError) {
                        Log.e("Volley", "ParseError");
                    }
                    Log.d("Maps:", " Error: " + error.getMessage());
                }
            };
            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG, BASE_URL + "get_login_companies_bigelin", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void drawCart(ArrayList<LoginCompanies> list){

        // set a LinearLayoutManager with default horizontal orientation and false value for reverseLayout to show the items from start to end
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        login_recyc.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        LoginCompaniesAdapter customAdapter = new LoginCompaniesAdapter(LoginActivity.this,LoginCompanies.getData(list));
        login_recyc.setAdapter(customAdapter); // set the Adapter to RecyclerView


        Log.wtf("categories", String.valueOf(list));

    }
}