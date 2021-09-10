package com.bigelin.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyList {
    private static final String TAG = "CategoriesPojo ";
    private int company_id;
    private String company_title, company_img,company_desc,city_title,company_rating;

    public CompanyList(JSONObject response, boolean isLogin) {
        try {
            this.company_id    = response.getInt("company_id");
            this.company_title = response.getString("company_title");
            this.company_img            = response.getString("company_img");
            this.company_desc            = response.getString("company_desc");
            this.city_title            = response.getString("city_title");
            this.company_rating            = response.getString("company_rating");
            if (isLogin) {
                this.company_id    = response.getInt("company_id");
                this.company_title = response.getString("company_title");
                this.company_img            = response.getString("company_img");
                this.company_desc            = response.getString("company_desc");
                this.city_title            = response.getString("city_title");
                this.company_rating            = response.getString("company_rating");
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public CompanyList() {

    }

    public static String getTAG() {
        return TAG;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_title() {
        return company_title;
    }

    public void setCompany_title(String company_title) {
        this.company_title = company_title;
    }

    public String getCompany_img() {
        return company_img;
    }

    public void setCompany_img(String company_img) {
        this.company_img = company_img;
    }

    public String getCompany_desc() {
        return company_desc;
    }

    public void setCompany_desc(String company_desc) {
        this.company_desc = company_desc;
    }

    public String getCity_title() {
        return city_title;
    }

    public void setCity_title(String city_title) {
        this.city_title = city_title;
    }

    public String getCompany_rating() {
        return company_rating;
    }

    public void setCompany_rating(String company_rating) {
        this.company_rating = company_rating;
    }

    public static ArrayList<CompanyList> getData(ArrayList<CompanyList> ary) {
        ArrayList<CompanyList> productList = new ArrayList<CompanyList>();
        for (int i = 0; i < ary.size(); i++) {
            CompanyList temp = new CompanyList();
            temp.setCompany_id(ary.get(i).company_id);
            temp.setCompany_title(ary.get(i).company_title);
            temp.setCompany_img(ary.get(i).company_img);
            temp.setCity_title(ary.get(i).city_title);
            temp.setCompany_desc(ary.get(i).company_desc);
            temp.setCompany_rating(ary.get(i).company_rating);
            productList.add(temp);
        }
        return productList;
    }
}
