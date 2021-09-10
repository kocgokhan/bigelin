package com.bigelin.Activity.Couple.Activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.bigelin.Activity.Couple.CoupleMainActivity;
import com.bigelin.Activity.Login.CoupleLoginActivity;
import com.bigelin.Activity.Login.GuestLoginActivity;
import com.bigelin.Activity.Login.LoginActivity;
import com.bigelin.Adapter.CategoriesAdapter;
import com.bigelin.Adapter.CompanyListAdapter;
import com.bigelin.Adapter.LoginCompaniesAdapter;
import com.bigelin.Pojo.Categories;
import com.bigelin.Pojo.CompanyList;
import com.bigelin.Pojo.LoginCompanies;
import com.bigelin.R;
import com.bigelin.Request.AqJSONObjectRequest;
import com.bigelin.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.bigelin.Util.StaticFields.BASE_URL;


public class CategoriesCompanyActivity extends AppCompatActivity {
    private ImageView back_btn;
    private String category_title,searched_text;
    private int category_id;
    private TextView title_category;
    private String TAG = "CategoriesCompanyAct";
    private ImageButton searchbtn,filter_btn;
    private EditText search_input;
    private RecyclerView company_recyc;

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
        setContentView(R.layout.activity_categories_company);
        title_category = (TextView) findViewById(R.id.category_title) ;
        company_recyc = (RecyclerView) findViewById(R.id.company_list_recy);

        filter_btn = (ImageButton) findViewById(R.id.filter_btn);
        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goFilter();
            }
        });

        back_btn = (ImageView) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        /*search_input = (EditText) findViewById(R.id.search_input);
        search_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i(TAG,"Enter pressed");
                }
                return false;
            }
        });
        searchbtn = (ImageButton) findViewById(R.id.searchbtn);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searched_text = search_input.getText().toString().trim();
                Log.wtf(TAG,searched_text);

            }
        });*/

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            category_title= null;
        } else {
            category_title= extras.getString("category_title");
            category_id= extras.getInt("category_id");
            title_category.setText(category_title);
        }



        companyRecyclerRequest();
    }
    private void companyRecyclerRequest(){
        ArrayList<CompanyList> companyListArrayList = new ArrayList<>();
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
                            CompanyList companyList = new CompanyList(jsonObject, false);
                            companyListArrayList.add(companyList);
                        }
                        drawCart(companyListArrayList);
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
            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG, BASE_URL + "get_companies_list_bigelin", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void drawCart(ArrayList<CompanyList> list){

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        company_recyc.setLayoutManager(layoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CompanyListAdapter categoriesAdapter = new CompanyListAdapter(CategoriesCompanyActivity.this, CompanyList.getData(list));
        company_recyc.setAdapter(categoriesAdapter); // set the Adapter to RecyclerView


        Log.wtf("categories", String.valueOf(list));

    }

    public void goFilter(){
        Intent intent = new Intent(CategoriesCompanyActivity.this, CompaniesFilterActivity.class);
        CategoriesCompanyActivity.this.startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}