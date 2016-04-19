package com.deng.pushdemoservice1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;


public class WebActivity extends Activity {


    private TextView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
       mWebView = (TextView)findViewById(R.id.tv_url);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        System.out.println(url);
        mWebView.setText(url);
    }

}
