package com.zhaoyani.myapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoyani.myapp.R;
import com.zhaoyani.myapp.bean.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.MyHolder> {

    private List<Activity> mActivities = new ArrayList<>();

    public ActivityListAdapter(List<Activity> list){
        this.mActivities = list;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_lv,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        Activity activity =  mActivities.get(position);
        //设置数据
        holder.tv_title.setText(activity.getActivity_title());
        holder.tv_startTime.setText("开始时间："+activity.getActivity_startTime());
        holder.tv_endTime.setText("结束时间"+activity.getActivity_endTime());
        holder.tv_creator.setText("发起人"+activity.getActivity_creator());
        holder.activity_img.setImageResource(activity.getActivity_img());


    }

    @Override
    public int getItemCount() {
        return mActivities.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_startTime;
        TextView tv_endTime;
        TextView tv_creator;
        ImageView activity_img;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            //初始化控件
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_startTime = itemView.findViewById(R.id.tv_startTime);
            tv_endTime = itemView.findViewById(R.id.tv_endTime);
            tv_creator = itemView.findViewById(R.id.tv_creator);
            activity_img = itemView.findViewById(R.id.item_activity_iv);


        }
    }
}
