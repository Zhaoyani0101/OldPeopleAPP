package com.zhaoyani.myapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhaoyani.myapp.R;
import com.zhaoyani.myapp.bean.Activity;
import com.zhaoyani.myapp.bean.Recipe;
import com.zhaoyani.myapp.utils.ActivityListAdapter;
import com.zhaoyani.myapp.utils.RecipeListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HealthRecipeActivity extends AppCompatActivity {
    EditText searchEt;
    ImageView searchIv,flushIv;
    ListView showLv;

    private RecyclerView mRecyclerView;

    private RecipeListAdapter mListAdapter;

    private List<Recipe> mRecipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_recipe);
        initView();



        //初始化控件
        mRecyclerView = findViewById(R.id.recyclerView);
        //模拟数据
        mRecipeList.add(new Recipe(0,R.drawable.babaozhou,"八宝粥","2022-09-12","管理员"));
        mRecipeList.add(new Recipe(1,R.drawable.baiwei,"白薇","2022-09-12","管理员"));
        mRecipeList.add(new Recipe(2,R.drawable.banxia,"半夏","2022-9-20","管理员"));
        mRecipeList.add(new Recipe(3,R.drawable.bingtanxueli,"冰糖雪梨","2022-9-20","管理员"));
        mRecipeList.add(new Recipe(4,R.drawable.bohe,"薄荷","2022-09-12","管理员"));
        mRecipeList.add(new Recipe(5,R.drawable.chengpi,"陈皮","2022-09-12","管理员"));
        mRecipeList.add(new Recipe(4,R.drawable.chengxiangcha,"沉香茶","2022-9-20","管理员"));
        mRecipeList.add(new Recipe(5,R.drawable.gancao,"甘草","2022-09-12","管理员"));

        //初始化适配器
        mListAdapter = new RecipeListAdapter(mRecipeList);

        //设置适配器
        mRecyclerView.setAdapter(mListAdapter);


    }


    private void initView() {
        searchEt = findViewById(R.id.id_et_search);
        searchIv = findViewById(R.id.id_iv_search);
        flushIv = findViewById(R.id.id_iv_flush);

    }
}