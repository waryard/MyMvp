package com.wyd.royalprince.mymvp.common.model;

/**
 * Created by wyd on 2016/5/18.
 */
public interface AbsModel {
//    public abstract Object getData();


  interface OnLoadListener<T> {

    void onSuccess(T t);

    void onFailure(String msg);
  }

}
