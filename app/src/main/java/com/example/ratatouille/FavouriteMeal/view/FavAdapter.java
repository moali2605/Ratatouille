package com.example.ratatouille.FavouriteMeal.view;

import android.content.Context;

import android.content.Intent;
import android.provider.CalendarContract;
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
        meal=myList.get(position);
        holder.tvFavName.setText(myList.get(position).getStrMeal());
        Glide.with(context).load(myList.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(400, 250))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(holder.ivFavMeal);
        holder.btnDelete.setOnClickListener(v -> {
            deleteInterface.onClick(myList.get(position));
        });
        holder.cvFavMeal.setOnClickListener(v -> {

            deleteInterface.onClickShowMeal(myList.get(position));
        });
        holder.btnAddToDaily.setOnClickListener(v -> {

            deleteInterface.onClickAddToDaily(myList.get(position).getIdMeal());
        });
        holder.btnAddToCal.setOnClickListener(v -> {
            addToMobileCalender();
        });
    }
    private void addToMobileCalender(){
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, meal.getStrMeal())
                .putExtra(CalendarContract.Events.DESCRIPTION, "Enjoy a delicious " + meal.getStrMeal() + " for dinner!")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Home")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + (60 * 60 * 1000)); // End time is 1 hour after start time
        context.startActivity(intent);
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
        Button btnAddToDaily,btnDelete,btnAddToCal;
        CardView cvFavMeal;
        public viewHolder(@NonNull View v) {
            super(v);
            ivFavMeal=v.findViewById(R.id.ivFav);
            tvFavName=v.findViewById(R.id.tvFav);
            btnAddToDaily=v.findViewById(R.id.btnAddToDaily);
            btnDelete=v.findViewById(R.id.btnDelete);
            cvFavMeal=v.findViewById(R.id.cvFav);
            btnAddToCal=v.findViewById(R.id.btnAddToCal);

        }
    }
}