package com.bigelin.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryList {
    private static final String TAG = "SearchProductListPojo ";

    private int category_id;
    private String category_title, category_image;
    // private ArrayList<UserOtherImage> userOtherImageArrayList = new ArrayList<>();

    public CategoryList(JSONObject response, boolean isLogin) {
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


    public CategoryList() {

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



    public static ArrayList<CategoryList> getData(ArrayList<CategoryList> ary) {
        ArrayList<CategoryList> productList = new ArrayList<CategoryList>();

        for (int i = 0; i < ary.size(); i++) {
            CategoryList temp = new CategoryList();
            temp.setCategory_id(ary.get(i).category_id);
            temp.setCategory_title(ary.get(i).category_title);
            productList.add(temp);

        }
        return productList;
    }
}
