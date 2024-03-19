package com.zhaoyani.myapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhaoyani.myapp.R;

public class LoginActivity extends BaseActivity {
    private EditText eEtUsername;
    private EditText eEtPassword;
    private Button eBtnLogin;
    private TextView eTvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setTitle("登录");
        initEvent();
        setUpToolBar();
    }

    private void initEvent() {
        eBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 登录验证
                toHomeAcyivity();

            }
        });
        eTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegisterActivity();

            }
        });


    }

    private void toRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void toHomeAcyivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        eEtUsername = findViewById(R.id.id_et_username);
        eEtPassword = findViewById(R.id.id_et_password);
        eBtnLogin = findViewById(R.id.id_btn_login);
        eTvRegister = findViewById(R.id.id_tv_register);




    }
}