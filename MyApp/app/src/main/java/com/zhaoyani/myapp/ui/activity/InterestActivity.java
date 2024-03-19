package com.zhaoyani.myapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zhaoyani.myapp.R;
import com.zhaoyani.myapp.bean.Title;

import java.util.ArrayList;
import java.util.List;

public class InterestActivity extends AppCompatActivity {

    private List<Title> titles = new ArrayList<>();
    //private String [] titles = {"娱乐","军事","教育","文化","健康","财经","体育","科技","汽车"};
    private TabLayout tab_layout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        //初始化title数据
        titles.add(new Title("推荐","top"));
        titles.add(new Title("国内","guonei"));
        titles.add(new Title("国际","guoji"));
        titles.add(new Title("娱乐","yule"));
        titles.add(new Title("体育","tiyu"));
        titles.add(new Title("军事","junshi"));
        titles.add(new Title("科技","keji"));
        titles.add(new Title("财经","caijing"));
        titles.add(new Title("游戏","youxi"));
        titles.add(new Title("汽车","qiche"));
        titles.add(new Title("健康","jiankang"));



        //初始化控件
        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

        //viewPager需要设置一个adapter
       viewPager.setAdapter(new FragmentStateAdapter(this) {
           @NonNull
           @Override
           public Fragment createFragment(int position) {
               String title = titles.get(position).getPy_title();
               TabNewsFragment tabNewsFragment = TabNewsFragment.newInstance(title);
               return tabNewsFragment;
           }

           @Override
           public int getItemCount() {
               return titles.size();
           }
       });


        //tab_layout点击事件
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置viewPager选中当前页
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //tab_layout和viewPager关联在一起
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position).getTitle());
            }
        });

        tabLayoutMediator.attach();

    }
}