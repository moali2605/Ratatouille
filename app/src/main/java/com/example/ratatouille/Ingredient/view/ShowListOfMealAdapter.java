package com.example.ratatouille.Ingredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouille.R;
import com.example.ratatouille.model.MealDto;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ShowListOfMealAdapter extends RecyclerView.Adapter<ShowListOfMealAdapter.viewHolder> {
    List<MealDto> myList = new ArrayList<>();
    Context context;
    OnClickShowMealInterface onClickShowMealInterface;

    public void setList(List<MealDto> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public ShowListOfMealAdapter(Context context,OnClickShowMealInterface  onClickShowMealInterface) {
        this.context = context;
        this.onClickShowMealInterface=onClickShowMealInterface;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.tvListOfMealName.setText(myList.get(position).getStrMeal());
        Glide.with(context).load(myList.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(400, 250))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(holder.ivListByMeal);
        holder.cvItem.setOnClickListener(v -> {
            onClickShowMealInterface.onClick(myList.get(position));
        });

        holder.btnAddToFavItem.setOnClickListener(v -> {
                onClickShowMealInterface.onClickFav(myList.get(position));
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

        ImageView ivListByMeal;
        TextView tvListOfMealName;
        Button btnAddToFavItem;
        CardView cvItem;

        public viewHolder(@NonNull View v) {
            super(v);
            ivListByMeal = v.findViewById(R.id.ivDailyInspiration);
            tvListOfMealName = v.findViewById(R.id.tvDIName);
            btnAddToFavItem = v.findViewById(R.id.btnAboutApp);
            cvItem=v.findViewById(R.id.cvItem);
        }
    }
}