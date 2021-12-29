package com.theatifwaheed.exampractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<String> names_data;
    ArrayList<String> ids_data;
    ArrayList<Bitmap> images_data;
    Context golbalContext;
    public RecyclerAdapter(ArrayList<String> names_data, ArrayList<String> id_data, ArrayList<Bitmap> images_data, Context golbalContext) {
        this.names_data = names_data;
        this.ids_data = id_data;
        this.images_data = images_data;
        this.golbalContext = golbalContext;
        Toast.makeText(golbalContext, "Recycler View Initiated", Toast.LENGTH_SHORT).show();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_data_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_rv_name.setText(names_data.get(position));
        holder.textView_rv_id.setText(ids_data.get(position));
        holder.imageView_rv_image.setImageBitmap(images_data.get(position));
    }

    @Override
    public int getItemCount() {
        return ids_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_rv_name;
        TextView textView_rv_id;
        ImageView imageView_rv_image;
        CardView cardView_rv_data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_rv_name= itemView.findViewById(R.id.tv_rv_name);
            textView_rv_id= itemView.findViewById(R.id.tv_rv_id);
            cardView_rv_data = itemView.findViewById(R.id.cv_rv_data);
            imageView_rv_image = itemView.findViewById(R.id.iv_rv_image);
        }
    }
}
