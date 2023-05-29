package com.example.ratatouille.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.viewHolder> {
    List<MealDto> myList = new ArrayList<>();
    Context context;

    InsertInterface insertInterface;
    public void setList(List<MealDto>myList){
        this.myList=myList;
        notifyDataSetChanged();
    }
    public IngredientAdapter (Context context,InsertInterface insertInterface){
        this.context=context;
        this.insertInterface=insertInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.avater_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.tvItemName.setText(myList.get(position).getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+myList.get(position).getStrIngredient()+".png")
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(holder.ivItem);
        holder.ivItem.setOnClickListener(v -> {
            insertInterface.onClickIngredient(myList.get(position).getStrIngredient());
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setList(ArrayList<MealDto> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemName;
        public ImageView ivItem;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName=itemView.findViewById(R.id.tvItemName);
            ivItem=itemView.findViewById(R.id.ivProfile);
        }
    }
}