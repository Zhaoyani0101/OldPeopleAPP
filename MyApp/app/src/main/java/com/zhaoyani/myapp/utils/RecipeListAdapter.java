package com.zhaoyani.myapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoyani.myapp.R;
import com.zhaoyani.myapp.bean.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyHolder> {

    private List<Recipe> mRecipes = new ArrayList<>();

    public RecipeListAdapter(List<Recipe> list){
        this.mRecipes = list;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_lv,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        Recipe recipe =  mRecipes.get(position);
        //设置数据
        holder.tv_title.setText(recipe.getRecipe_title());
        holder.tv_createTime.setText("发布时间："+recipe.getCreateTime());
        holder.tv_creator.setText("发布人"+recipe.getCreator());
        holder.recipe_img.setImageResource(recipe.getRecipe_img());


    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_createTime;
        TextView tv_creator;
        ImageView recipe_img;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            //初始化控件
            tv_title = itemView.findViewById(R.id.item_recipe_tv_title);
            tv_createTime = itemView.findViewById(R.id.item_recipe_tv_time);
            tv_creator = itemView.findViewById(R.id.item_recipe_tv_creator);
            recipe_img = itemView.findViewById(R.id.item_recipe_iv);


        }
    }
}
