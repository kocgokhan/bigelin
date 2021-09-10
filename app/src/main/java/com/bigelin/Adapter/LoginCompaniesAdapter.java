package com.bigelin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bigelin.Pojo.LoginCompanies;
import com.bigelin.R;
import com.bigelin.Util.MyApplication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoginCompaniesAdapter extends RecyclerView.Adapter<LoginCompaniesAdapter.MyViewHolder>{

    ArrayList<LoginCompanies> mProductList;
    LayoutInflater inflater;

    public LoginCompaniesAdapter(Context context, ArrayList<LoginCompanies> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.login_recy, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LoginCompanies selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView company_id, company_title,city_title;
        ImageView company_image;
        Button inspect_btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            company_title = (TextView) itemView.findViewById(R.id.company_title);
            company_image = (ImageView) itemView.findViewById(R.id.company_image);
            inspect_btn = (Button) itemView.findViewById(R.id.inspect_btn);
            city_title = (TextView) itemView.findViewById(R.id.city_title);

            inspect_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailOfCategory(v.getContext());
                }
            });
        }

        public void setData(LoginCompanies selectedProduct, int position) {
            this.company_title.setText(selectedProduct.getCompany_title());
            this.city_title.setText(selectedProduct.getCity_title());
            String category_photo = selectedProduct.getCompany_img();
            Picasso.get().load(category_photo).into(this.company_image);
        }

        public void detailOfCategory(Context context){
           /* Intent i = new Intent(context.getApplicationContext(), LoginCompaniesCompanyActivity.class);
            String strName = null;
            i.putExtra("category_id", mProductList.get(getAdapterPosition()).getCategory_id());
            i.putExtra("category_title", mProductList.get(getAdapterPosition()).getCategory_title());
            context.startActivity(i);
            MyApplication.get().getRequestQueue().getCache().clear();*/
        }



    }
}