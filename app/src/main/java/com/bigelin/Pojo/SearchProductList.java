package com.bigelin.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchProductList {
    private static final String TAG = "SearchProductListPojo ";

    private int product_id;
    private String product_category, product_title, product_status,product_image;
    private boolean productData;
    // private ArrayList<UserOtherImage> userOtherImageArrayList = new ArrayList<>();

    public SearchProductList(JSONObject response, boolean isLogin) {
        try {
            this.productData = response.getBoolean("productData");
            this.product_id = response.getInt("product_id");
            this.product_category = response.getString("product_category");
            this.product_image = response.getString("product_image");
            this.product_title = response.getString("product_title");
            this.product_status = response.getString("product_status");
            if (isLogin) {
                this.productData = response.getBoolean("productData");
                this.product_id = response.getInt("product_id");
                this.product_category = response.getString("product_category");
                this.product_image = response.getString("product_image");
                this.product_title = response.getString("product_title");
                this.product_status = response.getString("product_status");
            }

        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public SearchProductList() {

    }
    public boolean isProductData() {
        return productData;
    }
    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public static ArrayList<SearchProductList> getData(ArrayList<SearchProductList> ary) {
        ArrayList<SearchProductList> productList = new ArrayList<SearchProductList>();

        for (int i = 0; i < ary.size(); i++) {
            SearchProductList temp = new SearchProductList();
            temp.setProduct_id(ary.get(i).product_id);
            temp.setProduct_category(ary.get(i).product_category);
            temp.setProduct_image(ary.get(i).product_image);
            temp.setProduct_title(ary.get(i).product_title);
            temp.setProduct_status(ary.get(i).product_status);
            productList.add(temp);

        }
        return productList;
    }
}
