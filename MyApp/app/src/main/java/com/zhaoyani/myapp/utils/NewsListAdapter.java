package com.zhaoyani.myapp.utils;

import android.content.Context;
import android.media.MediaDrm;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhaoyani.myapp.R;
import com.zhaoyani.myapp.bean.News;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyHolder> {

    private List<News.ResultDTO.DataDTO> mDataDTOList = new ArrayList<>();

    private Context mContext;


    public NewsListAdapter(Context context) {
        this.mContext = context;
    }
    public void setListData(List<News.ResultDTO.DataDTO> listData){
        this.mDataDTOList = listData;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        News.ResultDTO.DataDTO dataDTO = mDataDTOList.get(position);
        holder.author_name.setText("来源："+dataDTO.getAuthor_name());
        holder.date.setText(dataDTO.getDate());
        holder.title.setText(dataDTO.getTitle());

        Glide.with(mContext).load(dataDTO.getThumbnail_pic_s()).into(holder.thumbnail_pic_s);

    }

    @Override
    public int getItemCount() {
        return mDataDTOList.size();
    }

    static class MyHolder extends RecyclerView. ViewHolder{

        ImageView thumbnail_pic_s;
        TextView title;
        TextView author_name;
        TextView date;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail_pic_s = itemView.findViewById(R.id.thumbnail_pic_s);
            title = itemView.findViewById(R.id.title);
            author_name = itemView.findViewById(R.id.author_name);
            date = itemView.findViewById(R.id.date);

        }
    }
}
