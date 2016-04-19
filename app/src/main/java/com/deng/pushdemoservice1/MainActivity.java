package com.deng.pushdemoservice1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvHW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,PushService.class));
        tvHW = (TextView) findViewById(R.id.tv_hw);
//        Intent intent = getIntent();
//        tvHW.setText(intent.getStringExtra("content"));

    }
}
