package com.wyd.royalprince.mymvp.mvp.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import com.wyd.royalprince.mymvp.R;
import com.wyd.royalprince.mymvp.mvp.main.MainActivity;


public class SplashActivity extends AppCompatActivity {

  private static final String LOG_TAG = "SplashActivity";

  private static final int GO_HOME = 1000;
  // 延迟3秒
  private static long SPLASH_DELAY_MILLIS = 1500;

  /**
   * Handler:跳转到不同界面
   */
  private Handler mHandler = new Handler() {

    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case GO_HOME:
          goHome();
          break;
      }
      super.handleMessage(msg);
    }
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    init();

  }

  private void init() {
    // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
    mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
  }

  private void goHome() {
    Intent intent = new Intent();
    intent.setClass(SplashActivity.this, MainActivity.class);
    SplashActivity.this.startActivity(intent);
    SplashActivity.this.finish();
  }

}