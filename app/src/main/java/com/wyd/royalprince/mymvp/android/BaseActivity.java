package com.wyd.royalprince.mymvp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.wyd.royalprince.mymvp.mvp.main.MainActivity;
import com.wyd.royalprince.mymvp.common.presenter.BasePresenter;
import com.wyd.royalprince.mymvp.utils.ActiivtyStack;
import com.wyd.royalprince.mymvp.widget.LoadDialog;


/**
 * 所有activity的基类
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

  private LoadDialog loadDialog;
  protected Activity mContext;
  LayoutInflater layoutInflater;

  public T presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // 每次加入stack
    presenter = initPresenter();
    presenter.setTag(this);
    if (presenter == null) {
      throw new NullPointerException("presenter must be created");
    }
    presenter.attach((V) this);

    mContext = this;
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (getIntent() != null) {
      initData(getIntent());
    }

    getWindow().setFormat(PixelFormat.TRANSLUCENT);//防止添加 videoview 黑屏

    setContentView(getLayoutResID());
    ButterKnife.bind(this);
    initLoadingDialog();
    initActivity();
    ActiivtyStack.getScreenManager().pushActivity(this);
    MyApp.getInstance().addActivity(this);
  }


  @Override
  protected void onResume() {
    super.onResume();

  }

  @Override
  protected void onPause() {
    super.onPause();

  }

  /**
   * 实例化presenter
   */
  public abstract T initPresenter();

  @Override
  protected void onDestroy() {
    presenter.cancel();
    presenter.dettach();
    super.onDestroy();
//    ButterKnife.unbind(this);
  }

  public abstract int getLayoutResID();


  public abstract void initActivity();

  /**
   * 用于接收数据
   */
  public void initData(Intent intent) {
  }

  private void initLoadingDialog() {
    loadDialog = new LoadDialog(this);
  }

  protected void showLoadingDialog(String msg) {
    loadDialog.setCancelable(true);
    loadDialog.show();
    if (!TextUtils.isEmpty(msg)) {
      loadDialog.setMessage(msg);
    }
  }

  protected void showLoadingDialogCanCancel(String msg) {
    loadDialog.setCancelable(true);
    loadDialog.show();
    if (!TextUtils.isEmpty(msg)) {
      loadDialog.setMessage(msg);
    }
  }

  protected void showLoadingDialogCanCancel() {
    showLoadingDialogCanCancel(null);
  }

  protected void showLoadingDialog() {
    showLoadingDialog(null);
  }

  protected void hideLoadingDialog() {
    loadDialog.dismiss();
  }


  // 连续两次退出应用程序
  private long exitTime = 0;

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyEvent != null) {
      return keyEvent.onBackPress();
    }
    if (mContext instanceof MainActivity) {
      if (keyCode == KeyEvent.KEYCODE_BACK
          && event.getAction() == KeyEvent.ACTION_DOWN) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
          Toast.makeText(mContext,"再按一次退出应用",Toast.LENGTH_SHORT).show();
          exitTime = System.currentTimeMillis();
        } else {
          ActiivtyStack.getScreenManager().popActivity(this);
          MyApp.getInstance().exit();
          finish();
        }
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }

  private MainKeyEvent keyEvent;

  public void setKeyEvent(MainKeyEvent keyEvent) {
    this.keyEvent = keyEvent;
  }

  public static interface MainKeyEvent {

    public boolean onBackPress();
  }

  public static void gotoActivity(AppCompatActivity activity1, AppCompatActivity activity2) {
    Intent intent = new Intent(activity1, activity2.getClass());
    activity1.startActivity(intent);
    activity1.finish();
  }

  public static void startActivity(AppCompatActivity activity1, AppCompatActivity activity2) {
    Intent intent = new Intent(activity1, activity2.getClass());
    activity1.startActivity(intent);
  }
}
