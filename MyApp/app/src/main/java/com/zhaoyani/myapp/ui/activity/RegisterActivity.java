package com.zhaoyani.myapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhaoyani.myapp.R;


public class RegisterActivity extends BaseActivity {
    private EditText eEtUsername;
    private EditText eEtPassword;
    private EditText eEtRePassword;
    private Button eBtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
        setTitle("注册");
        setUpToolBar();
    }


    private void initEvent() {
        eBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initView() {
        eEtUsername = findViewById(R.id.id_et_username);
        eEtPassword = findViewById(R.id.id_et_password);
        eEtRePassword = findViewById(R.id.id_et_repassword);
        eBtnRegister = findViewById(R.id.id_btn_register);
    }
}