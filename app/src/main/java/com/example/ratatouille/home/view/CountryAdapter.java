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
import java.util.Locale;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.veiwHolder> {
    List<MealDto> myList = new ArrayList<>();
    Context context;
    InsertInterface insertInterface;


    public void setList(List<MealDto>myList){
        this.myList=myList;
        notifyDataSetChanged();
    }
    public CountryAdapter (Context context,InsertInterface insertInterface){
        this.context=context;
        this.insertInterface=insertInterface;
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
        String imageName = myList.get(position).getStrArea().toLowerCase(Locale.ROOT);
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        Glide.with(context).load(resourceId)
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(holder.ivItem);
        holder.ivItem.setOnClickListener(v -> {
            insertInterface.onClickCountry(myList.get(position).getStrArea());
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

    public class veiwHolder extends RecyclerView.ViewHolder {

        public TextView tvItemName;
        public ImageView ivItem;
        public veiwHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName=itemView.findViewById(R.id.tvItemName);
            ivItem=itemView.findViewById(R.id.ivProfile);
        }
    }
}