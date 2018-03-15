package com.wyd.royalprince.mymvp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.wyd.royalprince.mymvp.android.MyApp;
import com.wyd.royalprince.mymvp.utils.ToolUtils;

public class LastingSharedPref {

  public static final String PREFERENCES_NAME = "xqd_preferences";    //SharedPreferences name
  public static final String KEY_SMS_TOKEN_ITEM = "sms_item_token";   //短信获取临时token
  public static final String KEY_LAND_TOKEN = "land_token";   //登陆token
  public static final String KEY_EXPIRATION_TOKEN = "expiration_token";   //过期token
  public static final String KEY_CURRENT_VERSION = "currentVersion";   //登陆token
  public static final String KEY_IS_LOGIN = "is_login";   //是否登陆
  public static final String KEY_FROM_RECRUITBATCHID = "from_recruitBatchId";   //存储 RecruitBatchId
  public static final String KEY_DEVICE_ID = "device_id";   //对于没有device_id的设备自己随机一个持久化，以后就使用这个
  public static final String KEY_APP_VERSION_RESP_ITEM = "app_version_resp_item";   //版本信息对象
  public static final String KEY_PHONENUM = "phonenum";   //登陆token
  private static LastingSharedPref mLastingSharedPref = null;
  private static SharedPreferences mSharedPreferences = null;
  private static Gson GSON = new Gson();

  private LastingSharedPref(Context context) {
    if (mSharedPreferences == null) {
      mSharedPreferences = MyApp.getInstance().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
  }

  public synchronized static LastingSharedPref getInstance() {
    if (mLastingSharedPref == null) {
      mLastingSharedPref = new LastingSharedPref(MyApp.getInstance());
    }
    return mLastingSharedPref;
  }

  /**
   * 持久化对象
   */
  public void putObject(String key, Object object) {
    if (object == null) {
      throw new IllegalArgumentException("object is null");
    }

    if (key.equals("") || key == null) {
      throw new IllegalArgumentException("key is empty or null");
    }

    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(key, GSON.toJson(object));
    editor.commit();
  }

  /**
   * 获取持久化对象
   */
  public <T> T getObject(String key, Class<T> a) {
    String gson = mSharedPreferences.getString(key, null);
    if (gson == null) {
      return null;
    } else {
      try {
        return GSON.fromJson(gson, a);
      } catch (Exception e) {
        throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");
      }
    }
  }

  /**
   * 方法描述：获取设备信息随机的唯一标示
   */
  public String getDeviceId() {
    return mSharedPreferences.getString(KEY_DEVICE_ID, null);
  }

  public void setDeviceId(String deviceId) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_DEVICE_ID, deviceId);
    editor.commit();
  }

  /**
   * 方法描述：获取登陆信息唯一标示
   */
  public String getBuildToken() {
    String token = getToken();
    if (TextUtils.isEmpty(token)) {
      return "";
    }
    return "Bearer " + token;
  }

  /**
   * 方法描述：获取登陆信息唯一标示
   */
  public String getToken() {
    return mSharedPreferences.getString(KEY_LAND_TOKEN, null);
  }

  public void setToken(String token) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_LAND_TOKEN, token);
    editor.commit();
  }

  public String getSMSToken() {
    return mSharedPreferences.getString(KEY_SMS_TOKEN_ITEM, "");
  }

  /**
   * 短信token
   */
  public void setSMSToken(String token) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_SMS_TOKEN_ITEM, token);
    editor.commit();
  }

  /**
   * 方法描述：获取登陆的手机号
   */
  public String getPhoneNum() {
    return mSharedPreferences.getString(KEY_PHONENUM, null);
  }

  public void setPhoneNum(String phonenum) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_PHONENUM, phonenum);
    editor.commit();
  }

  /**
   * 方法描述：获取失效token
   */
  public String getExpirationToken() {
    return mSharedPreferences.getString(KEY_EXPIRATION_TOKEN, null);
  }

  public void setExpirationToken(String token) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_EXPIRATION_TOKEN, token);
    editor.commit();
  }

  /**
   * 方法描述：获取版本信息
   */
  public String getAppVersion() {
    return mSharedPreferences.getString(KEY_CURRENT_VERSION, null);
  }

  public void setAppVersion(String appVersion) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_CURRENT_VERSION, appVersion);
    editor.commit();
  }

  /**
   * 是否登陆
   */
  public void setIsLogin(boolean isLogin) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putBoolean(KEY_IS_LOGIN, isLogin);
    editor.commit();
  }

  public boolean isLogin() {
    String token = mSharedPreferences.getString(KEY_LAND_TOKEN, null);
    if (token == null) {
      return false;
    }
    return token != null ? true : false;
  }


  /**
   * 是否第一次登陆
   */
  public void setIsFirstIn(boolean isFirstIn) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putBoolean(ToolUtils.getVersion(), isFirstIn);
    editor.commit();
  }

  public boolean isFirstIn() {
    return mSharedPreferences.getBoolean(ToolUtils.getVersion(), true);
  }

  /**
   * 持久化版本对象
   */
  public void putAppVersionRespItem(Object object) {
    if (object == null) {
      throw new IllegalArgumentException("object is null");
    }

    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_APP_VERSION_RESP_ITEM, GSON.toJson(object));
    editor.commit();
  }

  /**
   * 获取持久化版本对象
   */
  public <T> T getAppVersionRespItem(Class<T> a) {
    String gson = mSharedPreferences.getString(KEY_APP_VERSION_RESP_ITEM, null);
    if (gson == null) {
      return null;
    } else {
      try {
        return GSON.fromJson(gson, a);
      } catch (Exception e) {
        throw new IllegalArgumentException("Object storaged with key " + KEY_APP_VERSION_RESP_ITEM + " is instanceof other class");
      }
    }
  }

  public String getRecruitBatchId() {
    return mSharedPreferences.getString(KEY_FROM_RECRUITBATCHID, "");
  }

  /**
   * 保存recruitBatchId
   */
  public void setRecruitBatchId(String recruitBatchId) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putString(KEY_FROM_RECRUITBATCHID, recruitBatchId);
    editor.commit();
  }

  /**
   * 清除缓存 是否第一次登陆 不能清除
   */
  public void clear() {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    //        editor.remove(KEY_LAND_TOKEN);
    //        editor.remove(KEY_EXPIRATION_TOKEN);
    //        editor.remove(KEY_IS_LOGIN);
    //        editor.remove(KEY_APP_VERSION_RESP_ITEM);
    //        editor.remove(KEY_FROM_RECRUITBATCHID);
    editor.clear();

    editor.commit();
  }

  /**
   * 存贮boolean
   */
  public void putBoolean(String key, boolean value) {
    if ("".equals(key) || key == null) {
      throw new IllegalArgumentException("key is empty or null");
    }
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putBoolean(key, value);
    editor.commit();
  }

  /**
   * 获取boolean
   */
  public boolean getBoolean(String key, boolean defaultValue) {
    if ("".equals(key) || key == null) {
      throw new IllegalArgumentException("key is empty or null");
    }
    boolean value = mSharedPreferences.getBoolean(key, defaultValue);
    return value;
  }
}
