package com.example.ratatouille.Meal.view;

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

public class MealIngredientAdapter extends RecyclerView.Adapter<MealIngredientAdapter.viewHolder> {
    List<MealDto> myList = new ArrayList<>();
    List<String> ingredientName = new ArrayList<>();
    List<String> ingredientMeasure = new ArrayList<>();

    Context context;


    public void setList(List<MealDto>myList){
        this.myList=myList;
        notifyDataSetChanged();
    }
    public MealIngredientAdapter (Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        for (int i = 1; i <= 20; i++) {
            String ingredient = null;
            String measure = null;
            try {
                ingredient = (String) getClass().getDeclaredField("strIngredient" + i).get(myList);
                measure = (String) getClass().getDeclaredField("strMeasure" + i).get(myList);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            if (ingredient != null && !ingredient.isEmpty()) {
                ingredientName.add(ingredient);
                ingredientMeasure.add(measure);
            }

        }
       // holder.tvNameIngredient.setText();

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

        ImageView ivIngredient;
        TextView tvNameIngredient,tvMeasureIngredient;

        public viewHolder(@NonNull View v) {
            super(v);
            ivIngredient=v.findViewById(R.id.ivIngredient);
            tvNameIngredient=v.findViewById(R.id.name_ingredient);
            tvMeasureIngredient=v.findViewById(R.id.measure_ingredient);
        }
    }
}