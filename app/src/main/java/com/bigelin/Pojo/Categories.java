package com.bigelin.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Categories {
    private static final String TAG = "CategoriesPojo ";

    private int category_id;
    private String category_title, category_image;

    public Categories(JSONObject response, boolean isLogin) {
        try {
            this.category_id = response.getInt("category_id");
            this.category_title = response.getString("category_title");
            this.category_image = response.getString("category_image");
            if (isLogin) {
                this.category_id = response.getInt("category_id");
                this.category_title = response.getString("category_title");
                this.category_image = response.getString("category_image");
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Categories() {

    }

    public static String getTAG() {
        return TAG;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public static ArrayList<Categories> getData(ArrayList<Categories> ary) {
        ArrayList<Categories> productList = new ArrayList<Categories>();
        for (int i = 0; i < ary.size(); i++) {
            Categories temp = new Categories();
            temp.setCategory_id(ary.get(i).category_id);
            temp.setCategory_title(ary.get(i).category_title);
            temp.setCategory_image(ary.get(i).category_image);
            productList.add(temp);
        }
        return productList;
    }
}
