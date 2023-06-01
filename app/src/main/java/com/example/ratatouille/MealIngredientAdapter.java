package com.example.ratatouille;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouille.model.MealDto;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MealIngredientAdapter extends RecyclerView.Adapter<MealIngredientAdapter.viewHolder> {

    MealDto meal;
    List<String> ingredientName = new ArrayList<>();
    List<String> ingredientMeasure = new ArrayList<>();

    Context context;


    public void setList(MealDto meal){
        this.meal=meal;
        ingredientName.clear();
        ingredientMeasure.clear();
        for (int i = 1; i <= 20; i++) {
            String ingredient = null;
            String measurement = null;


            try {
                String ingredientMethodName = "getStrIngredient" + i;
                Log.d(TAG, "Invoking method: " + ingredientMethodName);
                ingredient = (String) meal.getClass().getMethod(ingredientMethodName).invoke(meal);
                String measurementMethodName = "getStrMeasure" + i;
                Log.d(TAG, "Invoking method: " + measurementMethodName);
                measurement = (String) meal.getClass().getMethod(measurementMethodName).invoke(meal);
                if (ingredient == null) ingredient = "";
                if (measurement == null) measurement = "";
                if (!ingredient.equals("")) {
                    ingredientName.add(ingredient);

                }
                if (!measurement.equals("")) {
                    ingredientMeasure.add(measurement);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        }

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
        holder.tvNameIngredient.setText(ingredientName.get(position));
        holder.tvMeasureIngredient.setText(ingredientMeasure.get(position));
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredientName.get(position)+"-small.png"
                )
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.profilphoto)
                .error(R.drawable.profilphoto).into(holder.ivIngredient);

    }

    @Override
    public int getItemCount() {
        return ingredientName.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView ivIngredient;
        TextView tvNameIngredient,tvMeasureIngredient;

        public viewHolder(@NonNull View v) {
            super(v);
            ivIngredient=v.findViewById(R.id.ivProfile);
            tvNameIngredient=v.findViewById(R.id.name_ingredient);
            tvMeasureIngredient=v.findViewById(R.id.measure_ingredient);
        }
    }
}