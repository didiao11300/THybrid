package com.example.thybrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null == unbinder) {
            unbinder.unbind();
        }
    }

    @OnClick(R.id.btn_webview)
    public void onClick() {
        Intent intent = new Intent(MainActivity.this, H5Activity.class);
        startActivity(intent);
    }
}
