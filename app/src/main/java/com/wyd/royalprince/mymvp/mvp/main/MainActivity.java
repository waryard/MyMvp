package com.wyd.royalprince.mymvp.mvp.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.wyd.royalprince.mymvp.R;
import com.wyd.royalprince.mymvp.android.BaseActivity;
import com.wyd.royalprince.mymvp.mvp.h5.H5Activity;
import com.wyd.royalprince.mymvp.mvp.main.presenter.MainPresenter;
import com.wyd.royalprince.mymvp.mvp.main.view.IMainActivityView;
import com.wyd.royalprince.mymvp.mvp.news.NewsActivity;
import com.wyd.royalprince.mymvp.mvp.translate.TranslateActivity;
import com.wyd.royalprince.mymvp.utils.AntiShake;
import java.lang.reflect.Method;

public class MainActivity extends BaseActivity<IMainActivityView, MainPresenter> implements IMainActivityView {


    @BindView(R.id.main_tv)
    TextView mainTv;
    @BindView(R.id.main_btn)
    Button mainBtn;
    @BindView(R.id.main_btn2)
    Button mainBtn2;
    @BindView(R.id.main_btn3)
    Button mainBtn3;
    @BindView(R.id.main_btn4)
    Button mainBtn4;

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initActivity() {
        mainTv.setText("黄油刀的测试text");
    }

    @OnClick({R.id.main_btn, R.id.main_btn2, R.id.main_btn3,R.id.main_btn4})
    public void onClick(View view) {
        if (AntiShake.check(view.getId())) {    //判断是否多次点击
            return;
        }

        switch (view.getId()) {
            case R.id.main_btn:
                Toast.makeText(MainActivity.this, "点击了1按钮", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, H5Activity.class);
                startActivity(i);
                break;
            case R.id.main_btn2:
                Toast.makeText(MainActivity.this, "点击了按钮2！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.main_btn3:
                checkDeviceHasNavigationBar(MainActivity.this);
                isNavigationBarShow();
                break;
            case R.id.main_btn4:
                Intent intent1 = new Intent(MainActivity.this, TranslateActivity.class);
                startActivity(intent1);
                MainActivity.this.finish();
                break;
        }
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    //获取是否存在NavigationBar
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
            Log.e("------->", "navBarOverride:" + navBarOverride);
        } catch (Exception e) {

        }
        Log.e("------->", "hasNavigationBar:" + hasNavigationBar);
        return hasNavigationBar;

    }

    public boolean isNavigationBarShow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = MainActivity.this.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            boolean result = realSize.y != size.y;
            Log.e("------->", "result:" + result);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(MainActivity.this).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            Log.e("------->", "menu:" + menu + ",back:" + back);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }
}
