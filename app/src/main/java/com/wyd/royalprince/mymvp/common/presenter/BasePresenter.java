package com.wyd.royalprince.mymvp.common.presenter;

import android.content.Context;


/**
 * Autor：wyd on 2016/5/30 21:48
 */
public abstract class BasePresenter<T> {

  public T mView;
  public Object tag;//网络请求的tag，取消用。

  public Context context;

  public void attach(T mView) {
    this.mView = mView;
  }


  public void dettach() {
    mView = null;
  }

  public void cancel() {

  }


  public void setTag(Object tag) {
    this.tag = tag;
  }
}
