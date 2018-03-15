package com.wyd.royalprince.mymvp.mvp.h5;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;
import com.wyd.royalprince.mymvp.R;

public class H5Activity extends AppCompatActivity {

    WebView mWebView;
    String url = "http://10.17.11.13:3002/html/test.html";
    EditText et1, et2;
    String str1, str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        mWebView = (WebView) findViewById(R.id.h5_wv);
        et1 = (EditText) findViewById(R.id.et_1);
        et2 = (EditText) findViewById(R.id.et_2);
        str1 = et1.getText().toString();
        str2 = et2.getText().toString();
        mWebView.loadUrl(url);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JsInteration(), "android");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals("http://10.17.11.13:3002/html/test2.html")) {
                    Log.e("-------->", "shouldOverrideUrlLoading: " + url);
//                    startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    return true;
                } else {
                    mWebView.loadUrl(url);
                    return false;
                }
            }
        });
        findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //            mWebView.loadUrl("javascript:do()");
                mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Toast.makeText(H5Activity.this, "结果是：" + value, Toast.LENGTH_SHORT).show();
                        Log.e("----------->", "onReceiveValue value=" + value);
                    }
                });
            }
        });
    }

    //Android调用有返回值js方法
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {

        mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.e("----------->", "onReceiveValue value=" + value);
            }
        });
    }

    public class JsInteration {

        @JavascriptInterface
        public String back(String str) {
            Log.e("----------->", "back()");
            Toast.makeText(H5Activity.this, "hhhhh点击了:" + str, Toast.LENGTH_SHORT).show();

            return "安卓本地返回：" + str;
        }
    }
}