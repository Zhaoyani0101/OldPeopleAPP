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

public class MainActivity extends AppCompatActivity {
    private Banner banner;
    private List<BannerDataInfo> mBannerDataInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        banner = findViewById(R.id.id_banner);
        //设置数据
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_1,"图片1"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_2,"图片2"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_3,"图片3"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_4,"图片4"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_5,"图片5"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_6,"图片6"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_7,"图片7"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.banner_8,"图片8"));

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
                Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void onClick(View view){
        Intent intent = new Intent();
        if (view.getId() == R.id.danzuhuodong) {
            intent.setClass(MainActivity.this,PartyActivity.class);

        } else if (view.getId() == R.id.zhitongdaohe) {
            intent.setClass(MainActivity.this,InterestActivity.class);

        }else if (view.getId() == R.id.shipingliaotian) {

        }else if (view.getId() == R.id.yijianzhaoji) {

        }else if (view.getId() == R.id.dianhualianxi) {
            intent.setClass(MainActivity.this,PhoneActivity.class);

        }else if (view.getId() == R.id.daigoufuwu) {
            intent.setClass(MainActivity.this,ShoppingActivity.class);


        }else if (view.getId() == R.id.huzhufuwu) {
            intent.setClass(MainActivity.this,HelpActivity.class);


        }else if (view.getId() == R.id.jiuyifuwu) {
            intent.setClass(MainActivity.this, MedicalActivity.class);

        }else if (view.getId() == R.id.yulehuodong) {
            intent.setClass(MainActivity.this,EntertainmentActivity.class);

        }else if (view.getId() == R.id.jiankangjiance) {

        }else if (view.getId() == R.id.xinlizixun) {
            intent.setClass(MainActivity.this,HeartActivity.class);

        }else if (view.getId() == R.id.jiankangshipu) {
            intent.setClass(MainActivity.this,HealthRecipeActivity.class);

        }
        startActivity(intent);
    }
}