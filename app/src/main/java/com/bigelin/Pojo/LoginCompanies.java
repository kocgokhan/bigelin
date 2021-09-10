package com.bigelin.Pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginCompanies {
    private static final String TAG = "LoginCompaniesPojo ";
    private int company_id;
    private String company_title, company_img,city_title;

    public LoginCompanies(JSONObject response, boolean isLogin) {
        try {
            this.company_id    = response.getInt("company_id");
            this.company_title = response.getString("company_title");
            this.company_img            = response.getString("company_img");
            this.city_title            = response.getString("city_title");
            if (isLogin) {
                this.company_id    = response.getInt("company_id");
                this.company_title = response.getString("company_title");
                this.company_img            = response.getString("company_img");
                this.city_title            = response.getString("city_title");
            }
        } catch (JSONException e) {
            Log.wtf(TAG, "json parse catche dustu : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public LoginCompanies() {

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

    public String getCity_title() {
        return city_title;
    }

    public void setCity_title(String city_title) {
        this.city_title = city_title;
    }

    public String getCompany_img() {
        return company_img;
    }

    public void setCompany_img(String company_img) {
        this.company_img = company_img;
    }

    public static ArrayList<LoginCompanies> getData(ArrayList<LoginCompanies> ary) {
        ArrayList<LoginCompanies> productList = new ArrayList<LoginCompanies>();
        for (int i = 0; i < ary.size(); i++) {
            LoginCompanies temp = new LoginCompanies();
            temp.setCompany_id(ary.get(i).company_id);
            temp.setCompany_title(ary.get(i).company_title);
            temp.setCity_title(ary.get(i).city_title);
            temp.setCompany_img(ary.get(i).company_img);
            productList.add(temp);
        }
        return productList;
    }
}
