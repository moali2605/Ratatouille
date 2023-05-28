package com.example.ratatouille.FavouriteMeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouille.R;
import com.example.ratatouille.model.MealDto;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.viewHolder> {
    List<MealDto> myList = new ArrayList<>();
    DeleteInterface deleteInterface;
    MealDto meal;
    Context context;

    public FavAdapter(Context context,DeleteInterface deleteInterface){
        this.context=context;
        this.deleteInterface=deleteInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tvFavName.setText(myList.get(position).getStrMeal());
        Glide.with(context).load(myList.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(400, 250))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(holder.ivFavMeal);
        holder.btnDelete.setOnClickListener(v -> {
            meal=myList.get(position);
            deleteInterface.onClick(meal);
        });
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (myList != null)
            size = myList.size();
        return size;
    }

    public void setList(ArrayList<MealDto> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public ImageView ivFavMeal;
        TextView tvFavName;
        Button btnAddToDaily,btnDelete;

        public viewHolder(@NonNull View v) {
            super(v);
            ivFavMeal=v.findViewById(R.id.ivDailyInspiration);
            tvFavName=v.findViewById(R.id.tvDIName);
            btnAddToDaily=v.findViewById(R.id.btnAddToFavItem);
            btnDelete=v.findViewById(R.id.btnDelete);


        }
    }
}