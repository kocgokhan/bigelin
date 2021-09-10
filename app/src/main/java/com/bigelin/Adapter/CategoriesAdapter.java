package com.bigelin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bigelin.Activity.Couple.Activties.CategoriesCompanyActivity;
import com.bigelin.Pojo.Categories;
import com.bigelin.R;
import com.bigelin.Util.MyApplication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{

    ArrayList<Categories> mProductList;
    LayoutInflater inflater;

    public CategoriesAdapter(Context context, ArrayList<Categories> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_recy, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Categories selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView category_id, category_title;
        ImageView category_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            category_title = (TextView) itemView.findViewById(R.id.category_title);
            category_image = (ImageView) itemView.findViewById(R.id.category_image);

            category_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailOfCategory(v.getContext());
                }
            });
        }

        public void setData(Categories selectedProduct, int position) {
            this.category_title.setText(selectedProduct.getCategory_title());
            String category_photo = selectedProduct.getImg();
            Picasso.get().load(category_photo).into(this.category_image);
        }

        public void detailOfCategory(Context context){
            Intent i = new Intent(context.getApplicationContext(), CategoriesCompanyActivity.class);
            String strName = null;
            i.putExtra("category_id", mProductList.get(getAdapterPosition()).getCategory_id());
            i.putExtra("category_title", mProductList.get(getAdapterPosition()).getCategory_title());
            context.startActivity(i);
            MyApplication.get().getRequestQueue().getCache().clear();
        }



    }
}