package com.example.thybrid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thybrid.core.FuncCallBack;
import com.example.thybrid.core.TWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by tory on 2018/1/30.
 */

public class H5Activity extends Activity {
    private Unbinder unbinder;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.btn_js)
    Button btn_js;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity后台不可见
        setContentView(R.layout.activity_h5);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //浏览器一般为singleTop模式心来的intent直接覆盖
    }

    @Override
    protected void onStart() {
        //activity后台且可见
        super.onStart();
        //加载本地文件
        webView.loadUrl("file:///android_asset/html/test.html");
        //加载网络
//        webView.loadUrl("http://www.baidu.com/");
    }

    @OnClick(R.id.btn_js)
    void callJs() {
        if (webView instanceof TWebView) {
            ((TWebView) webView).callJs("confirmDialog", "这是android", new FuncCallBack() {
                @Override
                public void onCallback(String data) {
                    Toast.makeText(H5Activity.this, data, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onResume() {
//        activity前台可见，可以操作动画和view
        super.onResume();
    }

    @Override
    protected void onPause() {
        //activity前台可见500ms
        super.onPause();
    }

    @Override
    protected void onStop() {
//        activity后台不可见，一般保存数据库
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //回收资源
        if (null != unbinder) {
            unbinder.unbind();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
