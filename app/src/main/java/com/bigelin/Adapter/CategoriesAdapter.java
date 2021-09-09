package com.bigelin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bigelin.Pojo.Categories;
import com.bigelin.R;
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


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView category_id, category_title;
        ImageView category_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            category_title = (TextView) itemView.findViewById(R.id.category_title);
            category_image = (ImageView) itemView.findViewById(R.id.category_image);

        }

        public void setData(Categories selectedProduct, int position) {

            String category_photo = selectedProduct.getCategory_image();

            this.category_title.setText(selectedProduct.getCategory_title());
            Picasso.get().load(category_photo).into(this.category_image);

        }


        @Override
        public void onClick(View v) {


        }


    }
}