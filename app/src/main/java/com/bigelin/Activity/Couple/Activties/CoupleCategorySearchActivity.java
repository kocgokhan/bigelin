package com.bigelin.Activity.Couple.Activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigelin.Adapter.CoupleCategoryRecyclerAdapter;
import com.bigelin.Pojo.SearchProductList;
import com.bigelin.R;
import com.bigelin.Request.AqJSONObjectRequest;
import com.bigelin.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static com.bigelin.Util.StaticFields.BASE_URL;

public class CoupleCategorySearchActivity extends AppCompatActivity {

    private String TAG = "CoupleCategorySearchActivity";
    private String category_id,city_id,category_name,city_name;
    private TextView category_title,city_title;
    private RecyclerView category_all_recyc;
    private ImageView back_btn;
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));

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
        setContentView(R.layout.activity_couple_category_search);

        back_btn = (ImageView) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        category_title = (TextView) findViewById(R.id.category_title);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            category_id= null;
            city_id= null;
        } else {
            category_id = extras.getString("category_id");
            category_name = extras.getString("category_title");
            city_id = extras.getString("city_id");
            city_name = extras.getString("city_title");
            category_title.setText(category_name + " / " +city_name);
        }
        category_all_recyc = (RecyclerView) findViewById(R.id.category_all_recyc);


        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        category_all_recyc.setLayoutManager(layoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CoupleCategoryRecyclerAdapter customAdapter1 = new CoupleCategoryRecyclerAdapter(this,personImages);
        category_all_recyc.setAdapter(customAdapter1); // set the Adapter to RecyclerView

        //requestProductList(category_id);

    }
    /*private void requestProductList(String category_id) {
        ArrayList<SearchProductList> searchProductListArrayList = new ArrayList<>();
        JSONObject params = new JSONObject();
        try {
            params.put("category_id", category_id);
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);

                    JSONArray jsonArray = null;
                    try {
                        jsonArray = response.getJSONArray("aqArrayi");
                        JSONObject jsonObject;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);
                            SearchProductList notification = new SearchProductList(jsonObject, false);
                            searchProductListArrayList.add(notification);
                        }
                        if (searchProductListArrayList.get(0).isProductData()==false){

                            //setup();

                        }else{ drawCart(searchProductListArrayList);}

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

                }
            };
            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG,BASE_URL + "product_detail", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }*/

    /*public void drawCart(ArrayList<SearchProductList> list){

        CoupleCategoryAllRecyclerAdapter notificationAdapter = new CoupleCategoryAllRecyclerAdapter(this, SearchProductList.getData(list));
        category_all_recyc.setAdapter(notificationAdapter);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        category_all_recyc.setLayoutManager(layoutManager);

        Log.wtf("notification", String.valueOf(list));
    }*/
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