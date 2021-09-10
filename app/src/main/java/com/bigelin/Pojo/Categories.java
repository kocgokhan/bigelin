package com.bigelin.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Categories {
    private static final String TAG = "CategoriesPojo ";
    private int category_id;
    private String category_title, img;

    public Categories(JSONObject response, boolean isLogin) {
        try {
            this.category_id    = response.getInt("category_id");
            this.category_title = response.getString("category_title");
            this.img            = response.getString("img");
            if (isLogin) {
                this.category_id    = response.getInt("category_id");
                this.category_title = response.getString("category_title");
                this.img            = response.getString("img");
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static ArrayList<Categories> getData(ArrayList<Categories> ary) {
        ArrayList<Categories> productList = new ArrayList<Categories>();
        for (int i = 0; i < ary.size(); i++) {
            Categories temp = new Categories();
            temp.setCategory_id(ary.get(i).category_id);
            temp.setCategory_title(ary.get(i).category_title);
            temp.setImg(ary.get(i).img);
            productList.add(temp);
        }
        return productList;
    }
}
