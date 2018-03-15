package com.wyd.royalprince.mymvp.android;

import android.app.Activity;
import android.app.Application;
import butterknife.BuildConfig;
import butterknife.ButterKnife;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wyd on 2017/5/26.
 */

public class MyApp extends Application {

  private static final String TAG = MyApp.class.getSimpleName();
  private static MyApp mInstance;
  private List<Activity> activityList = new LinkedList<Activity>();

  @Override
  public void onCreate() {
    super.onCreate();
    mInstance = this;
//    ButterKnife.setDebug(BuildConfig.DEBUG);
  }

  public static MyApp getInstance() {
    return mInstance;
  }

  public void addActivity(Activity activity) {
    activityList.add(activity);
  }

  /**
   * 遍历所有Activity并finish
   * 在每一个Activity中的onCreate方法里添加该Activity到MyApplication对象实例容器中
   * MyApplication.getInstance().addActivity(this);
   * 在需要结束所有Activity的时候调用exit方法
   * MyApplication.getInstance().exit();
   */
  public void exit() {
    for (Activity activity : activityList) {
      activity.finish();
    }
//        System.exit(0);

  }
}
