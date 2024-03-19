package com.zhaoyani.myapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zhaoyani.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    private Banner banner;
    private List<BannerDataInfo> mBannerDataInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //初始化控件
        banner = findViewById(R.id.id_banner);
        //设置数据
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.help1,"图片1"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.help2,"图片2"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.help3,"图片3"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.help4,"图片4"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.help5,"图片5"));

        //设置适配器
        banner.setAdapter(new BannerImageAdapter<BannerDataInfo>(mBannerDataInfo) {

            @Override
            public void onBindView(BannerImageHolder holder, BannerDataInfo bannerDataInfod, int position, int size) {
                //设置数据
                holder.imageView.setImageResource(bannerDataInfod.getImg());
            }
        });

        //添加生命周期观察者
        banner.addBannerLifecycleObserver(this);
        //设置指示器
        banner.setIndicator(new CircleIndicator(this));


        //banner点击事件
        banner.setOnBannerListener(new OnBannerListener<BannerDataInfo>() {
            @Override
            public void OnBannerClick(BannerDataInfo data, int position) {
                Toast.makeText(HelpActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void onClick(View view){
        Intent intent = new Intent();
        if (view.getId() == R.id.fbqq) {
            intent.setClass(HelpActivity.this,yydgActivity.class);

        } else if (view.getId() == R.id.bmlb) {
            intent.setClass(HelpActivity.this,yydgActivity.class);

        }else if (view.getId() == R.id.fbpl) {
            intent.setClass(HelpActivity.this,yydgActivity.class);

        }else if (view.getId() == R.id.wdsc) {
            intent.setClass(HelpActivity.this,yydgActivity.class);

        }
        startActivity(intent);
    }
}