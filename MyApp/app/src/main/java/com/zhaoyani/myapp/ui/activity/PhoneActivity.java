package com.zhaoyani.myapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.zhaoyani.myapp.R;

public class PhoneActivity extends AppCompatActivity {
    private EditText phoneNum;
    private ImageButton call_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        if(shouldAskPermissions())
        {
            askPermissions();
        }
        phoneNum= (EditText) findViewById(R.id.phoneNum);
        call_btn= (ImageButton) findViewById(R.id.call_btn);
        call_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneNum.getText()));
                startActivity(intent);
            }
        });
    }
    protected boolean shouldAskPermissions(){
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    protected void askPermissions(){
        String[] permissions = {
                "android.permission.CALL_PHONE"
        };
        int requestCode = 200;
        requestPermissions(permissions,requestCode);
    }
}
