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

import com.bigelin.Activity.Couple.Activties.CompanyDetailActivity;
import com.bigelin.Pojo.CompanyList;
import com.bigelin.R;
import com.bigelin.Util.MyApplication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.MyViewHolder>{

    ArrayList<CompanyList> mProductList;
    LayoutInflater inflater;

    public CompanyListAdapter(Context context, ArrayList<CompanyList> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.company_list_recy, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CompanyList selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView company_desc, company_title, city_title;
        ImageView company_image;
        Button inspect_btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            company_title = (TextView) itemView.findViewById(R.id.company_title);
            company_desc = (TextView) itemView.findViewById(R.id.company_desc);
            city_title = (TextView) itemView.findViewById(R.id.city_title);
            company_image = (ImageView) itemView.findViewById(R.id.company_image);
            inspect_btn = (Button) itemView.findViewById(R.id.inspect_btn);

            inspect_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailOfCompany(v.getContext());
                }
            });

        }

        public void setData(CompanyList selectedProduct, int position) {
            this.company_title.setText(selectedProduct.getCompany_title());
            this.company_desc.setText(selectedProduct.getCompany_desc());
            this.city_title.setText(selectedProduct.getCity_title());
            String category_photo = selectedProduct.getCompany_img();
            Picasso.get().load(category_photo).into(this.company_image);
        }

        public void detailOfCompany(Context context){
            Intent i = new Intent(context.getApplicationContext(), CompanyDetailActivity.class);
            String strName = null;
            i.putExtra("company_id", mProductList.get(getAdapterPosition()).getCompany_id());
            context.startActivity(i);
            MyApplication.get().getRequestQueue().getCache().clear();
        }



    }
}