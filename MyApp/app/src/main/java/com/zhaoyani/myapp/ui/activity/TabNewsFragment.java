package com.zhaoyani.myapp.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhaoyani.myapp.R;
import com.zhaoyani.myapp.bean.News;
import com.zhaoyani.myapp.utils.NewsListAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TabNewsFragment extends Fragment {

    private String url="http://v.juhe.cn/toutiao/index?key=e07d2481deb08f54a2b38909a3fd4155&type=";

    private static final String ARG_PARAM = "title";
    private String title;

    private NewsListAdapter mNewsListAdapter;

    private View rootView;

    private RecyclerView recyclerView;

    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==100){
                String data = (String) msg.obj;
                News news = new Gson().fromJson(data,News.class);
                if(news != null && news.getError_code()==0){
                    if(null != mNewsListAdapter){
                        mNewsListAdapter.setListData(news.getResult().getData());
                    }
                }else{
                    Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public TabNewsFragment() {

    }

    public static TabNewsFragment newInstance(String param) {
        TabNewsFragment fragment = new TabNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM,param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_tab_news, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
            return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mNewsListAdapter = new NewsListAdapter(getActivity());
        recyclerView.setAdapter(mNewsListAdapter);


        //获取数据
        getHttpData();
    }

    private void getHttpData(){
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //构构造Request对象
        Request request = new Request.Builder()
                .url(url+title)
                .get()
                .build();
        //通过OkHttpClient和Request对象来构建Call对象
        Call call = okHttpClient.newCall(request);
        //通过Call对象的enqueue(Callback)方法来执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("-------------", "onFailure: "+e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                //Log.d("--------------", "onResponse: " +data);
                //System.out.println(data);

                Message message = new Message();
                message.what=100;
                message.obj=data;
                mHandler.sendMessage(message);

            }
        });

    }
}