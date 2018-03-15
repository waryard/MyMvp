package com.wyd.royalprince.mymvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.wyd.royalprince.mymvp.android.MyApp;
import com.wyd.royalprince.mymvp.data.LastingSharedPref;
import java.util.Random;

public class ToolUtils {
  private static final String TAG = ToolUtils.class.getSimpleName();
  /**
   * 获取设备唯一标识
   */
  public static String getDeviceID(Context context) {
    String deviceid = "";
    try {
      TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

      if (deviceid == null || deviceid.length() == 0) {
        String imei = tm.getDeviceId();
        deviceid = imei;
        if (Constants.DEBUG) {
          UtilLog.Log("DEVICE", "deviceid 2=" + deviceid);
        }
      }
      if (deviceid == null || deviceid.length() == 0) {
        //            String imei = tm.getDeviceId();
        String imsi = tm.getSubscriberId();
        deviceid = imsi;
        if (Constants.DEBUG) {
          UtilLog.Log("DEVICE", "deviceid 2_imsi=" + deviceid);
        }
      }
      if (deviceid == null || deviceid.length() == 0) {
        deviceid = tm.getSimSerialNumber();
        if (Constants.DEBUG) {
          UtilLog.Log("DEVICE", "deviceid 3=" + deviceid);
        }
      }

      if (Constants.DEBUG) {
        UtilLog.Log(TAG, "deviceid =" + deviceid);
      }
    } catch (SecurityException e) {

    }

    if (TextUtils.isEmpty(deviceid)) {
      deviceid = LastingSharedPref.getInstance().getDeviceId();
      if (TextUtils.isEmpty(deviceid)) {
        deviceid = String.valueOf(randomDeviceid());
        LastingSharedPref.getInstance().setDeviceId(deviceid);
      }
    } else {
      String deviceidSP = LastingSharedPref.getInstance().getDeviceId();
      if (!TextUtils.isEmpty(deviceidSP)) {
        if (!deviceid.equals(deviceidSP)) {
          deviceid = deviceidSP;
        }
      }
    }

    return deviceid;
  }

  /**
   * 生成唯一的IMEI
   */
  private static int randomDeviceid() {
    Random random = new Random();
    long currentTime = System.currentTimeMillis() / 1000;
    return random.nextInt(Integer.parseInt(String.valueOf(currentTime)));
  }


  /**
   * 获取版本号
   *
   * @return 当前应用的版本号
   */
  public static String getVersion() {
    try {
      PackageManager manager = MyApp.getInstance().getPackageManager();
      PackageInfo info = manager.getPackageInfo(MyApp.getInstance().getPackageName(), 0);
      String version = info.versionName;
      if (Constants.DEBUG) {
        UtilLog.Log("version ----------->", version);
      }
      return version;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  /**
   * 检查当前网络是否可用
   */

  public static boolean isNetworkAvailable(Activity activity) {
    Context context = activity.getApplicationContext();
    // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    if (connectivityManager == null) {
      return false;
    } else {
      // 获取NetworkInfo对象
      NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

      if (networkInfo != null && networkInfo.length > 0) {
        for (int i = 0; i < networkInfo.length; i++) {
          if (Constants.DEBUG) {
            System.out.println(i + "===状态===" + networkInfo[i].getState());
            System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
          }
          // 判断当前网络状态是否为连接状态
          if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
        }
      }
    }
    return false;
  }
  /**
   * 获取VersionCode
   *
   * @return 当前应用的VersionCode
   */
  public static int getVersionCode() {
    try {
      PackageManager manager = MyApp.getInstance().getPackageManager();
      PackageInfo info = manager.getPackageInfo(MyApp.getInstance().getPackageName(), 0);
      int versionCode = info.versionCode;
      if (Constants.DEBUG) {
        UtilLog.Log("version ----------->", versionCode + "");
      }
      return versionCode;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}


