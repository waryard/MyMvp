package com.wyd.royalprince.mymvp.mvp.news.view;

import com.wyd.royalprince.mymvp.common.view.BaseView;
import com.wyd.royalprince.mymvp.entity.NewsDataListRespItem;
import java.util.List;

/**
 * Created by wyd on 2017/6/5.
 */

public interface INewsActivityView extends BaseView {

  void showNewsDataList(List<NewsDataListRespItem> newsDataListRespItems);

}
