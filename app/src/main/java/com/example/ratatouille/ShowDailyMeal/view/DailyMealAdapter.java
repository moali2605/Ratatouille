package com.example.ratatouille.ShowDailyMeal.view;

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

import java.util.ArrayList;
import java.util.List;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.viewHolder> {
    List<MealDto> myList = new ArrayList<>();
    Context context;
    DailyMealOnClick dailyMealOnClick;

    public DailyMealAdapter(Context context,DailyMealOnClick dailyMealOnClick) {
        this.context = context;
        this.dailyMealOnClick=dailyMealOnClick;
    }

    public void setList(List<MealDto> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.tvDaily.setText(myList.get(position).getStrMeal());
        Glide.with(context).load(myList.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(350, 200))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(holder.ivDaily);
        holder.cvDaily.setOnClickListener(v -> {
            dailyMealOnClick.onClickShowMeal(myList.get(position));
        });
        holder.btnDeleteFromDaily.setOnClickListener(v -> {
            dailyMealOnClick.onClickDeleteDay(myList.get(position).getIdMeal());
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

        ImageView ivDaily;
        TextView tvDaily;
        Button btnDeleteFromDaily;
        CardView cvDaily;
        public viewHolder(@NonNull View v) {
            super(v);
            ivDaily=v.findViewById(R.id.ivDaily);
            tvDaily=v.findViewById(R.id.tvDaily);
            btnDeleteFromDaily=v.findViewById(R.id.btnDeleteFromDaily);
            cvDaily=v.findViewById(R.id.cvDaily);

        }
    }
}