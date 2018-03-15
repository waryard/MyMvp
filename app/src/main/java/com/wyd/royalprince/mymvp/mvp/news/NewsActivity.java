package com.wyd.royalprince.mymvp.mvp.news;

import android.widget.ListView;
import butterknife.BindView;
import butterknife.OnItemClick;
import com.wyd.royalprince.mymvp.R;
import com.wyd.royalprince.mymvp.android.BaseActivity;
import com.wyd.royalprince.mymvp.entity.NewsDataListRespItem;
import com.wyd.royalprince.mymvp.entity.NewsDataReq;
import com.wyd.royalprince.mymvp.mvp.news.adapter.NewsAdapter;
import com.wyd.royalprince.mymvp.mvp.news.presenter.NewsPresenter;
import com.wyd.royalprince.mymvp.mvp.news.view.INewsActivityView;
import com.wyd.royalprince.mymvp.utils.ToastUtil;
import com.wyd.royalprince.mymvp.utils.UtilLog;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends BaseActivity<INewsActivityView, NewsPresenter> implements INewsActivityView {

  @BindView(R.id.news_lv)
  ListView newsLv;
  NewsAdapter newsAdapter;
  List<NewsDataListRespItem> newsDataListRespItems = new ArrayList<>();


  @Override
  public NewsPresenter initPresenter() {
    return new NewsPresenter();
  }

  @Override
  public int getLayoutResID() {
    return R.layout.activity_news;
  }

  @Override
  public void initActivity() {
    newsAdapter = new NewsAdapter(mContext,newsDataListRespItems);
    presenter.getNewsDataList(new NewsDataReq());
    newsLv.setAdapter(newsAdapter);
  }

  @Override
  public void showLoading() {
    showLoadingDialog();
  }

  @Override
  public void hideLoading() {
    hideLoadingDialog();
  }

  @Override
  public void showNewsDataList(List<NewsDataListRespItem> newsDataListRespItems) {
    UtilLog.LogError("------->", "---showNewsDataList---->");
    UtilLog.LogError("------->", "---showNewsDataList---->"+newsDataListRespItems.size());
    newsAdapter.setData(newsDataListRespItems);
  }
  @OnItemClick(R.id.news_lv)
  public void onItemCLick(int position){
    ToastUtil.showToast(mContext,"点击了："+position);
  }
}
