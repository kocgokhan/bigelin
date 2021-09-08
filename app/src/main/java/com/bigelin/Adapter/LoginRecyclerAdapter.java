package com.bigelin.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bigelin.R;

import java.util.ArrayList;

public class LoginRecyclerAdapter extends RecyclerView.Adapter<LoginRecyclerAdapter.MyViewHolder> {

    ArrayList<Integer> personImages;
    Context context;

    public LoginRecyclerAdapter(Context context, ArrayList<Integer> personImages) {
        this.context = context;
        this.personImages = personImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.login_recy, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, personImages.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return personImages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            image = (ImageView) itemView.findViewById(R.id.product_image);

        }
    }
}