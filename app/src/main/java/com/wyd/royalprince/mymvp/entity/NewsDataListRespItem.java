package com.wyd.royalprince.mymvp.entity;

/**
 * Created by wyd on 2017/6/5.
 */

public class NewsDataListRespItem {
  private String desc;

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public String toString() {
    return "NewsDataListRespItem{" +
        "desc='" + desc + '\'' +
        '}';
  }
}
