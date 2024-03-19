package com.zhaoyani.myapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.zhaoyani.myapp.R;

public class SplashActivity extends AppCompatActivity {
    private Button eBtnSkip;
    private Handler eHandler = new Handler();
    private Runnable eRunnable = new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initEvent();
        eHandler.postDelayed(eRunnable,3000);
    }

    private void initEvent() {
        eBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLoginActivity();
                eHandler.removeCallbacks(eRunnable);
            }
        });
    }

    private void initView() {
        eBtnSkip = findViewById(R.id.id_btn_skip);
    }

    public void toLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eHandler.removeCallbacks(eRunnable);
    }
}