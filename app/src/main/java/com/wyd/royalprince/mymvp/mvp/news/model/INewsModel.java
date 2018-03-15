package com.wyd.royalprince.mymvp.mvp.news.model;

import com.wyd.royalprince.mymvp.common.model.AbsModel;
import com.wyd.royalprince.mymvp.entity.NewsDataListRespItem;
import com.wyd.royalprince.mymvp.entity.NewsDataReq;
import java.util.List;

/**
 * Created by wyd on 2017/6/5.
 */

public interface INewsModel extends AbsModel {

  void getNewsDataList(NewsDataReq newsDtaReq, OnLoadListener<List<NewsDataListRespItem>> onLoadListener, Object tag);
}
