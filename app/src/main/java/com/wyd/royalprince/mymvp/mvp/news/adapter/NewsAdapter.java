package com.wyd.royalprince.mymvp.mvp.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.wyd.royalprince.mymvp.R;
import com.wyd.royalprince.mymvp.entity.NewsDataListRespItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyd on 2017/6/5.
 */

public class NewsAdapter extends BaseAdapter {

  private LayoutInflater mInflater;
  private Context mContext;
  private List<NewsDataListRespItem> mData = new ArrayList<>();
  private int mDataSize;

  public NewsAdapter(Context mContext, List<NewsDataListRespItem> mData) {
    this.mContext = mContext;
    mInflater = LayoutInflater.from(this.mContext);
    this.mData = mData;
    mDataSize = (mData == null) ? 0 : mData.size();
  }

  public void appendData(List<NewsDataListRespItem> data) {
    if (data == null) {
      return;
    }
    if (mData == null) {
      mData = new ArrayList<NewsDataListRespItem>();
    }

    mData.addAll(data);
    mDataSize = (mData == null) ? 0 : mData.size();
    this.notifyDataSetChanged();
  }

  public List<NewsDataListRespItem> getData() {
    return mData;
  }

  public void setData(List<NewsDataListRespItem> data) {
    mData = data;
    mDataSize = (mData == null) ? 0 : mData.size();
    this.notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return mData == null ? 0 : mDataSize;
  }

  @Override
  public Object getItem(int position) {
    int count = getCount();
    return position >= 0 && position < count ? mData.get(position) : null;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = mInflater.inflate(R.layout.activity_news_item, parent, false);
      viewHolder = new ViewHolder(convertView);
      convertView.setTag(convertView);
    }else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    viewHolder.newsItemTv.setText(mData.get(position).getDesc());
    return convertView;
  }

  static class ViewHolder {

    @BindView(R.id.news_item_iv)
    ImageView newsItemIv;
    @BindView(R.id.news_item_tv)
    TextView newsItemTv;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
