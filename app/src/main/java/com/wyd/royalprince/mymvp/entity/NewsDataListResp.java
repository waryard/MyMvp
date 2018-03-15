package com.wyd.royalprince.mymvp.entity;

import java.util.List;

/**
 * Created by wyd on 2017/6/5.
 */

public class NewsDataListResp extends BaseResp {

  private List<NewsDataListRespItem> results;

  public List<NewsDataListRespItem> getResults() {
    return results;
  }

  public void setResults(List<NewsDataListRespItem> results) {
    this.results = results;
  }
}
