package com.bigelin.Activity.Couple.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bigelin.Adapter.CoupleCategoryRecyclerAdapter;
import com.bigelin.R;
import com.bigelin.Request.AqJSONObjectRequest;
import com.bigelin.Util.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

import static com.bigelin.Util.StaticFields.BASE_URL;

public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView search_btn;

    ArrayList<String> categoryItems = new ArrayList<>();
    ArrayList<String> cityItems = new ArrayList<>();
    SpinnerDialog spinnerDialogCategory;
    SpinnerDialog spinnerDialogCity;
    private String TAG = "CoupleMainAct";

    private RecyclerView category_recyc,vitrin_recyc;
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        requestCategorySearchItem();
        final TextView selectedItems = (TextView) view.findViewById(R.id.categoryText);
        final TextView cityText = (TextView) view.findViewById(R.id.cityText);


        category_recyc = (RecyclerView) view.findViewById(R.id.category_recyc);
        vitrin_recyc = (RecyclerView) view.findViewById(R.id.vitrin_recyc);

        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        category_recyc.setLayoutManager(layoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CoupleCategoryRecyclerAdapter customAdapter1 = new CoupleCategoryRecyclerAdapter(getContext(),personImages);
        category_recyc.setAdapter(customAdapter1); // set the Adapter to RecyclerView

        GridLayoutManager layoutManager2=new GridLayoutManager(getContext(),2);
        vitrin_recyc.setLayoutManager(layoutManager2);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CoupleCategoryRecyclerAdapter customAdapter2 = new CoupleCategoryRecyclerAdapter(getContext(),personImages);
        vitrin_recyc.setAdapter(customAdapter2); // set the Adapter to RecyclerView

        spinnerDialogCategory = new SpinnerDialog(getActivity(), categoryItems,
                "Aradığınız Kategoriyi Seçin");
        spinnerDialogCity = new SpinnerDialog(getActivity(), cityItems,
                "Bulunduğunuz Şehri Seçin");

        spinnerDialogCategory.setCancellable(true);
        spinnerDialogCategory.setShowKeyboard(false);
        spinnerDialogCity.setCancellable(true);
        spinnerDialogCity.setShowKeyboard(false);

        spinnerDialogCategory.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                selectedItems.setText(item);
            }
        });
        spinnerDialogCity.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                cityText.setText(item);
            }
        });

        view.findViewById(R.id.category_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialogCategory.showSpinerDialog();
            }
        });
        view.findViewById(R.id.city_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialogCity.showSpinerDialog();
            }
        });

        search_btn = (ImageView) view.findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), CoupleCategorySearchActivity.class);
                Bundle extras = new Bundle();
                if(selectedItems!=null && cityText!=null){
                    intent.putExtra("category_id",selectedItems.getText().toString());
                    intent.putExtra("category_title",selectedItems.getText().toString());
                    intent.putExtra("city_id",cityText.getText().toString());
                    intent.putExtra("city_title",cityText.getText().toString());

                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                }*/
            }
        });
        return view;
    }

    private void requestCategorySearchItem() {
        JSONObject params = new JSONObject();
        try {
            params.put("aqGokhan", 1);
            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.wtf(TAG, "onResponse : " + response);
                    categoryItems.add("Düğün Mekanları");
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
            AqJSONObjectRequest aqJSONObjectRequest = new AqJSONObjectRequest(TAG, BASE_URL + "get_category_bigelin", params, listener, errorListener);
            MyApplication.get().getRequestQueue().add(aqJSONObjectRequest);
        } catch (JSONException e) {
            Log.wtf(TAG, "request params catch e.getMessage() : " + e.getMessage());
            e.printStackTrace();
        }
    }
}