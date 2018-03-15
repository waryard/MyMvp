package com.wyd.royalprince.mymvp.mvp.news.presenter;

import com.wyd.royalprince.mymvp.common.model.AbsModel.OnLoadListener;
import com.wyd.royalprince.mymvp.common.presenter.BasePresenter;
import com.wyd.royalprince.mymvp.entity.NewsDataListRespItem;
import com.wyd.royalprince.mymvp.entity.NewsDataReq;
import com.wyd.royalprince.mymvp.mvp.news.model.INewsModel;
import com.wyd.royalprince.mymvp.mvp.news.model.NewsModel;
import com.wyd.royalprince.mymvp.mvp.news.view.INewsActivityView;
import java.util.List;

/**
 * Created by wyd on 2017/6/5.
 */

public class NewsPresenter extends BasePresenter<INewsActivityView> {

  INewsModel newsModel;

  public NewsPresenter() {
    newsModel = new NewsModel();
  }

  public void getNewsDataList(NewsDataReq newsDtaReq){
    newsModel.getNewsDataList(newsDtaReq, new OnLoadListener<List<NewsDataListRespItem>>() {
      @Override
      public void onSuccess(List<NewsDataListRespItem> newsDataListRespItems) {
          mView.showNewsDataList(newsDataListRespItems);
      }

      @Override
      public void onFailure(String msg) {

      }
    },tag);
  }
}
