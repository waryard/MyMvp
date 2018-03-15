package com.wyd.royalprince.mymvp.utils;

import android.util.Log;


/**
 * LOG相关
 */
public class UtilLog {

    /**
     * 打印Log信息
     */
    public static void Log(String message) {
        if (Constants.DEBUG) {
            Log.d("---MintQ---", "---" + System.currentTimeMillis() + "---" + message);
        }
    }

    /**
     * 打印Log信息
     */
    public static void Log(String tag, String message) {
        if (Constants.DEBUG) {
            Log.d(tag, "MintQ---" + System.currentTimeMillis() + "---" + message);
        }
    }

    /**
     * 打印错误Log信息
     */
    public static void LogError(String tag, String message) {
        if (Constants.DEBUG) {
            Log.e(tag, "MintQ---" + System.currentTimeMillis() + "---" + message);
        }
    }

    /**
     * 打印低级别Log信息
     */
    public static void LogVerbose(String tag, String message) {
        if (Constants.DEBUG) {
            Log.v(tag, message);
        }
    }
}
