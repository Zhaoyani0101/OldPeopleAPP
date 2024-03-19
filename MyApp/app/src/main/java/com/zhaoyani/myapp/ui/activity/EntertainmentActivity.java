package com.zhaoyani.myapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zhaoyani.myapp.R;
import com.zhaoyani.myapp.bean.Activity;
import com.zhaoyani.myapp.utils.ActivityListAdapter;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private ActivityListAdapter mListAdapter;

    private List<Activity> mActivityList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        //初始化控件
        mRecyclerView = findViewById(R.id.recyclerView);
        //模拟数据
        mActivityList.add(new Activity(0,R.drawable.banner_1,"舞动青春","2022-09-12","2022-9-20","管理员"));
        mActivityList.add(new Activity(1,R.drawable.banner_2,"美食大赛","2022-09-12","2022-9-20","管理员"));
        mActivityList.add(new Activity(2,R.drawable.banner_3,"喂鸽子","2022-09-12","2022-9-20","管理员"));
        mActivityList.add(new Activity(3,R.drawable.banner_4,"登山","2022-09-12","2022-9-20","管理员"));
        mActivityList.add(new Activity(4,R.drawable.banner_5,"啦啦操","2022-09-12","2022-9-20","管理员"));
        mActivityList.add(new Activity(5,R.drawable.banner_6,"唱歌","2022-09-12","2022-9-20","管理员"));
        mActivityList.add(new Activity(4,R.drawable.banner_7,"唱歌","2022-09-12","2022-9-20","管理员"));
        mActivityList.add(new Activity(5,R.drawable.banner_8,"唱歌","2022-09-12","2022-9-20","管理员"));

        //初始化适配器
        mListAdapter = new ActivityListAdapter(mActivityList);

        //设置适配器
        mRecyclerView.setAdapter(mListAdapter);


    }
}