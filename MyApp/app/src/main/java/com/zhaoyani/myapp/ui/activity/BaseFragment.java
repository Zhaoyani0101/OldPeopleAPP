package com.zhaoyani.myapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhaoyani.myapp.R;

public class BaseFragment extends Fragment implements View.OnClickListener {

    public Context context;

    public BaseFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_base,container,false);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {

    }
}
