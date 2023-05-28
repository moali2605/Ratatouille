package com.example.ratatouille.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ratatouille.R;
import com.example.ratatouille.model.MealDto;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.veiwHolder> {
    List<MealDto> myList = new ArrayList<>();
    Context context;


    public void setList(List<MealDto>myList){
        this.myList=myList;
        notifyDataSetChanged();
    }
    public CountryAdapter (Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public veiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new veiwHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.avater_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull veiwHolder holder, int position) {

        holder.tvItemName.setText(myList.get(position).getStrArea());
        holder.ivItem.setImageAlpha(R.drawable.earth);
//        Glide.with(context).load("https://www.themealdb.com/images/category/"+myList.get(position).getStrCategory()+".png")
//                .apply(new RequestOptions().override(200, 200))
//                .placeholder(R.drawable.profilphoto)
//                .error(R.drawable.profilphoto).into(holder.ivItem);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setList(ArrayList<MealDto> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class veiwHolder extends RecyclerView.ViewHolder {

        public TextView tvItemName;
        public ImageView ivItem;
        public veiwHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName=itemView.findViewById(R.id.tvItemName);
            ivItem=itemView.findViewById(R.id.ivIngredient);
        }
    }
}