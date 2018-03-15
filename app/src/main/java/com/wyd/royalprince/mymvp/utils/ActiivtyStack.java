package com.wyd.royalprince.mymvp.utils;

import android.app.Activity;
import java.util.Stack;

/**
 * Created by wyd on 2017/1/20.
 */

public class ActiivtyStack {
    private static Stack<Activity> mActivityStack = new Stack<Activity>();
    private static ActiivtyStack instance = new ActiivtyStack();

    private ActiivtyStack() {
    }

    public static ActiivtyStack getScreenManager() {
        return instance;
    }

    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    // 将当前Activity推入栈中
    public void pushActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    // 退出栈中所有Activity
    public void popAllActivityExceptOne() {
        while (mActivityStack.size() > 0) {
            Activity activity = mActivityStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
