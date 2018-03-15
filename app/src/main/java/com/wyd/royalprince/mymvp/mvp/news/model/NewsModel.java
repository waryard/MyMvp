package com.wyd.royalprince.mymvp.mvp.news.model;

import com.wyd.royalprince.mymvp.common.model.BaseModel;
import com.wyd.royalprince.mymvp.common.net.ErrKind;
import com.wyd.royalprince.mymvp.entity.BaseResp;
import com.wyd.royalprince.mymvp.entity.NewsDataListResp;
import com.wyd.royalprince.mymvp.entity.NewsDataListRespItem;
import com.wyd.royalprince.mymvp.entity.NewsDataReq;
import com.wyd.royalprince.mymvp.utils.ErrUtil;
import com.wyd.royalprince.mymvp.utils.UtilLog;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by wyd on 2017/6/5.
 */

public class NewsModel extends BaseModel implements INewsModel {

  @Override
  public void getNewsDataList(NewsDataReq newsDtaReq, final OnLoadListener<List<NewsDataListRespItem>> onLoadListener, Object tag) {
    mService.getNewsDataList("data/Android/10/1",new Callback<NewsDataListResp>() {
      @Override
      public void success(NewsDataListResp newsDataListResp, Response response) {
        if (!newsDataListResp.isError()) {
          UtilLog.LogError("------->", "------->" + newsDataListResp.getResults());
          onLoadListener.onSuccess(newsDataListResp.getResults());
        }
      }

      @Override
      public void failure(RetrofitError retrofitError) {
        UtilLog.LogError("------->", "-failure------>");
        ErrUtil.handleRetrofitError(retrofitError, new SSCallback() {
          @Override
          public void success(Object resp) {

          }

          @Override
          public void failure(ErrKind errKind, int httpStatus) {

          }
        });
        onLoadListener.onFailure("失败");
      }
    });

//    mService.getPostData("", new NewsDataReq(), new Callback<BaseResp>() {
//      @Override
//      public void success(BaseResp baseResp, Response response) {
//        if (baseResp instanceof NewsDataListResp){
//          //do something
//        }
//      }
//
//      @Override
//      public void failure(RetrofitError retrofitError) {
//
//      }
//    });
  }
}
