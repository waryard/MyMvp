package com.wyd.royalprince.mymvp.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.wyd.royalprince.mymvp.common.presenter.BasePresenter;
import com.wyd.royalprince.mymvp.utils.UtilLog;
import com.wyd.royalprince.mymvp.widget.LoadDialog;


public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

  protected Context mContext;
  private View view;
  private LoadDialog loadDialog;
  public T presenter;


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.mContext = context;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    presenter = initPresenter();
    presenter.setTag(this);
    if (presenter == null) {
      throw new NullPointerException("presenter must be created");
    }
    presenter.attach((V) this);
  }


  @Override
  public void onDestroy() {
    presenter.cancel();
    //ButterKnife.unbind(this);
    presenter.dettach();
    super.onDestroy();
  }

  /**
   * 实例化presenter
   */
  public abstract T initPresenter();


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    boolean cacheView = view == null;
    if (cacheView) {
      view = inflater.inflate(getFragLayoutResID(), container, false);
      ButterKnife.bind(this, view);
      initLoadingDialog();
      initView(view, container, savedInstanceState);
    }
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  public abstract int getFragLayoutResID();

  public abstract void initView(View view, ViewGroup container, Bundle savedInstanceState);


  /**
   * 添加fragment
   */
  public void addFragment(FragmentActivity context, int fragmentContainer) {
    FragmentManager fragmentManager = context.getSupportFragmentManager();
    FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(fragmentContainer, this);
    ft.replace(fragmentContainer, this, this.getClass().getSimpleName()).
        addToBackStack(this.getClass().getSimpleName()).
        commitAllowingStateLoss();

  }

  /**
   * 移除fragment
   */
  protected void removeFragment(FragmentActivity context) {
    UtilLog.Log(getActivity().getSupportFragmentManager().getBackStackEntryCount() + "");
    if (context.getSupportFragmentManager().getBackStackEntryCount() > 1) {
      context.getSupportFragmentManager().popBackStack();

    } else {
      context.finish();
    }
    UtilLog.Log(getActivity().getSupportFragmentManager().getBackStackEntryCount() + "");
  }


  public String getFragmentName() {
    return getClass().getName();
  }

  private void initLoadingDialog() {
    loadDialog = new LoadDialog(getActivity());
  }

  protected void showLoadingDialog(String msg) {
    loadDialog.show();
    if (!TextUtils.isEmpty(msg)) {
      loadDialog.setMessage(msg);
    }
  }

  protected void showLoadingDialog() {
    showLoadingDialog(null);
  }

  protected void hideLoadingDialog() {
    loadDialog.dismiss();
  }


}
